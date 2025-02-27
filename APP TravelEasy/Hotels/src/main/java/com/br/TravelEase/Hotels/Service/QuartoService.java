package com.br.TravelEase.Hotels.Service;

import com.br.TravelEase.Hotels.model.Quarto.Enum.TipoQuarto;
import com.br.TravelEase.Hotels.model.Quarto.Quarto;
import com.br.TravelEase.Hotels.model.Quarto.Repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuartoService {


    @Autowired
    QuartoRepository quartoRepository;

    public List<Quarto> buscarQuartosDisponiveis(Long hotelId, LocalDate checkIn, LocalDate checkOut) {

        List<Quarto> quartosDisponiveis;
        quartosDisponiveis = quartoRepository.findQuartosDisponiveisPorPeriodo(hotelId, checkIn, checkOut);
        return quartosDisponiveis;

    }

    public Quarto buscarPorId(Long quartoId) {
        Quarto quarto =  quartoRepository.findQuartoById(quartoId);
        return quarto;
    }




}
