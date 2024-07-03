package com.viniorsi.TravelEase.Domain.User.Enum;

public enum Status {


        ATIVO("A", "Ativo"),
        INATIVO("I", "Inativo"),
        PENDENTE("P", "Pendente");

        private String codigo;
        private String descricao;

        Status(String codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getDescricao() {
            return descricao;
        }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}



