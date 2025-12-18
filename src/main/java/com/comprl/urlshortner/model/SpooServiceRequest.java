package com.comprl.urlshortner.model;

public class SpooServiceRequest {

    private String long_url;

    public SpooServiceRequest(){}

    public SpooServiceRequest(String long_url) {
        this.long_url = long_url;
    }

    public String getLong_url() {
        return long_url;
    }

    public void setLong_url(String long_url) {
        this.long_url = long_url;
    }
}
