package com.br.TravelEase.Travel.feign.response;

import java.math.BigDecimal;
import java.util.List;

public record HotelDetailResponse(
         Long id,
         String tipo,
         BigDecimal precoPorNoite,
         List<String> comodidades, // WiFi, TV, Ar-condicionado, etc.
         List<String> fotos, // URLs das imagens
         Integer capacidade
) {
}
