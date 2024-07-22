package com.viniorsi.TravelEase.Domain.Travel.Entity;


import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Destiny.Enums.CountriesEnum;
import com.viniorsi.TravelEase.Domain.Transactional.Entity.Transactional;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Domain.Transactional.Enums.StatusPaymentEnum;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Table(name = "Travels")
@Entity(name = "Travel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Travel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "destiny_id", referencedColumnName = "id")
    private Destiny destiny;


    @Enumerated(EnumType.STRING)
    private CountriesEnum departureLocation;
    private LocalDateTime departureDate;
    private LocalDateTime return_date;
    private boolean roundTrip;
    private int ticketsCount;




    public Travel(User user, Destiny destiny, DTOTravelRequest dtoTravelRequest) {
        this.user = user;
        this.destiny = destiny;
        this.departureLocation = CountriesEnum.valueOf(dtoTravelRequest.departureLocation());
        this.departureDate = dtoTravelRequest.departureDate();
        this.return_date = dtoTravelRequest.returnDate();
        this.roundTrip = dtoTravelRequest.isRoundTrip();
        this.ticketsCount = dtoTravelRequest.ticketscount();
    }
}
