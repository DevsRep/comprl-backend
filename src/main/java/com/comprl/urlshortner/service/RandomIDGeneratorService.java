package com.comprl.urlshortner.service;


import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class RandomIDGeneratorService {

    private static final String CHARSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final SecureRandom random = new SecureRandom();

    public String generateRandomID() {

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<7; i++){
            sb.append(CHARSET.charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

}
