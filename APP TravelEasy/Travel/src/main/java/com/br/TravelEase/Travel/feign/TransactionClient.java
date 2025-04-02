package com.br.TravelEase.Travel.feign;

import com.br.TravelEase.Travel.feign.request.TransactionCreationRequest;
import com.br.TravelEase.Travel.feign.response.TransactionResponse;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;

public interface TransactionClient {

    @RequestLine("Post /transaction/create")
    TransactionResponse transactionCreation(@RequestBody TransactionCreationRequest request);

}
