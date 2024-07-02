package com.viniorsi.TravelEase.Domain.Address.DTO;

public record DTOAddress(
        String zip_code,
        String address,
        String number,
        String additional_info,
        String neigborhood,
        String state,
        String city
        )
{ }
