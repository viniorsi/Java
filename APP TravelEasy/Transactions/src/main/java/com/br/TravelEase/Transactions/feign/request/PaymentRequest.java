package com.br.TravelEase.Transactions.feign.request;

import java.time.LocalDateTime;

public record PaymentRequest(
         String userId,
         Double amount,
         String currency,
         String paymentMethod,
         String paymentMethodId,
         String destination,
         LocalDateTime travelDate,
         String bookingCode

) {
}
