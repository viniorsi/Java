package com.viniorsi.TravelEase.Domain.Transaction.DTO;

import com.viniorsi.TravelEase.Domain.Transaction.Entity.Transaction;
import com.viniorsi.TravelEase.Domain.Transaction.Enums.StatusPaymentEnum;

import java.time.LocalDateTime;

public record DTOPaymentDetails(

        Long transaction_id,
        String name,
        Double value,
        LocalDateTime transactionDate,
        String statusPayment
) {
    public DTOPaymentDetails(Transaction transaction) {
        this( transaction.getId(), transaction.getUser().getName(), transaction.getValue(), transaction.getTransactionDate(), transaction.getStatusPayment().getDescricao()
        );

    }
}
