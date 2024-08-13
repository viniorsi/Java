package com.viniorsi.TravelEase.Domain.Transaction.Repository;

import com.viniorsi.TravelEase.Domain.Transaction.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionalRepository extends JpaRepository<Transaction, Long> {


    @Query("""
    select t from Transactional t
    where 
    t.stripe_transaction_id = :paymentIntentId
    """)
    Transaction findTransactionalByStripeTransactionId(@Param("paymentIntentId") String paymentIntentId);



}
