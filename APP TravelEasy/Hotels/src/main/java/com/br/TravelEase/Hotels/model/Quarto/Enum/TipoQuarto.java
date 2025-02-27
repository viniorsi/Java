package com.br.TravelEase.Hotels.model.Quarto.Enum;

public enum TipoQuarto {
    STANDARD,
    LUXO,
    SUITE,
    FAMILIA,
    PRESIDENCIAL;

    public static TipoQuarto fromString(String value) {
        return TipoQuarto.valueOf(value.toUpperCase());
    }
}