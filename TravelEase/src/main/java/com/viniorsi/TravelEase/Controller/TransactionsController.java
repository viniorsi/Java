package com.viniorsi.TravelEase.Controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.viniorsi.TravelEase.Domain.Transaction.DTO.DTOPaymentDetails;
import com.viniorsi.TravelEase.Domain.Transaction.DTO.PaymentIntentDTO;
import com.viniorsi.TravelEase.Domain.Transaction.Entity.Transaction;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOADDCard;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOCustumer;
import com.viniorsi.TravelEase.Service.Transactional.StripeService;
import com.viniorsi.TravelEase.Service.Transactional.TransactionalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transactions")
@SecurityRequirement(name = "bearer-key")
public class TransactionsController {

    @Autowired
    private TransactionalService transactionalService;

    @Autowired
    private StripeService stripeService;

    @PostMapping("/create-customer")
    public Customer createCustomer(@RequestParam String email, @RequestParam String name) throws StripeException {
        return stripeService.createCustomer(email, name);
    }

    @GetMapping(value = "/retrieve-customer")
    public ResponseEntity<DTOCustumer> retrieveCustomer(@RequestParam String cpf) throws StripeException {
        Customer customer = stripeService.retrieveCustomer(cpf);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new DTOCustumer(customer));
        }

    }

    @PostMapping("/addCard")
    public ResponseEntity<DTOCustumer> addCard(@RequestBody @Valid DTOADDCard dtoaddCard) throws StripeException {
        Customer customer = stripeService.addCardToCustomer(dtoaddCard);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(new DTOCustumer(customer));
        }
    }

    @PostMapping("/paytransaction")
    public ResponseEntity paytransaction(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {

        try {
       Transaction transaction =  transactionalService.payTransaction(paymentIntentDTO);
        return ResponseEntity.ok(new DTOPaymentDetails(transaction));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }



}











