package com.vyo.robologger.main.url;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vyo.robologger.main.Main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class constructs the URL strings that are to be submitted to LinkExtractor and LinkSearch by The Blue Alliance API Invocation
 */
public class UrlConstructor {

    private String allTeamsStr;
    private ArrayList<TeamJsonWrapper> teams = new ArrayList<>();

    private ArrayList<IndexedURLWrapper> indexedSearchURIs = new ArrayList<>();

    private final String TBA_BASE_CONSTRUCT_URL = "https://www.thebluealliance.com/team/";
    /**
     * Extracts the team numbers from the response string and appends them onto a list
     */
    private void extractTeams(){
        assert !allTeamsStr.isEmpty() : "All teams api call response empty";

        JSONArray jsonArray = new JSONArray(allTeamsStr);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            teams.add(new TeamJsonWrapper(jsonobject.getInt("team_number")+"",jsonobject));
        }
    }

    /**
     * Gets the response string for all teams
     * @throws IOException
     */
    private void getAllTeams() throws IOException, UnirestException {

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://www.thebluealliance.com/api/v3/teams/all")
                .header("X-TBA-Auth-Key", Main.config.getTBA_KEY())
                .asString();


        String responseStr = response.getBody();
        System.out.println(responseStr);
        this.allTeamsStr = responseStr;
    }

    /**
     * Method validates a provided URL and returns whether it exists.
     * @param url
     * @return
     */
    private boolean validateURL(String url) throws IOException {
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
            return true;
        }

        System.out.println("Invalid url URL:" + url);
        return false;
    }

    /**
     * Method constructs all the urls the link extractor will search
     */
    public void constructAllURL(){
        try {
            getAllTeams();
            extractTeams();
        } catch (IOException | UnirestException e) {
            throw new RuntimeException(e);
        }

        try {
            for (TeamJsonWrapper teamJsonWrapper : this.teams) {
                String team = teamJsonWrapper.getTeamNum();
                String rokYearStr = teamJsonWrapper.getTeamJsonObject().get("rookie_year").toString();
                try {
                    if (rokYearStr != null) {
                        int rokYear = Integer.parseInt(rokYearStr);
                        for (int i = rokYear; i < Calendar.getInstance().get(Calendar.YEAR); i++) {
                            //System.out.println("Validating URL "+ TBA_BASE_CONSTRUCT_URL + "/" + team + "/" + i);
                            if (validateURL(TBA_BASE_CONSTRUCT_URL + team + "/" + i)) {
                                System.out.println("Link validated");
                                indexedSearchURIs.add(new IndexedURLWrapper(team, TBA_BASE_CONSTRUCT_URL + "/" + team + "/" + i));
                            }
                        }
                    }
                }catch (Exception e){
                   // e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns all the validated urls where search will should be done
     * @return
     */
    public ArrayList<IndexedURLWrapper> getIndexedSearchURIs() {
        return indexedSearchURIs;
    }

    /**
     * Returns the json wrapper list
     * @return
     */
    public ArrayList<TeamJsonWrapper> getTeams() {
        return teams;
    }
}
