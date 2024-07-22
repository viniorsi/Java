package com.viniorsi.TravelEase.Domain.Transactional.Enums;

public enum StatusPaymentEnum {

    P("Pago"),
    A("Aguardando Pagamento"),
    R("Recusado");

    private String descricao;

    StatusPaymentEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static String getDescricaoByLetra(String letra) {
        for (StatusPaymentEnum status : values()) {
            if (status.name().equals(letra)) {
                return status.getDescricao();
            }
        }
        throw new IllegalArgumentException("Status não encontrado para a letra: " + letra);
    }


}
