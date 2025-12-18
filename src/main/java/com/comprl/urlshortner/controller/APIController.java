package com.comprl.urlshortner.controller;


import com.comprl.urlshortner.model.*;
import com.comprl.urlshortner.service.ExternalShorteningService;
import com.comprl.urlshortner.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "chrome-extension://miafpdbdgdcnnkobgmhplbepagmncplc")
public class APIController {

    private long START_TIME = System.currentTimeMillis();

    @Autowired
    private ExternalShorteningService externalShorteningService;

    @Autowired
    private FirestoreService firestoreService;

    @PostMapping("/spoo/shorten")
    public Mono<Url> shortenUsingSpoo(@RequestBody Url urlEntity){

        SpooServiceRequest spooServiceRequest = new SpooServiceRequest(urlEntity.getLongUrl());

        return externalShorteningService.sendPostRequesttoSpoo(spooServiceRequest)
                .elementAt(0)
                .map(externalServiceRequest -> {
                    urlEntity.setShortUrl(externalServiceRequest.getShortUrl());
                    return urlEntity;
                });

    }

    @PostMapping("/clc/shorten")
    public Mono<Url> shortenUsingClc(@RequestBody Url urlEntity){

        CLCServiceRequest clcServiceRequest = new CLCServiceRequest(urlEntity.getLongUrl());

        return externalShorteningService.sendPostRequesttoCLC(clcServiceRequest)
                .elementAt(0)
                .map(externalServiceRequest -> {
                    urlEntity.setShortUrl(externalServiceRequest.getShortUrl());
                    return urlEntity;
                });
    }

    @PostMapping("/shorten")
    public Url shortenUsingFirestore(@RequestBody Url urlEntity) throws Exception{

        return firestoreService.storeUrl(urlEntity);

    }


    @GetMapping("/health")
    public ResponseEntity<Health> getHealth() {

        return ResponseEntity.ok(new Health("UP", System.currentTimeMillis() - START_TIME));

    }

}
