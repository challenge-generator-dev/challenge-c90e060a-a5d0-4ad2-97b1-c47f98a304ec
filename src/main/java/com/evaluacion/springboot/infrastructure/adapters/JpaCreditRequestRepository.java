package com.evaluacion.springboot.infrastructure.adapters;

import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.model.CreditRequestStatus;
import com.evaluacion.springboot.domain.ports.CreditRequestRepository;
import com.evaluacion.springboot.infrastructure.repositories.CreditRequestJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class JpaCreditRequestRepository implements CreditRequestRepository {
    
    private static final Logger log = LoggerFactory.getLogger(JpaCreditRequestRepository.class);
    
    private final CreditRequestJpaRepository jpaRepository;
    
    public JpaCreditRequestRepository(CreditRequestJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        log.debug("Guardando solicitud de crédito: operationNumber={}, channel={}",
            creditRequest.operationNumber(), creditRequest.channel());
        
        CreditRequestEntity entity = toEntity(creditRequest);
        CreditRequestEntity savedEntity = jpaRepository.save(entity);
        
        log.info("Solicitud de crédito guardada exitosamente: id={}, status={}",
            savedEntity.getId(), savedEntity.getStatus());
        
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<CreditRequest> findById(UUID id) {
        log.debug("Buscando solicitud de crédito por id: {}", id);
        return jpaRepository.findById(id).map(this::toDomain);
    }
    
    @Override
    public Optional<CreditRequest> findByOperationNumberAndChannel(String operationNumber, String channel) {
        log.debug("Buscando solicitud por operationNumber={}, channel={}", operationNumber, channel);
        return jpaRepository.findByOperationNumberAndChannel(operationNumber, channel)
            .map(this::toDomain);
    }
    
    @Override
    public boolean existsByOperationNumberAndChannel(String operationNumber, String channel) {
        return jpaRepository.existsByOperationNumberAndChannel(operationNumber, channel);
    }
    
    private CreditRequestEntity toEntity(CreditRequest domain) {
        CreditRequestEntity entity = new CreditRequestEntity();
        entity.setId(domain.id());
        entity.setClientId(domain.clientId());
        entity.setClientName(domain.clientName());
        entity.setClientEmail(domain.clientEmail());
        entity.setClientPhone(domain.clientPhone());
        entity.setRequestedAmount(domain.requestedAmount());
        entity.setCurrency(domain.currency());
        entity.setPaymentTermMonths(domain.paymentTermMonths());
        entity.setPurpose(domain.purpose());
        entity.setOperationNumber(domain.operationNumber());
        entity.setChannel(domain.channel());
        entity.setStatus(domain.status().name());
        entity.setRejectionReason(domain.rejectionReason());
        entity.setAntifraudReference(domain.antifraudReference());
        entity.setRiskBureauReference(domain.riskBureauReference());
        entity.setCreatedAt(domain.createdAt());
        entity.setUpdatedAt(domain.updatedAt());
        return entity;
    }
    
    private CreditRequest toDomain(CreditRequestEntity entity) {
        return CreditRequest.builder()
            .id(entity.getId())
            .clientId(entity.getClientId())
            .clientName(entity.getClientName())
            .clientEmail(entity.getClientEmail())
            .clientPhone(entity.getClientPhone())
            .requestedAmount(entity.getRequestedAmount())
            .currency(entity.getCurrency())
            .paymentTermMonths(entity.getPaymentTermMonths())
            .purpose(entity.getPurpose())
            .operationNumber(entity.getOperationNumber())
            .channel(entity.getChannel())
            .status(CreditRequestStatus.valueOf(entity.getStatus()))
            .rejectionReason(entity.getRejectionReason())
            .antifraudReference(entity.getAntifraudReference())
            .riskBureauReference(entity.getRiskBureauReference())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }
}