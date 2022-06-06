package com.vyo.robologger.main.url;

import com.vyo.robologger.main.links.LinkExtractor;

public class IndexedURLWrapper {
    String team;
    String url;

    LinkExtractor linkExtractor;

    public IndexedURLWrapper(String team, String url) {
        this.team = team;
        this.url = url;
    }

    public IndexedURLWrapper(String team, String url, LinkExtractor linkExtractor) {
        this.team = team;
        this.url = url;
        this.linkExtractor = linkExtractor;
    }

    public String getTeam() {
        return team;
    }

    public String getUrl() {
        return url;
    }

    public LinkExtractor getLinkExtractor() {
        return linkExtractor;
    }

    public void setLinkExtractor(LinkExtractor linkExtractor) {
        this.linkExtractor = linkExtractor;
    }
}
