package com.br.TravelEase.Hotels.feing;

import java.util.List;

public record DisponibilidadeResponse(
        private boolean disponivel,
        private List<QuartoResumoResponse> quartosDisponiveis,
        private String mensagem
) {
}
