package com.br.TravelEase.Hotels.Controller;

import com.br.TravelEase.Hotels.Service.QuartoService;
import com.br.TravelEase.Hotels.Service.ReservaService;
import com.br.TravelEase.Hotels.feing.*;
import com.br.TravelEase.Hotels.model.Quarto.Quarto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/hotel")
@SecurityRequirement(name = "bearer-key")
public class HotelController {

    @Autowired
    QuartoService quartoService;

    @Autowired
    ReservaService reservaService;

    @GetMapping("/{hotelId}/availability")
    public ResponseEntity<DisponibilidadeResponse> checkAvailability(
            @PathVariable Long hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut
    ) {
        List<Quarto> quartosDisponiveis = quartoService.buscarQuartosDisponiveis(hotelId, checkIn, checkOut);

        if (quartosDisponiveis.isEmpty()) {
            return ResponseEntity.ok(new DisponibilidadeResponse(false, Collections.emptyList(), "Nenhum quarto disponível."));
        }


        List<QuartoResumoResponse> resumos = quartosDisponiveis.stream()
                .map(quarto -> new QuartoResumoResponse(
                        quarto.getId(),
                        quarto.getTipo(),
                        quarto.getPrecoPorNoite()
                ))
                .toList();

        return ResponseEntity.ok(new DisponibilidadeResponse(true, resumos, null));
    }

    @GetMapping("/quartos/{quartoId}")
    public ResponseEntity<QuartoDetalhesResponse> buscarDetalhesQuarto(
            @PathVariable Long quartoId
    ) {
        Quarto quarto = quartoService.buscarPorId(quartoId);
        return ResponseEntity.ok(new QuartoDetalhesResponse(quarto));
    }

    @PostMapping("/reserva")
    public ResponseEntity<ReservaQuartoResponse> reservarQuarto(
            @RequestBody ReservaQuartoRequest request
    ) {
        ReservaQuartoResponse response =  reservaService.reservarQuarto(request);
        return ResponseEntity.ok(response);
    }
}
