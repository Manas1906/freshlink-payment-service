package com.freshlink.payment.repository;

import com.freshlink.payment.model.LedgerEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LedgerRepository extends JpaRepository<LedgerEntry, Long> {

    Optional<LedgerEntry> findTopByUserEmailOrderByIdDesc(String userEmail);
}

