package com.viniorsi.TravelEase.Domain.Feedback.DTO;

import java.time.LocalDate;

public record DTOGiveFeedback(

        Long user_id,
         Long destiny_id,
         int rating,
         String rating_text,
         LocalDate FeedBackDate) {
}
