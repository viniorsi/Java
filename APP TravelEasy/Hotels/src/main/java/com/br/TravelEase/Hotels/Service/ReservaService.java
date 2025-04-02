package com.br.TravelEase.Hotels.Service;

import com.br.TravelEase.Hotels.feing.ReservaQuartoRequest;
import com.br.TravelEase.Hotels.feing.ReservaQuartoResponse;
import com.br.TravelEase.Hotels.model.Quarto.Quarto;
import com.br.TravelEase.Hotels.model.Quarto.Repository.QuartoRepository;
import com.br.TravelEase.Hotels.model.Reserva.Repository.Reservarepository;
import com.br.TravelEase.Hotels.model.Reserva.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;

@Service
public class ReservaService {
    @Autowired
    private Reservarepository reservaRepository;

    @Autowired
    private QuartoRepository quartoRepository;

    public ReservaQuartoResponse reservarQuarto(ReservaQuartoRequest request) {
        try {
            Quarto quarto = quartoRepository.findById(request.quartoId())
                    .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
            BigDecimal valor = calculateReserveValue(quarto, request);
            Reserva reserva = new Reserva(request, quarto, valor);
            reservaRepository.save(reserva);
            return new ReservaQuartoResponse(request, reserva);
        } catch (RuntimeException e) {
            throw new RuntimeException("erro ao salvar reserva do quarto de id: " + request.quartoId() + e);
        }


    }

    private BigDecimal calculateReserveValue(Quarto quarto, ReservaQuartoRequest request) {

        Long Days = ChronoUnit.DAYS.between(request.check_in(), request.check_out());
        BigDecimal valor = quarto.getPrecoPorNoite().multiply(BigDecimal.valueOf(Days));
        if (request.childCount() >=1){
        valor = addKidsDiscount(request,quarto.getPrecoPorNoite());
        }
        if (request.pet() > 0) {
            valor = addPetFees(request, quarto.getPrecoPorNoite());
        }
        return valor;
    }

    private BigDecimal addPetFees(ReservaQuartoRequest request, BigDecimal valor) {
        return valor.multiply(BigDecimal.valueOf(Math.pow(1.10, request.pet())));
    }

    private BigDecimal addKidsDiscount(ReservaQuartoRequest request, BigDecimal valorDiaria) {
        return valorDiaria.multiply(BigDecimal.valueOf(0.5)).multiply(BigDecimal.valueOf(request.childCount()));
    }

}
