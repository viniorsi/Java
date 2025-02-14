package com.br.TravelEase.Travel.Domain.Destiny.Enums;

public enum ContinentsEnum {

        AFRICA("África"),
        AMERICA_DO_NORTE("América do Norte"),
        AMERICA_DO_SUL("América do Sul"),
        ANTARTIDA("Antártida"),
        ASIA("Ásia"),
        EUROPA("Europa"),
        OCEANIA("Oceania");

        private final String name;

        ContinentsEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

}
