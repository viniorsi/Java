package com.br.TravelEase.Travel.Domain.Feedback.DTO;

import com.viniorsi.TravelEase.Domain.Destiny.Enums.CountriesEnum;
import com.viniorsi.TravelEase.Domain.Feedback.Entity.Feedback;

import java.time.LocalDate;

public record DTOfeedbackDetails(
        String user_name,
        CountriesEnum destiny_name,
        int rating,
        String rating_text,
        LocalDate FeedbackDate
) {
    public DTOfeedbackDetails(Feedback feedback) {
        this(
                feedback.getUser_id().getName(),
                feedback.getDestiny().getCountry(),
                feedback.getRating(),
                feedback.getRating_text(),
                feedback.getFeedbackDate());
    }

}
