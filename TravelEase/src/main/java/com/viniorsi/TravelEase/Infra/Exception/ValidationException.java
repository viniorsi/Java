package com.viniorsi.TravelEase.Infra.Exception;

    public class ValidationException extends RuntimeException {
        public ValidationException(String mensagem) {
            super(mensagem);
        }
    }
