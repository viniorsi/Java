package com.viniorsi.TravelEase.Domain.Airplane.Seat.Entity;

import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Entity.Airplane;
import com.viniorsi.TravelEase.Domain.Airplane.Seat.Enums.StatusSeatEnum;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Seat")
@Entity(name = "Seat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id  ;
    @ManyToOne
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;
    private String seat_number;
    @Enumerated(EnumType.STRING)
    private StatusSeatEnum status;



}
