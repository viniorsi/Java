package com.br.TravelEase.Travel.Domain.Feedback.DTO;

public record DTOGiveFeedback(

        String user_cpf,
        Long destiny_id,
        int rating,
        String rating_text
) {
}
