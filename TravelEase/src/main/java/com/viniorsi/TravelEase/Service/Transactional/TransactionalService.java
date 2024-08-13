package com.viniorsi.TravelEase.Service.Transactional;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.viniorsi.TravelEase.Domain.Hotels.Entity.Hotels;
import com.viniorsi.TravelEase.Domain.Ticket.Entity.Ticket;
import com.viniorsi.TravelEase.Domain.Ticket.TicketRepository;
import com.viniorsi.TravelEase.Domain.Transaction.DTO.PaymentIntentDTO;
import com.viniorsi.TravelEase.Domain.Transaction.Entity.Transaction;
import com.viniorsi.TravelEase.Domain.Transaction.Enums.StatusPaymentEnum;
import com.viniorsi.TravelEase.Domain.Transaction.Repository.TransactionalRepository;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Domain.Travel.Entity.Travel;
import com.viniorsi.TravelEase.Domain.Travel.Repository.TravelRepository;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Service.Ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionalService {

    @Autowired
    TransactionalRepository transactionalRepository;

    @Autowired
    TicketService ticketService;

    @Autowired
    StripeService stripeService;

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Transactional
    public Transaction payTransaction(PaymentIntentDTO paymentIntentDTO) throws StripeException {

        try {
            Transaction transaction = transactionalRepository.findTransactionalByStripeTransactionId(paymentIntentDTO.paymentIntentId());

            if (transaction != null) {
                stripeService.confirmPayment(paymentIntentDTO);

                transaction.setStatusPayment(StatusPaymentEnum.P);
                transaction.setTransactionDate(LocalDateTime.now());
                transactionalRepository.save(transaction);


                Travel travel = travelRepository.getReferenceById(transaction.getTravel().getId());
                List<Ticket> tickets = ticketRepository.findAllByTravelId(transaction.getTravel().getId());
                ticketService.setStatusToActive(tickets);

                return transaction;
            } else {
                throw new RuntimeException("Transação não encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar o pagamento: " + e.getMessage());
            throw new RuntimeException("Erro ao processar o pagamento, a operação foi abortada.", e);
        }


    }

    public void addTransaction(Transaction transaction, DTOTravelRequest dtoTravelRequest, User user, Travel travel) throws StripeException {

        List<Ticket> tickets = ticketService.ticketCreation(dtoTravelRequest, user, travel);

        PaymentIntent paymentIntent = stripeService.createPaymentIntent(transaction.getUser().getId_customer_stripe(), transaction.getValue());

        transaction.setStripe_transaction_id(paymentIntent.getId());


        transactionalRepository.save(transaction);
    }

    public int generatePoints(Double value) {
        value = value * 0.2;
        return value.intValue();
    }

    public Double getPriceWithHotel(DTOTravelRequest travelRequest, Double dailyValueDestiny, Hotels hotel) {

        var Days = ChronoUnit.DAYS.between(travelRequest.departureDate(), travelRequest.returnDate());
        var value = (dailyValueDestiny * Days) + hotel.getValue() * travelRequest.adults_count();
        if (travelRequest.pet() > 0) {
            value += hotel.getValue() * travelRequest.pet() * 3;
        }
        if (travelRequest.kids_count() >= 2) {
            value += (hotel.getValue() * travelRequest.kids_count()) / 2;
        }
        if (travelRequest.isRoundTrip()) {
            value -= -value * 0.1;
        }
        return value;

    }

    public Double getPriceWithoutHotel(DTOTravelRequest travelRequest, Double dailyValueDestiny) {

        var Days = ChronoUnit.DAYS.between(travelRequest.departureDate(), travelRequest.returnDate());
        var value = (dailyValueDestiny * Days) * travelRequest.ticketscount();
        if (travelRequest.pet() > 0) {
            value += 100 * travelRequest.pet();
        }
        if (travelRequest.isRoundTrip()) {
            value -= value * 0.1;
        }
        return value;

    }


}
