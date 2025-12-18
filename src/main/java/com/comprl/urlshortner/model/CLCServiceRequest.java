package com.comprl.urlshortner.model;

public class CLCServiceRequest {

    private String domain = "clc.is";
    private String target_url;


    public CLCServiceRequest() {}

    public CLCServiceRequest(String target_url){
        this.domain = "clc.is";
        this.target_url = target_url;
    }

    public CLCServiceRequest(String domain, String target_url) {
        this.domain = domain;
        this.target_url = target_url;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


//    {
//        "domain": "clc.is",
//        "target_url": "https://example.com",
//        "slug": "custom", // optional
//        "expired_url": "https://expired.example.com", // optional
//        "expired_hours": 48 // optional (0 = no expiration)
//    }
}
