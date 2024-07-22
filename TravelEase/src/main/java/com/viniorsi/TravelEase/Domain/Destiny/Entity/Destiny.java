package com.viniorsi.TravelEase.Domain.Destiny.Entity;



import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Entity.Airplane;
import com.viniorsi.TravelEase.Domain.Destiny.Enums.ContinentsEnum;
import com.viniorsi.TravelEase.Domain.Destiny.Enums.CountriesEnum;
import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Table(name = "destiny")
@Entity(name = "Destiny")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destiny {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private CountriesEnum country;
    private String description;
    private String image_url;
    private Double daily_value;
    @Enumerated(EnumType.STRING)
    private ContinentsEnum continent;
    @OneToMany(mappedBy = "destiny", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Airplane> airplanes;



}
