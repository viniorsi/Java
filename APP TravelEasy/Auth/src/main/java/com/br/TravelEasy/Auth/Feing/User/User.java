package com.br.TravelEasy.Auth.Feing.User;

import com.br.TravelEasy.Auth.Feing.Address.Entity.Address;
import com.br.TravelEasy.Auth.Feing.enums.StatusEnum;
import com.br.TravelEasy.Auth.Feing.request.DTOUserRegister;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id  ;
    private String id_customer_stripe;
    private String cpf ;
    private String name ;
    private String tel ;
    private String ddd ;
    private String email ;
    private String password ;
    private LocalDate birthday ;
    private LocalDate creation_date ;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private StatusEnum status ;
    private int points;

    public User(DTOUserRegister data) {
        this.cpf = data.cpf();
        this.name = data.name();
        this.tel = data.tel();
        this.ddd = data.ddd();
        this.email = data.email();
        this.password = data.password();
        this.birthday = data.birthday();
        this.creation_date = LocalDate.now();
        this.address = new Address(data.address());
        this.status = StatusEnum.P;
        this.points = 0;
    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
