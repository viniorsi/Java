package com.viniorsi.TravelEase.Domain.Airplane.Airplane.Entity;

import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Enums.StatusAirplaneEnum;
import com.viniorsi.TravelEase.Domain.Airplane.Seat.Entity.Seat;
import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "Airplane")
@Entity(name = "Airplane")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id  ;
    @ManyToOne
    @JoinColumn(name = "destiny_id", referencedColumnName = "id")
    private Destiny destiny;
    private String model;
    private String registration_number;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private StatusAirplaneEnum status;
    private String airline;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seat> seats;






}
