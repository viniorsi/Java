package com.br.TravelEase.Travel.Controller;


import com.br.TravelEase.Travel.Domain.Destiny.DTO.DTOListDestinations;
import com.br.TravelEase.Travel.Service.Destiny.DestinyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/feedback")
//    public ResponseEntity<DTOfeedbackDetails> giveDestinyFeedback(
//           @RequestBody DTOGiveFeedback dtoGiveFeedback
//    ){
//
//        try {
//            var feedback = destinyService.giveDestinyFeedback(dtoGiveFeedback);
//            return ResponseEntity.ok(feedback);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }
//    }
//
//    @GetMapping("/{id}/feedbacks")
//    public Page<DTOfeedbackDetails> listFeedbacks(@PathVariable Long id, Pageable pagination) {
//        return destinyService.destinationFeedbacksList(id, pagination);
//    }


}
