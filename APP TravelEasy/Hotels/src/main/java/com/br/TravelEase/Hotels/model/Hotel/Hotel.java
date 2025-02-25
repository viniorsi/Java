package com.br.TravelEase.Hotels.model.Hotel;

import com.br.TravelEase.Hotels.model.Quarto.Quarto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hoteis")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long destinoId; // ID do destino (referência externa)

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String localizacao; // Ex: "São Paulo, Brasil"

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Quarto> quartos; // Lista de quartos do hotel


}
