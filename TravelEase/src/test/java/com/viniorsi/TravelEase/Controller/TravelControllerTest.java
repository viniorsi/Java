package com.viniorsi.TravelEase.Controller;


import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelRequest;
import com.viniorsi.TravelEase.Domain.Travel.DTO.DTOTravelResponseWithoutHotel;
import com.viniorsi.TravelEase.Service.Travel.TravelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TravelControllerTest {

    @InjectMocks
    TravelController travelController;

    @Mock
    TravelService travelService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSuccessfulCreationTravel() throws Exception {
        // Dados de entrada para a requisição
        DTOTravelRequest travelRequest = new DTOTravelRequest(
                "12345678909",
                1L,
                "São Paulo",
                null,
                2,
                0,
                0,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                true,
                2,
                Collections.emptyList()
        );

        // Retorno esperado do serviço
        DTOTravelResponseWithoutHotel travelResponse = new DTOTravelResponseWithoutHotel(
                "12345678909",
                "Rio de Janeiro",
                "São Paulo",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                true,
                2,
                100,
                500.00,
                1L,
                "teste"

        );


        when(travelService.addTravel(any(DTOTravelRequest.class))).thenReturn(travelResponse);


        ResponseEntity response = travelController.addTravel(travelRequest);

        // Verificando o resultado
        assertEquals(200, response.getStatusCodeValue()); // Verifica o status da resposta (200 OK)
        assertEquals(travelResponse, response.getBody()); // Verifica se a resposta contém o objeto esperado
    }

}
