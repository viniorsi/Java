package com.br.TravelEase.Travel.feign.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record HotelDetailResponse(
        String nomeHotel,
        String  numeroQuarto,
        LocalDate check_in,
        LocalDate check_out,
        BigDecimal valor,
        String status
) {
    public HotelDetailResponse(HotelDetailResponse original, BigDecimal novoValor) {
        this(original.nomeHotel, original.numeroQuarto(), original.check_in,original.check_out, novoValor,original.status);
    }
}
