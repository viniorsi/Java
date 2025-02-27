package com.br.TravelEase.Hotels.feing;

import com.br.TravelEase.Hotels.model.Reserva.Enum.StatusReserva;
import com.br.TravelEase.Hotels.model.Reserva.Reserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaQuartoResponse(

      String  numeroQuarto,
      LocalDate check_in,
      LocalDate check_out,
      BigDecimal valor,
      StatusReserva status

) {

    public ReservaQuartoResponse(ReservaQuartoRequest request, Reserva reserva) {
        this(reserva.getQuarto().getNumero(),request.check_in(),request.check_out(),reserva.getValor_total(),reserva.getStatusReserva());
    }
}
