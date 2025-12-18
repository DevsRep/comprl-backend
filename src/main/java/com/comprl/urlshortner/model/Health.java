package com.comprl.urlshortner.model;

public class Health {

    private String status;
    private long uptime;

    public Health(String status, long uptime) {
        this.status = status;
        this.uptime = uptime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }
}
