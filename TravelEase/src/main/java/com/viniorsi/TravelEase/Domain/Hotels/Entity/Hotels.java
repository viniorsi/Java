package com.viniorsi.TravelEase.Domain.Hotels.Entity;

import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Hotels")
@Entity(name = "Hotels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Hotels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "destiny_id", referencedColumnName = "id")
    private Destiny destiny;
    private String name;
    private String description;
    private String type;
    private Double value;
    private String restriction;
    private boolean pet;



}
