package com.br.TravelEase.Transactions.controller;

import com.br.TravelEase.Transactions.Service.TransactionService;
import com.br.TravelEase.Transactions.feign.request.PaymentRequest;
import com.br.TravelEase.Transactions.model.Transaction;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/pay")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Transaction transaction = service.processPayment(paymentRequest);
            return ResponseEntity.ok(transaction);
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable String userId) {
        return service.getUserTransactions(userId);
    }

}
