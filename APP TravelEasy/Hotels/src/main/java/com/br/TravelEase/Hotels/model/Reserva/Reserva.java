package com.br.TravelEase.Hotels.model.Reserva;

import com.br.TravelEase.Hotels.feing.ReservaQuartoRequest;
import com.br.TravelEase.Hotels.model.Quarto.Quarto;
import com.br.TravelEase.Hotels.model.Reserva.Enum.StatusReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservas")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarto_id", nullable = false)
    private Quarto quarto;

    @Column(name = "check_in", nullable = false)
    private LocalDate checkIn;

    @Column(name = "check_out", nullable = false)
    private LocalDate checkOut;

    private Long usuario_id;

    private BigDecimal valor_total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusReserva statusReserva;
    private LocalDate created_at;
    private LocalDate updated_at;

    public Reserva(ReservaQuartoRequest request,Quarto quarto,BigDecimal valor) {
        this.quarto = quarto;
        this.checkIn = request.check_in();
        this.checkOut = request.check_out();
        this.usuario_id = request.userId();
        this.valor_total = valor;
        this.statusReserva = StatusReserva.PENDENTE;
    }

}
