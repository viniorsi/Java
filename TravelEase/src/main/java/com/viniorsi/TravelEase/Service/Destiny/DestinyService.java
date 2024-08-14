package com.viniorsi.TravelEase.Service.Destiny;

import com.viniorsi.TravelEase.Domain.Destiny.DTO.DTOListDestinations;
import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Destiny.Repository.DestinyRepository;
import com.viniorsi.TravelEase.Domain.Feedback.DTO.DTOGiveFeedback;
import com.viniorsi.TravelEase.Domain.Feedback.Entity.Feedback;
import com.viniorsi.TravelEase.Domain.Feedback.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DestinyService {

    @Autowired
    DestinyRepository destinyRepository;

    @Autowired
    FeedbackRepository feedbackRepository;




    public Page<DTOListDestinations> listOfDestinations(Pageable pagination) {
        Page<Destiny> destinies = destinyRepository.findAll(pagination);
        return destinies.map(DTOListDestinations::new);
    }


    public Feedback giveDestinyFeedback(DTOGiveFeedback dtoGiveFeedback) {
        Feedback feedback = new Feedback(dtoGiveFeedback.user_id(),dtoGiveFeedback.destiny_id(),dtoGiveFeedback.rating(),dtoGiveFeedback.rating_text(),dtoGiveFeedback.FeedBackDate());
    }
}
