package com.viniorsi.TravelEase.Domain.TravelHotels.Entity;

import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "TravelHotels")
@Table(name = "travel_hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TravelHotels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    @ManyToOne
    @JoinColumn(name = "hotels_id")
    private Hotels hotels;

    private int adult_count;
    private int kids_count;
    private int pet;
    private LocalDateTime entry_date;
    private LocalDateTime end_date;

    public TravelHotels(Travel travel, Hotels hotel, int adults_count, int kids_count, int pet, LocalDateTime entry_date, LocalDateTime end_date) {
        this.travel = travel;
        this.hotels = hotel;
        this.adult_count = adults_count;
        this.kids_count = kids_count;
        this.pet = pet;
        this.entry_date = entry_date;
        this.end_date = end_date;
    }


}
