package com.br.TravelEase.Hotels.feing;

import com.br.TravelEase.Hotels.model.Reserva.Enum.StatusReserva;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;

public record ReservaQuartoRequest(
        Long userId,
        Long quartoId,
        int adultCount,
        int childCount,
        int pet,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate check_in,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate check_out
        ) {
}
