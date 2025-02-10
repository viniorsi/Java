package com.br.TravelEasy.Auth.Feing.Address.Entity;

import com.br.TravelEasy.Auth.Feing.Address.DTO.DTOAddress;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String zip_code;
    private String address;
    private String number;
    private String additional_info;
    private String neigborhood;
    private String state;
    private String city;

    public Address(DTOAddress dtoAddress) {
        this.zip_code = dtoAddress.zip_code();
        this.address = dtoAddress.address();
        this.number = dtoAddress.number();
        this.additional_info = dtoAddress.additional_info();
        this.neigborhood = dtoAddress.neigborhood();
        this.state = dtoAddress.state();
        this.city = dtoAddress.city();
    }
}
