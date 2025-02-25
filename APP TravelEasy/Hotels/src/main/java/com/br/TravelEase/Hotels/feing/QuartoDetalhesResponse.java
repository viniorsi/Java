package com.br.TravelEase.Hotels.feing;

import com.br.TravelEase.Hotels.model.Quarto.Enum.TipoQuarto;
import com.br.TravelEase.Hotels.model.Quarto.Quarto;

import java.math.BigDecimal;
import java.util.List;

public record QuartoDetalhesResponse(
        private Long id,
        private TipoQuarto tipo,
        private BigDecimal precoPorNoite,
        private List<String> comodidades, // WiFi, TV, Ar-condicionado, etc.
        private List<String> fotos, // URLs das imagens
        private Integer capacidade) {

    public QuartoDetalhesResponse(Quarto quarto){
        this(quarto.getId(),quarto.getTipo(),quarto.getPrecoPorNoite(),quarto.getComodidades(),quarto.getFotos(),quarto.getCapacidade());
    }

}
