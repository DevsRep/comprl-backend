package com.comprl.urlshortner.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class Url {


    @JsonAlias({"long_url", "longUrl", "url"})
    private String longUrl;
    private String shortUrl;

    private Map<String, Object> extra = new HashMap<>();

    @JsonAnySetter
    public void setExtra(String key, Object value) {
        extra.put(key, value);
    }

    public Object getExtra(String key) {
        return extra.get(key);
    }

    public Url(){}

    public Url(String longUrl){
        this.longUrl = longUrl;
    }

    public Url(String longUrl, String shortUrl) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}