package com.viniorsi.TravelEase.Domain.Airplane.Seat.Enums;

import com.viniorsi.TravelEase.Domain.User.Enums.StatusEnum;
import lombok.Getter;

@Getter
public enum StatusSeatEnum {

    R("Reservado"),
    L("Livre"),
    P("Pendente");

    private String descricao;

    StatusSeatEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static String getDescricaoByLetra(String letra) {
        for (StatusSeatEnum status : values()) {
            if (status.name().equals(letra)) {
                return status.getDescricao();
            }
        }
        throw new IllegalArgumentException("Status não encontrado para a letra: " + letra);
    }
}
