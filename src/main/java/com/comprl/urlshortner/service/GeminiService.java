package com.comprl.urlshortner.service;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {


    @Autowired
    private Client client;

    public String getURLShortDescription(String url){
        String prompt = """
                Generate EXACTLY two words that describe this URL.
                With a random 2 digit alphanumeric at the end.
                Words spilt by hypen including Random ID. No explanation.
                URL: """ + url;
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        prompt,
                        null);

        return response.text();
    }

}
