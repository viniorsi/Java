package com.viniorsi.TravelEase.Controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOCustumer;
import com.viniorsi.TravelEase.Domain.UserVerification.DTO.DTOUserVerificationStatus;
import com.viniorsi.TravelEase.Service.Transactional.StripeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/stripe")
@SecurityRequirement(name = "bearer-key")
public class StripeController {


    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-customer")
    public Customer createCustomer(@RequestParam String email, @RequestParam String name) throws StripeException {
        return stripeService.createCustomer(email, name);
    }

    @GetMapping(value = "/retrieve-customer")
    public ResponseEntity<DTOCustumer> retrieveCustomer(@RequestParam String customerId) throws StripeException {
        Customer customer = stripeService.retrieveCustomer(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new DTOCustumer(customer));
        }
    }


}
