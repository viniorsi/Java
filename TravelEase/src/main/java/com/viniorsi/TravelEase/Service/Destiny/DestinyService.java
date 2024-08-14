package com.viniorsi.TravelEase.Service.Destiny;

import com.viniorsi.TravelEase.Domain.Destiny.DTO.DTOListDestinations;
import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Destiny.Repository.DestinyRepository;
import com.viniorsi.TravelEase.Domain.Feedback.DTO.DTOGiveFeedback;
import com.viniorsi.TravelEase.Domain.Feedback.DTO.DTOfeedbackDetails;
import com.viniorsi.TravelEase.Domain.Feedback.Entity.Feedback;
import com.viniorsi.TravelEase.Domain.Feedback.Repository.FeedbackRepository;
import com.viniorsi.TravelEase.Domain.Travel.Repository.TravelRepository;
import com.viniorsi.TravelEase.Domain.User.Entity.User;
import com.viniorsi.TravelEase.Repository.User.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DestinyService {

    @Autowired
    DestinyRepository destinyRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    UserRespository userRepository;



    public Page<DTOListDestinations> listOfDestinations(Pageable pagination) {
        Page<Destiny> destinies = destinyRepository.findAll(pagination);
        return destinies.map(DTOListDestinations::new);
    }


    public DTOfeedbackDetails giveDestinyFeedback(DTOGiveFeedback dtoGiveFeedback) {
      User user = userRepository.getReferenceByCpf(dtoGiveFeedback.user_cpf());
      Destiny destiny = destinyRepository.getReferenceById(dtoGiveFeedback.destiny_id());
        Feedback feedback = new Feedback(user, destiny, dtoGiveFeedback.rating(), dtoGiveFeedback.rating_text());
        feedbackRepository.save(feedback);
        return new DTOfeedbackDetails(feedback);
    }

    public Page<DTOfeedbackDetails> destinationFeedbacksList(Long destinyId, Pageable pagination) {
        Page<Feedback> feedbacks = feedbackRepository.findAllByDestiny_Id(destinyId, pagination);
        return feedbacks.map(DTOfeedbackDetails::new);
    }
}
