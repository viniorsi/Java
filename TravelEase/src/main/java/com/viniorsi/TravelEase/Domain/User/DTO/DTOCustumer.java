package com.viniorsi.TravelEase.Domain.User.DTO;

import com.stripe.model.Customer;

public record DTOCustumer(
        String id,
        String email,
        String name
) {


    public DTOCustumer(Customer custumer) {
        this(custumer.getId(),custumer.getEmail(),custumer.getName());
    }

}
