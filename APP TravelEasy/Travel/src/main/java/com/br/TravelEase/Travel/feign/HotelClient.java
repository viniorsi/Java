package com.br.TravelEase.Travel.feign;

import com.br.TravelEase.Travel.feign.response.HotelDetailResponse;
import feign.Param;
import feign.RequestLine;

public interface HotelClient {

    @RequestLine("GET /quartos/{quartoId}")
//    @Headers({"teste : teste"})
    HotelDetailResponse quartoDetails(
            @Param("quartoId") Long quartoId
    );


}
