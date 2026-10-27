package com.evaluacion.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.evaluacion.springboot.infrastructure.config.AppProperties;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableTransactionManagement
@EnableAsync
public class Application {
    
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        loggingFilter.setIncludeHeaders(true);
        return loggingFilter;
    }
    
    @Bean
    public String applicationInfo(AppProperties properties) {
        System.out.println("\n===========================================\n");
        System.out.println("Aplicación: " + properties.getApplicationName());
        System.out.println("Ambiente: " + properties.getEnvironment());
        System.out.println("Versión: " + properties.getVersion());
        System.out.println("Tamaño máximo de solicitud: " + properties.getMaxRequestSize() + " MB");
        System.out.println("Idempotencia habilitada: " + properties.isIdempotencyEnabled());
        System.out.println("===========================================\n");
        return "Application info logged";
    }
}