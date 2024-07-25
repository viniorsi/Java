package com.viniorsi.TravelEase.Service.Transactional;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.TokenCreateParams;
import com.stripe.param.issuing.CardCreateParams;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOADDCard;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Autowired
    UserRespository userRespository;


    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }


    public Customer createCustomer(String email, String name) throws StripeException {
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setEmail(email)
                .setName(name)
                .build();

        return Customer.create(params);
    }

    public Customer retrieveCustomer(String cpf) throws StripeException {
        User user = userRespository.getReferenceByCpf(cpf) ;
        Customer customer = Customer.retrieve(user.getId_customer_stripe());
        return customer;
    }

    public Customer addCardToCustomer(@Valid DTOADDCard dtoaddCard) throws StripeException {
        User user = userRespository.getReferenceByCpf(dtoaddCard.cpf()) ;
        Customer customer = Customer.retrieve(user.getId_customer_stripe());

        CustomerUpdateParams updateParams = CustomerUpdateParams.builder()
                .setSource(dtoaddCard.tokenId())
                .build();

        return customer.update(updateParams);
    }





}
