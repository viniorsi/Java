package com.viniorsi.TravelEase.Domain.Feedback.DTO;

import com.viniorsi.TravelEase.Domain.Destiny.Enums.CountriesEnum;
import com.viniorsi.TravelEase.Domain.Feedback.Entity.Feedback;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.UserVerification.Entity.UserVerification;

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
