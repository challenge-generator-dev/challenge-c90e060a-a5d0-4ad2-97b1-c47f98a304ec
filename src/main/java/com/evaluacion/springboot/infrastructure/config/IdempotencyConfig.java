package com.evaluacion.springboot.infrastructure.config;


import com.evaluacion.springboot.application.usecases.CreateCreditRequestUseCase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Component
public class IdempotencyConfig {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyConfig.class);
    private static final long CACHE_EXPIRY_MILLIS = 3600000;
    private static final int MAX_CACHE_ENTRIES = 10000;

    private final Map<String, IdempotencyCacheEntry> idempotencyCache = new ConcurrentHashMap<>();
    private final ReentrantLock cacheCleanupLock = new ReentrantLock();

    @Around("execution(* com.evaluacion.springboot.application.usecases.CreateCreditRequestUseCase.execute(..))")
    public Object checkIdempotency(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        
        if (args.length < 2) {
            log.debug("Método no requiere verificación de idempotencia");
            return joinPoint.proceed();
        }
        
        String operationNumber = extractOperationNumber(args);
        String channel = extractChannel(args);
        String idempotencyKey = extractIdempotencyKey(args);
        
        if (operationNumber == null || channel == null) {
            log.debug("Parámetros de idempotencia no proporcionados, continuando con ejecución normal");
            return joinPoint.proceed();
        }
        
        String cacheKey = buildCacheKey(operationNumber, channel);
        
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            cacheKey = buildCacheKey(idempotencyKey, channel);
        }
        
        log.debug("Verificando idempotencia para clave: {}", cacheKey);
        
        IdempotencyCacheEntry existingEntry = idempotencyCache.get(cacheKey);
        
        if (existingEntry != null) {
            if (System.currentTimeMillis() - existingEntry.timestamp > CACHE_EXPIRY_MILLIS) {
                log.info("Entrada de caché expirada para clave: {}, removiendo", cacheKey);
                idempotencyCache.remove(cacheKey);
            } else if (existingInProgress(cacheKey)) {
                log.warn("Solicitud con clave de idempotencia {} ya está en progreso", cacheKey);
                throw new IllegalStateException("Request with this idempotency key is already being processed");
            } else {
                log.info("Respuesta cacheada encontrada para clave: {}", cacheKey);
                return existingEntry.result;
            }
        }
        
        if (idempotencyCache.size() >= MAX_CACHE_ENTRIES) {
            cleanupExpiredEntries();
        }
        
        markInProgress(cacheKey);
        
        try {
            Object result = joinPoint.proceed();
            
            idempotencyCache.put(cacheKey, new IdempotencyCacheEntry(result, System.currentTimeMillis()));
            log.info("Resultado almacenado en caché de idempotencia para clave: {}", cacheKey);
            
            return result;
            
        } finally {
            removeInProgress(cacheKey);
        }
    }

    private String extractOperationNumber(Object[] args) {
        if (args.length > 0 && args[0] != null) {
            try {
                return args[0].getClass().getMethod("operationNumber").invoke(args[0]).toString();
            } catch (Exception e) {
                log.debug("No se pudo extraer operationNumber del argumento");
            }
        }
        return null;
    }

    private String extractChannel(Object[] args) {
        if (args.length > 0 && args[0] != null) {
            try {
                return args[0].getClass().getMethod("channel").invoke(args[0]).toString();
            } catch (Exception e) {
                log.debug("No se pudo extraer channel del argumento");
            }
        }
        return null;
    }

    private String extractIdempotencyKey(Object[] args) {
        if (args.length > 1 && args[1] != null) {
            return args[1].toString();
        }
        return null;
    }

    private String buildCacheKey(String operationNumber, String channel) {
        return operationNumber + ":" + channel;
    }

    private boolean existingInProgress(String cacheKey) {
        return idempotencyCache.containsKey(cacheKey + ":inProgress");
    }

    private void markInProgress(String cacheKey) {
        idempotencyCache.put(cacheKey + ":inProgress", 
                new IdempotencyCacheEntry(null, System.currentTimeMillis()));
    }

    private void removeInProgress(String cacheKey) {
        idempotencyCache.remove(cacheKey + ":inProgress");
    }

    private void cleanupExpiredEntries() {
        if (cacheCleanupLock.tryLock()) {
            try {
                long now = System.currentTimeMillis();
                long expiryThreshold = now - CACHE_EXPIRY_MILLIS;
                
                idempotencyCache.entrySet().removeIf(entry -> 
                        entry.getValue().timestamp < expiryThreshold);
                
                log.info("Limpieza de caché de idempotencia completada. Entradas restantes: {}", 
                        idempotencyCache.size());
                
            } finally {
                cacheCleanupLock.unlock();
            }
        }
    }

    public void clearCache() {
        log.info("Limpiando caché de idempotencia");
        idempotencyCache.clear();
    }

    public int getCacheSize() {
        return idempotencyCache.size();
    }

    private static class IdempotencyCacheEntry {
        final Object result;
        final long timestamp;

        IdempotencyCacheEntry(Object result, long timestamp) {
            this.result = result;
            this.timestamp = timestamp;
        }
    }
}