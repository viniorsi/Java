package com.br.TravelEase.Travel.Controller;

import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.br.TravelEase.Travel.Service.Travel.TravelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel")
@SecurityRequirement(name = "bearer-key")
public class TravelController  {

    @Autowired
    TravelService travelService;

    @Transactional
    @PostMapping("create")
    public ResponseEntity addTravel(@Valid @RequestBody DTOTravelRequest dtoTravelRequest) {
        try {
            var response = travelService.addTravel(dtoTravelRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }


    @Transactional
    @PutMapping("consult/{travelId}")
    public ResponseEntity<?> consultTravel(@PathVariable Long travelId) {
        try {
            var response = travelService.consultTravel(travelId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @Transactional
    @PutMapping("cancel/{travelId}")
    public ResponseEntity<?> cancelTravel(@PathVariable Long travelId) {
        try {
            var response = travelService.cancelTravel(travelId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }





}
