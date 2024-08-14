package com.viniorsi.TravelEase.Domain.Feedback.Entity;

import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Table(name = "Feedback")
@Entity(name = "Feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "destiny_id", referencedColumnName = "id")
    private Destiny destiny;

    private int rating;
    private String rating_text;
    private LocalDate FeedbackDate;


    public Feedback(User user_id, Destiny destiny_id, int rating, String rating_text) {
        this.user_id = user_id;
        this.destiny = destiny_id;
        this.rating = rating;
        this.rating_text = rating_text;
        FeedbackDate = LocalDate.now();
    }
}
