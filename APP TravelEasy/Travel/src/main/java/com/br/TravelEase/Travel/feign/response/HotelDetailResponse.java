package com.br.TravelEase.Travel.feign.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record HotelDetailResponse(
        String  numeroQuarto,
        LocalDate check_in,
        LocalDate check_out,
        BigDecimal valor,
        String status
) {
}
