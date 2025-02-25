package com.br.TravelEase.Hotels.model.Quarto;

import com.br.TravelEase.Hotels.model.Quarto.Enum.TipoQuarto;
import com.br.TravelEase.Hotels.model.Quarto.Quarto.Enum.TipoQuarto;
import com.br.TravelEase.Hotels.model.Hotel.Hotel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quartos")
public class Quarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel; // Relacionamento com o hotel (dentro do mesmo microsserviço)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoQuarto tipo; // Ex: STANDARD, LUXO, SUITE

    @Column(nullable = false)
    private String numero; // Ex: "101A", "202B" (opcional se não quiser identificar fisicamente)

    @Column(name = "preco_por_noite", nullable = false)
    @Positive(message = "O preço deve ser positivo")
    private BigDecimal precoPorNoite;

    @Column(nullable = false)
    @PositiveOrZero(message = "A capacidade deve ser zero ou mais")
    private Integer capacidade; // Número máximo de hóspedes

    @ElementCollection
    @CollectionTable(name = "quarto_comodidades", joinColumns = @JoinColumn(name = "quarto_id"))
    private List<String> comodidades; // Ex: ["Wi-Fi", "TV", "Ar Condicionado"]

    @Column(name = "aceita_pet")
    private boolean aceitaPet;

    @Column(name = "fotos")
    @ElementCollection
    private List<String> fotos; // URLs das fotos do quarto

    // Campo para controle de concorrência (Otimistic Locking)
    @Version
    private Long version;
}
