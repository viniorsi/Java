package com.br.TravelEase.Travel.Domain.Destiny.Enums;

public enum CountriesEnum {

    // Continente: África
    NIGERIA("Nigéria"),
    EGITO("Egito"),
    AFRICA_DO_SUL("África do Sul"),
    QUENIA("Quênia"),
    GANA("Gana"),

    // Continente: América do Norte
    ESTADOS_UNIDOS("Estados Unidos"),
    CANADA("Canadá"),
    MEXICO("México"),
    CUBA("Cuba"),
    REPUBLICA_DOMINICANA("República Dominicana"),

    // Continente: América do Sul
    BRASIL("Brasil"),
    ARGENTINA("Argentina"),
    COLOMBIA("Colômbia"),
    CHILE("Chile"),
    PERU("Peru"),

    // Continente: Ásia
    CHINA("China"),
    INDIA("Índia"),
    JAPAO("Japão"),
    COREIA_DO_SUL("Coreia do Sul"),
    INDONESIA("Indonésia"),

    // Continente: Europa
    ALEMANHA("Alemanha"),
    FRANCA("França"),
    REINO_UNIDO("Reino Unido"),
    ITALIA("Itália"),
    ESPANHA("Espanha"),

    // Continente: Oceania
    AUSTRALIA("Austrália"),
    NOVA_ZELANDIA("Nova Zelândia"),
    FIJI("Fiji"),
    PAPUA_NOVA_GUINE("Papua-Nova Guiné"),
    SAMOA("Samoa");

    private final String name;

    CountriesEnum(String nome) {
        this.name = nome;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}
