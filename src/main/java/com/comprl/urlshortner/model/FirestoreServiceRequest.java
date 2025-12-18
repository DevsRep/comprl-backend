package com.comprl.urlshortner.model;

import java.util.Date;

public class FirestoreServiceRequest {

    private String url;
    private String password = "";
    private int clickCount;
    private Date date;

    public FirestoreServiceRequest(){}

    public FirestoreServiceRequest(String url, int clickCount, Date date){
        this.url = url;
        this.password = "";
        this.clickCount = clickCount;
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
