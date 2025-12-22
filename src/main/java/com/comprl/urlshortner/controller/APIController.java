package com.comprl.urlshortner.controller;


import com.comprl.urlshortner.model.*;
import com.comprl.urlshortner.service.ExternalShorteningService;
import com.comprl.urlshortner.service.FirestoreService;
import com.comprl.urlshortner.service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "chrome-extension://miafpdbdgdcnnkobgmhplbepagmncplc")
public class APIController {

    private final long START_TIME = System.currentTimeMillis();

    @Autowired
    private ExternalShorteningService externalShorteningService;

    @Autowired
    private FirestoreService firestoreService;

    @Autowired
    private GeminiService geminiService;

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
    public Url shortenUsingFirestore(@RequestBody Url urlEntity){

        return firestoreService.storeURL(urlEntity);

    }


    @PostMapping("/shortenai")
    public Url shortenUsingGemini(@RequestBody Url urlEntity){

        String slug = geminiService.getURLShortDescription(urlEntity.getLongUrl());

        return firestoreService.storeURLwAI(urlEntity, slug);

    }


    @PostMapping("/shortencustom")
    public Url shortenUsingCustom(@RequestBody Url urlEntity){
        return firestoreService.storeURLwCustom(urlEntity, urlEntity.getExtra("customSlug"));
    }


    @GetMapping("/urldesc")
    public String getURLDescription(@RequestBody String url) {
        return geminiService.getURLShortDescription(url);
    }

    @GetMapping("/u/{urlId}")
    public Map<String, String > getURL(@PathVariable String urlId){
        Map<String, String > map = new HashMap<>();
        map.put("url", firestoreService.getUrl(urlId));
        return map;
    }


    @GetMapping("/health")
    public ResponseEntity<Health> getHealth() {

        return ResponseEntity.ok(new Health("UP", System.currentTimeMillis() - START_TIME));

    }

}
