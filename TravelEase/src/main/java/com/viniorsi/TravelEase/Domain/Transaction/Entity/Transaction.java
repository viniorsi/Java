package com.viniorsi.TravelEase.Domain.Transaction.Entity;


import com.viniorsi.TravelEase.Domain.Transaction.Enums.StatusPaymentEnum;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "transactional")
@Entity(name = "Transactional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id  ;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "travel_id", referencedColumnName = "id")
    private Travel travel;

    private String stripe_transaction_id;

    private int points;
    private double value;
    private LocalDateTime transactionDate;
    private LocalDateTime paymentDeadLine;
    @Enumerated(EnumType.STRING)
    private StatusPaymentEnum statusPayment;


    public Transaction(User user, Double value, int points, Travel travel) {
        this.user = user;
        this.travel = travel;
        this.value = value;
        this.points = points;
        this.paymentDeadLine = LocalDateTime.now().plusDays(3);
        this.statusPayment = StatusPaymentEnum.A;
    }

}
