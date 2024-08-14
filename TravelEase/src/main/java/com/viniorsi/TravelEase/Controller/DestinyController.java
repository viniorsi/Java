package com.viniorsi.TravelEase.Controller;


import com.viniorsi.TravelEase.Domain.Destiny.DTO.DTOListDestinations;
import com.viniorsi.TravelEase.Domain.Feedback.DTO.DTOGiveFeedback;
import com.viniorsi.TravelEase.Domain.Feedback.Entity.Feedback;
import com.viniorsi.TravelEase.Service.Destiny.DestinyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destiny")
@SecurityRequirement(name = "bearer-key")
public class DestinyController {

    @Autowired
    DestinyService destinyService;

    @GetMapping()
    public ResponseEntity<Page<DTOListDestinations>> listDestinations(@PageableDefault(size = 10) Pageable pagination) {
        try {
            var page = destinyService.listOfDestinations(pagination);
            return ResponseEntity.ok(page);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/feedback")
    public ResponseEntity<Feedback> giveDestinyFeedback(
           @RequestBody DTOGiveFeedback dtoGiveFeedback
    ){

        try {
            var page = destinyService.giveDestinyFeedback(dtoGiveFeedback);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


}
