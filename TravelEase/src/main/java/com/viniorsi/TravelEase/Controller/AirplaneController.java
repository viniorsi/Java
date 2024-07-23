package com.viniorsi.TravelEase.Controller;

import com.viniorsi.TravelEase.Domain.Airplane.Airplane.DTO.DTOAirplaneSeats;
import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Entity.Airplane;
import com.viniorsi.TravelEase.Domain.Airplane.Airplane.Repository.AirplaneRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airplane")
@SecurityRequirement(name = "bearer-key")
public class AirplaneController {

    @Autowired
    AirplaneRepository repository;


    @GetMapping("/{id}")
    public ResponseEntity<Page<DTOAirplaneSeats>> listSeats(@PageableDefault(size = 10) Pageable pagination, @PathVariable Long id) {
        try {
            Page<Airplane> airplanes = repository.findById(id,pagination);
            Page<DTOAirplaneSeats> dtoAirplanes = airplanes.map(DTOAirplaneSeats::new);
            return ResponseEntity.ok(dtoAirplanes);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception or use a logger
            return ResponseEntity.badRequest().body(null);
        }
    }
}
