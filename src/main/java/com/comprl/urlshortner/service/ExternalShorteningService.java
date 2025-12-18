package com.comprl.urlshortner.service;


import com.comprl.urlshortner.model.CLCServiceRequest;
import com.comprl.urlshortner.model.ExternalServiceRequest;
import com.comprl.urlshortner.model.SpooServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ExternalShorteningService {


    @Autowired
    private WebClient webClient;

    public Flux<ExternalServiceRequest> sendPostRequesttoSpoo(SpooServiceRequest bodyContent){

        return webClient.post()
                    .uri("https://spoo.me/api/v1/shorten")
                    .bodyValue(bodyContent)
                    .retrieve()
                    .bodyToFlux(ExternalServiceRequest.class);

    }

    public Flux<ExternalServiceRequest> sendPostRequesttoCLC(CLCServiceRequest bodyContent){

        return webClient.post()
                .uri("https://clc.is/api/links")
                .bodyValue(bodyContent)
                .retrieve()
                .bodyToFlux(ExternalServiceRequest.class);

    }

}
