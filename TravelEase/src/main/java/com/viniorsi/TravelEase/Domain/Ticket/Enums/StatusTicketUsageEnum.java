package com.viniorsi.TravelEase.Domain.Ticket.Enums;

import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;

public enum StatusTicketUsageEnum {


    A( "Ativo"),
    U( "Usado"),
    P( "Pendente"),
    C ("Cancelado");

    private String descricao;

    StatusTicketUsageEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static String getDescricaoByLetra(String letra) {
        for (StatusTicketUsageEnum status : values()) {
            if (status.name().equals(letra)) {
                return status.getDescricao();
            }
        }
        throw new IllegalArgumentException("Status não encontrado para a letra: " + letra);
    }
}
