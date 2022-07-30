package com.vyo.robologger.main.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//This class is used to store information about the progress of the program crawling through the webpages constructed by the application so that one can return
@Entity
public class ProcessingProgress {

    @Id
    private Long id;

    @Column
    private int teamNum;

    @Column
    private int year;

    @Column
    private String webpageURL;

    @Column
    private String robotPics;

    public ProcessingProgress() {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getWebpageURL() {
        return webpageURL;
    }

    public void setWebpageURL(String webpageURL) {
        this.webpageURL = webpageURL;
    }

    public String getRobotPics() {
        return robotPics;
    }

    public void setRobotPics(String robotPics) {
        this.robotPics = robotPics;
    }
}

