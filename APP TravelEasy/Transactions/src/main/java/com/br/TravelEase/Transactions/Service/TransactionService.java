package com.br.TravelEase.Transactions.Service;

import com.br.TravelEase.Transactions.feign.request.PaymentRequest;
import com.br.TravelEase.Transactions.model.Transaction;
import com.br.TravelEase.Transactions.model.repository.TransactionRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {


    @Autowired
    private TransactionRepository repository;



    public Transaction processPayment(PaymentRequest paymentRequest) throws StripeException {
        // Cria a transação a partir do DTO
        Transaction transaction = new Transaction(paymentRequest);

        Map<String, Object> params = new HashMap<>();
        params.put("amount", (int) (paymentRequest.amount() * 100));
        params.put("currency", paymentRequest.currency().toLowerCase());
        params.put("automatic_payment_methods", Map.of(
                "enabled", true,
                "allow_redirects", "never"
        ));
        params.put("confirm", true);

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Atualiza o status da transação com base na resposta do Stripe
        transaction.setStatus(paymentIntent.getStatus().toUpperCase());
        transaction.setStripePaymentId(paymentIntent.getId()); // Salva o ID do Stripe
        transaction.setCreatedAt(LocalDateTime.now());

        return repository.save(transaction);
    }

    public List<Transaction> getUserTransactions(String userId) {
        return repository.findByUserId(userId);
    }
}
