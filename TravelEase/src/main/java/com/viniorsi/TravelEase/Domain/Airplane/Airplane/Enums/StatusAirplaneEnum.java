package com.viniorsi.TravelEase.Domain.Airplane.Airplane.Enums;

import lombok.Getter;

@Getter
public enum StatusAirplaneEnum {
    A( "Ativo"),
    M( "Manutenção"),
    D( "Desativado");

    private String descricao;

    StatusAirplaneEnum(String descricao) {
        this.descricao = descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static String getDescricaoByLetra(String letra) {
        for (StatusAirplaneEnum status : values()) {
            if (status.name().equals(letra)) {
                return status.getDescricao();
            }
        }
        throw new IllegalArgumentException("Status não encontrado para a letra: " + letra);
    }
}
