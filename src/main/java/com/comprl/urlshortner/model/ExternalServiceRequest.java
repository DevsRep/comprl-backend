package com.comprl.urlshortner.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ExternalServiceRequest {

    @JsonAlias({"long_url"})
    private String longUrl;

    private CLCServiceRequest input;

    @JsonAlias({"url", "short_url"})
    private String shortUrl;

    @JsonAlias({"slug", "alias"})
    private String slug;

    private boolean is_generated;

    public CLCServiceRequest getInput() {
        return input;
    }

    public void setInput(CLCServiceRequest input) {
        this.input = input;
    }

    private long created_at;
    private String owner_id;
    private String private_stats;
    private String status;

    public ExternalServiceRequest(){}

    public  ExternalServiceRequest(CLCServiceRequest input, String shortUrl, String slug, boolean is_generated){
        this.input = input;
        this.shortUrl = shortUrl;
        this.slug = slug;
        this.is_generated = is_generated;
    }

    public ExternalServiceRequest(String longUrl, String shortUrl, String slug, long created_at, String owner_id, String private_stats, String status){
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.slug = slug;
        this.created_at = created_at;
        this.owner_id = owner_id;
        this.private_stats = private_stats;
        this.status = status;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isIs_generated() {
        return is_generated;
    }

    public void setIs_generated(boolean is_generated) {
        this.is_generated = is_generated;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getPrivate_stats() {
        return private_stats;
    }

    public void setPrivate_stats(String private_stats) {
        this.private_stats = private_stats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
