package com.br.TravelEase.Travel.Controller;

import com.br.TravelEase.Travel.Domain.Travel.DTO.DTOTravelRequest;
import com.br.TravelEase.Travel.Service.Travel.TravelService;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Service.Travel.TravelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel")
@SecurityRequirement(name = "bearer-key")
public class TravelController  {

    @Autowired
    TravelService travelService;

    @Transactional
    @PostMapping()
    public ResponseEntity addTravel(@Valid @RequestBody DTOTravelRequest dtoTravelRequest) {
        try {
            var response = travelService.addTravel(dtoTravelRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }




}
