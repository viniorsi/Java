package com.br.TravelEase.Hotels.feing;

import com.br.TravelEase.Hotels.model.Quarto.Enum.TipoQuarto;
import com.br.TravelEase.Hotels.model.Quarto.Quarto;

import java.math.BigDecimal;
import java.util.List;

public record QuartoDetalhesResponse(
         Long id,
         TipoQuarto tipo,
         BigDecimal precoPorNoite,
         List<String> fotos, // URLs das imagens
         Integer capacidade) {

    public QuartoDetalhesResponse(Quarto quarto){
        this(quarto.getId(),quarto.getTipo(),quarto.getPrecoPorNoite(),quarto.getFotos(),quarto.getCapacidade());
    }

}
