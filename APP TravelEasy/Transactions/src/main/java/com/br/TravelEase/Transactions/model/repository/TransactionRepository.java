package com.br.TravelEase.Transactions.model.repository;

import com.br.TravelEase.Transactions.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserId(String userId);
}
