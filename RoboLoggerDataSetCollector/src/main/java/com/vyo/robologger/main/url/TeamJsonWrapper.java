package com.vyo.robologger.main.url;

import org.json.JSONObject;

/**
 * Json Wrapper that stores team num and the Json Object for future retrieval
 */
public class TeamJsonWrapper {

    String teamNum;
    JSONObject teamJsonObject;

    public TeamJsonWrapper(String teamNum, JSONObject teamJsonObject) {
        this.teamNum = teamNum;
        this.teamJsonObject = teamJsonObject;
    }

    public String getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(String teamNum) {
        this.teamNum = teamNum;
    }

    public JSONObject getTeamJsonObject() {
        return teamJsonObject;
    }

    public void setTeamJsonObject(JSONObject teamJsonObject) {
        this.teamJsonObject = teamJsonObject;
    }
}
