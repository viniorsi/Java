package com.br.TravelEase.Travel.Domain.Ticket.Entity;

import com.viniorsi.TravelEase.Domain.Airplane.Seat.Entity.Seat;
import com.viniorsi.TravelEase.Domain.Ticket.Enums.StatusTicketUsageEnum;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "Ticket")
@Entity(name = "Ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    private String name;
    private String cpf;
    private LocalDate birthday;
    @ManyToOne
    @JoinColumn(name = "travel_id", referencedColumnName = "id")
    private Travel travel_id;
    @OneToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id")
    private Seat seat_id;
    private String qrcode;
    private String qrcodeHash;
    private LocalDateTime emission_date;
    @Enumerated(EnumType.STRING)
    private StatusTicketUsageEnum status;
    private LocalDateTime usage_date;


    public Ticket(User user_id, String name,
                  String cpf, LocalDate birthday, Travel travel_id,
                  Seat seat_id, String qrcode, String qrcodeHash) {
        this.user_id = user_id;
        this.name = name;
        this.cpf = cpf;
        this.birthday = birthday;
        this.travel_id = travel_id;
        this.seat_id = seat_id;
        this.qrcode = qrcode;
        this.qrcodeHash = qrcodeHash;
        this.emission_date = LocalDateTime.now();
        this.status = StatusTicketUsageEnum.P;
        this.usage_date = null;
    }
}
