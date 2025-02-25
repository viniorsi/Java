package com.br.TravelEase.Hotels.feing;

import com.br.TravelEase.Hotels.model.Quarto.Enum.TipoQuarto;

import java.math.BigDecimal;

public record QuartoResumoResponse(
         Long id,
         TipoQuarto tipo,
         BigDecimal precoPorNoite
) {

}
