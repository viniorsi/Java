package com.viniorsi.TravelEase.Domain.Transaction.DTO;

public record PaymentIntentDTO(
        String paymentIntentId,
         PaymentMethodType paymentMethodType,
         String paymentMethodId
     ) {

    public enum PaymentMethodType {
        PIX, BOLETO, CARD
    }
}
