package com.br.TravelEase.Hotels.model.Quarto.Repository;

import com.br.TravelEase.Hotels.model.Quarto.Quarto;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface QuartoRepository extends JpaRepository<Quarto, Long> {

    @Query("SELECT q FROM Quarto q " +
            "WHERE q.hotel.id = :hotelId " +
            "AND q.id NOT IN (" +
            "   SELECT r.quarto.id FROM Reserva r " +
            "   WHERE (r.checkIn <= :checkOut AND r.checkOut >= :checkIn)" +
            ")")
    List<Quarto> findQuartosDisponiveisPorPeriodo(
            @Param("hotelId") Long hotelId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut
    );


    Quarto findQuartoById(
            @Param("quartoId") Long quartoId
    );
}
