package com.comprl.urlshortner.config;


import com.google.genai.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GeminiConfig {

    @Value("${GEMINI_API_KEY}")
    private String GEMINI_API_KEY;


    @Bean
    public Client GeminiClient(){
        return Client.builder().apiKey(GEMINI_API_KEY).build();

    }

}
