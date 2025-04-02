package com.br.TravelEase.Transactions.model;


import com.br.TravelEase.Transactions.feign.request.PaymentRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data // Lombok para getters/setters
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(name = "stripe_payment_id")
    private String stripePaymentId;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String currency; // Ex: "BRL", "USD"

    @Column(nullable = false)
    private String paymentMethod; // Ex: "CREDIT_CARD", "PIX"

    @Column(nullable = false)
    private String status; // "APPROVED", "PENDING", "FAILED"

    @Embedded
    private TravelDetails travelDetails; // Detalhes da viagem

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Construtor que recebe o DTO
    public Transaction(PaymentRequest paymentRequest) {
        this.userId = paymentRequest.userId();
        this.amount = paymentRequest.amount();
        this.currency = paymentRequest.currency();
        this.paymentMethod = paymentRequest.paymentMethod();

        // Mapeia os detalhes da viagem
        this.travelDetails = new TravelDetails();
        this.travelDetails.setDestination(paymentRequest.destination());
        this.travelDetails.setTravelDate(paymentRequest.travelDate());
        this.travelDetails.setBookingCode(paymentRequest.bookingCode());
    }

    // Classe interna para detalhes da viagem (opcional)
    @Embeddable
    @Data
    public static class TravelDetails {
        private String destination;
        private LocalDateTime travelDate;
        private String bookingCode;
    }
}
