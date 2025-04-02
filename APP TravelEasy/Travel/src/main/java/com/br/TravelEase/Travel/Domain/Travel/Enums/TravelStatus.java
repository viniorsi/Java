package com.br.TravelEase.Travel.Domain.Travel.Enums;

public enum TravelStatus {
    PENDING,       // Viagem criada, mas não confirmada
    CONFIRMED,     // Viagem confirmada
    COMPLETED,     // Viagem realizada
    CANCELED,      // Viagem cancelada
    RESCHEDULED
}
