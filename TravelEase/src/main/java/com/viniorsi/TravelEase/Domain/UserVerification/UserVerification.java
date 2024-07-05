package com.viniorsi.TravelEase.Domain.UserVerification;


import com.viniorsi.TravelEase.Domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "usersverification")
@Entity(name = "UserVerification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private LocalDateTime expirationDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

     public UserVerification(User user){
        this.user = user;
        this.uuid = UUID.randomUUID().toString();
        this.expirationDate = LocalDateTime.now().plusHours(2);
    }
}
