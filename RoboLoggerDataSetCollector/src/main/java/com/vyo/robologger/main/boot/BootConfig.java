package com.vyo.robologger.main.boot;

import com.google.gson.Gson;
/**
 * Boot file object stores useful program variables
 */
import java.io.File;

public class BootConfig {

    private String TBA_KEY;

    private String DOWNLOAD_LOCATION;

    private String MYSQL_URL;

    private String MYSQL_USR;

    private String MYSQL_PASSWD;

    private BootBehaviour bootBehaviour;


    public BootConfig() {

    }

    public void setTBA_KEY(String TBA_KEY) {
        this.TBA_KEY = TBA_KEY;
    }

    public void setDOWNLOAD_LOCATION(String DOWNLOAD_LOCATION) {
        this.DOWNLOAD_LOCATION = DOWNLOAD_LOCATION;
    }

    public void setMYSQL_URL(String MYSQL_URL) {
        this.MYSQL_URL = MYSQL_URL;
    }

    public void setMYSQL_USR(String MYSQL_USR) {
        this.MYSQL_USR = MYSQL_USR;
    }

    public void setMYSQL_PASSWD(String MYSQL_PASSWD) {
        this.MYSQL_PASSWD = MYSQL_PASSWD;
    }

    public void setBootBehaviour(BootBehaviour bootBehaviour) {
        this.bootBehaviour = bootBehaviour;
    }

    public String getTBA_KEY() {
        return TBA_KEY;
    }

    public String getDOWNLOAD_LOCATION() {
        return DOWNLOAD_LOCATION;
    }

    public String getMYSQL_URL() {
        return MYSQL_URL;
    }

    public String getMYSQL_USR() {
        return MYSQL_USR;
    }

    public String getMYSQL_PASSWD() {
        return MYSQL_PASSWD;
    }

    public BootBehaviour getBootBehaviour() {
        return bootBehaviour;
    }

    public BootConfig rectifyDownloadLocationStr(){
        if (this.DOWNLOAD_LOCATION.toCharArray()[this.DOWNLOAD_LOCATION.length()-1] != File.separatorChar){
            this.DOWNLOAD_LOCATION = this.DOWNLOAD_LOCATION+File.separator;
        }
        return this;
    }
}
