package com.evaluacion.springboot.infrastructure.repositories;

import com.evaluacion.springboot.infrastructure.adapters.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditRequestJpaRepository extends JpaRepository<CreditRequestEntity, UUID> {
    
    Optional<CreditRequestEntity> findByOperationNumberAndChannel(String operationNumber, String channel);
    
    boolean existsByOperationNumberAndChannel(String operationNumber, String channel);
    
    @Query("SELECT cr FROM CreditRequestEntity cr WHERE cr.clientId = :clientId AND cr.status = :status")
    java.util.List<CreditRequestEntity> findByClientIdAndStatus(
        @Param("clientId") String clientId, 
        @Param("status") String status
    );
    
    @Query("SELECT COUNT(cr) FROM CreditRequestEntity cr WHERE cr.createdAt >= :startDate")
    long countRequestsSince(java.time.Instant startDate);
    
    @Query("SELECT cr FROM CreditRequestEntity cr WHERE cr.status = 'PENDING' AND cr.createdAt < :threshold")
    java.util.List<CreditRequestEntity> findStalePendingRequests(java.time.Instant threshold);
}