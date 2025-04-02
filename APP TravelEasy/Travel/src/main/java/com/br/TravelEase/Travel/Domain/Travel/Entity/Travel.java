package com.br.TravelEase.Travel.Domain.Travel.Entity;


import com.br.TravelEase.Travel.Domain.Destiny.Entity.Destiny;
import com.br.TravelEase.Travel.Domain.Destiny.Enums.CountriesEnum;
import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.br.TravelEase.Travel.Domain.Travel.Enums.PaymentStatus;
import com.br.TravelEase.Travel.Domain.Travel.Enums.TravelStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


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
    @JoinColumn(name = "destiny_id", referencedColumnName = "id")
    private Destiny destiny;

    UUID transaction_id;

    private Long user_id;

    @Enumerated(EnumType.STRING)
    private CountriesEnum departureLocation;
    private LocalDateTime departureDate;
    private LocalDateTime return_date;
    private boolean roundTrip;
    private int ticketsCount;

    @Enumerated(EnumType.STRING)
    private TravelStatus travelStatus;  // Status da viagem

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;




    public Travel(Long user_id, Destiny destiny, DTOTravelRequest dtoTravelRequest) {
        this.user_id = user_id;
        this.destiny = destiny;
        this.departureLocation = CountriesEnum.valueOf(dtoTravelRequest.departureLocation());
        this.departureDate = dtoTravelRequest.departureDate();
        this.return_date = dtoTravelRequest.returnDate();
        this.roundTrip = dtoTravelRequest.isRoundTrip();
        this.ticketsCount = dtoTravelRequest.ticketscount();
        this.travelStatus = TravelStatus.PENDING;
        this.paymentStatus = PaymentStatus.PENDENTE;
    }
}
