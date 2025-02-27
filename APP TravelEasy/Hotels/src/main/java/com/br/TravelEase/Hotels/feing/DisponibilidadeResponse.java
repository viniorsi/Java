package com.br.TravelEase.Hotels.feing;

import java.util.List;

public record DisponibilidadeResponse(
         boolean disponivel,
         List<QuartoResumoResponse> quartosDisponiveis,
         String mensagem
) {
}
