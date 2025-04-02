package com.br.TravelEase.Travel.feign.response;

import java.util.UUID;

public record TransactionResponse(
        UUID Transaction_id,
        int points
) {
}
