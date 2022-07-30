package com.vyo.robologger.main.url;

import com.vyo.robologger.main.links.LinkExtractor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class IndexedURLWrapper {
    String team;
    String url;

    LinkExtractor linkExtractor;

    boolean valid = false;

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

    public boolean isValid() {
        return valid;
    }

    /**
     * Method validates a provided URL and returns whether it exists.
     * @return
     * @throws IOException
     */
    public void validateURL() throws IOException {
        URL urlObj = new URL(url);
        HttpURLConnection huc = null;
        try {
            huc = (HttpURLConnection) urlObj.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(huc.getResponseCode());
        if(HttpURLConnection.HTTP_OK == huc.getResponseCode()){
            System.out.println("URL:"+url+"  is valid");
            valid = true;
        }

        System.out.println("Invalid url URL:" + url);
        valid = false;
    }




}
