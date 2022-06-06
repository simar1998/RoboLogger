package com.vyo.robologger.main.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 * Dataset raw data
 */
@Entity
public class ImageData {

    @Id
    private Long id;

    @Column
    private int teamNum;

    @Column
    private String url;

    @Column
    private String imageLocation;

    @Column
    private String retrievalTime;

    public ImageData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getRetrievalTime() {
        return retrievalTime;
    }

    public void setRetrievalTime(String retrievalTime) {
        this.retrievalTime = retrievalTime;
    }
}
