package com.br.TravelEase.Travel.feign;

import com.br.TravelEase.Travel.feign.model.Teste;
import feign.Headers;
import feign.RequestLine;

public interface HotelClient {
    @RequestLine("GET /Teste"),
    @Headers({"teste : teste"})
    Teste teste(
            Request request;
    );


}
