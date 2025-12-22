package com.comprl.urlshortner.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinkDir {

    private String userId = "default";
    private String linkDirID;
    private String linkDirName;
    private String linkDirDesc;
    private List<Map<String, String>> links = new ArrayList<>(0);

    public LinkDir(){}

    public LinkDir(String userId, String LinkDirID, String LinkDirName, String LinkDirDesc, List<Map<String, String>> links) {
        this.userId = userId;
        this.linkDirID = LinkDirID;
        this.linkDirName = LinkDirName;
        this.linkDirDesc = LinkDirDesc;
        this.links = links;
    }

    public String getLinkDirID() {
        return linkDirID;
    }

    public void setLinkDirID(String linkDirID) {
        this.linkDirID = linkDirID;
    }

    public String getLinkDirName() {
        return linkDirName;
    }

    public void setLinkDirName(String linkDirName) {
        this.linkDirName = linkDirName;
    }

    public String getLinkDirDesc() {
        return linkDirDesc;
    }

    public void setLinkDirDesc(String linkDirDesc) {
        this.linkDirDesc = linkDirDesc;
    }

    public List<Map<String, String>> getLinks() {
        return links;
    }

    public void setLinks(List<Map<String, String>> links) {
        this.links = links;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
