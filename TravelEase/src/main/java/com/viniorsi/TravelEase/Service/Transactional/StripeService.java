package com.viniorsi.TravelEase.Service.Transactional;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.param.*;
import com.viniorsi.TravelEase.Domain.Transaction.DTO.PaymentIntentDTO;
import com.viniorsi.TravelEase.Domain.User.DTO.DTOADDCard;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Domain.User.Repository.UserRespository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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


    public PaymentIntent createPaymentIntent(String custumer,Double amount) throws StripeException {
        long amountInCents = (long) (amount * 100);
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setCustomer(custumer)
                .setAmount(amountInCents)
                .setCurrency("brl")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                .setEnabled(true)
                                .setAllowRedirects(PaymentIntentCreateParams.AutomaticPaymentMethods.AllowRedirects.NEVER)
                                .build()
                )
                .setConfirm(false)
                .build();

        return PaymentIntent.create(params);
    }

    public PaymentIntent confirmPayment(PaymentIntentDTO paymentIntentDTO) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentDTO.paymentIntentId());

        PaymentIntentConfirmParams.Builder confirmParamsBuilder = PaymentIntentConfirmParams.builder();

        switch (paymentIntentDTO.paymentMethodType()) {
            case CARD:
                confirmParamsBuilder.setPaymentMethod(paymentIntentDTO.paymentMethodId());
                break;

            case PIX:
                confirmParamsBuilder.setPaymentMethod("pix"); // Use "pix" como o ID do método de pagamento
                confirmParamsBuilder.setPaymentMethodOptions(
                        PaymentIntentConfirmParams.PaymentMethodOptions.builder()
                                .setPix(
                                        PaymentIntentConfirmParams.PaymentMethodOptions.Pix.builder()
                                                .build()
                                )
                                .build()
                );
                break;

            case BOLETO:

                confirmParamsBuilder.setPaymentMethodOptions(
                        PaymentIntentConfirmParams.PaymentMethodOptions.builder()
                                .setBoleto(
                                        PaymentIntentConfirmParams.PaymentMethodOptions.Boleto.builder()
                                                .build()
                                )
                                .build()
                );
                break;

            default:
                throw new IllegalArgumentException("Método de pagamento não suportado");
        }

        PaymentIntent confirmedPaymentIntent = paymentIntent.confirm(confirmParamsBuilder.build());

        if (paymentIntentDTO.paymentMethodType() == PaymentIntentDTO.PaymentMethodType.PIX) {
            String pixCode = getPixPaymentCode(confirmedPaymentIntent.getId());
            System.out.println("Código QR Pix: " + pixCode);
        }

        return confirmedPaymentIntent;
    }

    public String getPixPaymentCode(String paymentIntentId) throws StripeException {
        PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
        if (paymentIntent.getNextAction() != null && paymentIntent.getNextAction().getPixDisplayQrCode() != null) {
            return paymentIntent.getNextAction().getPixDisplayQrCode().getData();
        } else {
            throw new IllegalStateException("A ação Pix ou o código QR não está disponível para este PaymentIntent");
        }
    }



}
