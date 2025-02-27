package com.br.TravelEase.Travel.feign;

import com.br.TravelEase.Travel.feign.request.HotelReservaRequest;
import com.br.TravelEase.Travel.feign.response.HotelDetailResponse;
import feign.Param;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

public interface HotelClient {

    @RequestLine("Post hotel/reserva")
//    @Headers({"teste : teste"})
    HotelDetailResponse reservaHotel(
            @RequestBody HotelReservaRequest hotelReservaRequest
    );


}
