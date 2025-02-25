package com.br.TravelEase.Hotels.feing;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AvailabilityResponse {
    private boolean available; // Indica se há disponibilidade no período
    private int availableRooms; // Quantidade de quartos disponíveis
    private LocalDate checkInDate; // Data de check-in solicitada
    private LocalDate checkOutDate; // Data de check-out solicitada
    private BigDecimal pricePerNight; // Preço por noite (pode variar por período)
    private String message; // Mensagem adicional (ex.: "Últimos 2 quartos!")


}