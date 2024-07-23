package com.viniorsi.TravelEase.Controller;

import com.viniorsi.TravelEase.Domain.Destiny.DTO.DTOListDestinations;
import com.viniorsi.TravelEase.Domain.Ticket.DTO.DTOTicketPerson;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Service.Destiny.DestinyService;
import com.viniorsi.TravelEase.Service.Travel.TravelService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/travel")
@SecurityRequirement(name = "bearer-key")
public class TravelController {

    @Autowired
    TravelService travelService;

    @Transactional
    @PostMapping()
    public ResponseEntity addTravel(@Valid @RequestBody DTOTravelRequest dtoTravelRequest) {
        try {
            var response = travelService.addTravel(dtoTravelRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }




}
