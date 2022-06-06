package com.vyo.robologger.main.url;

import com.vyo.robologger.main.Main;
import okhttp3.*;
import org.apache.commons.validator.UrlValidator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class constructs the URL strings that are to be submitted to LinkExtractor and LinkSearch by The Blue Alliance API Invocation
 */
public class UrlConstructor {

    private String allTeamsStr;
    private ArrayList<TeamJsonWrapper> teams = new ArrayList<>();

    private ArrayList<String> indexedSearchURIs = new ArrayList<>();

    private final String TBA_BASE_CONSTRUCT_URL = "https://www.thebluealliance.com/team/";
    /**
     * Extracts the team numbers from the response string and appends them onto a list
     */
    private void extractTeams(){
        assert !allTeamsStr.isEmpty() : "All teams api call response empty";

        JSONArray jsonArray = new JSONArray(allTeamsStr);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            teams.add(new TeamJsonWrapper(jsonobject.getString("team_number"),jsonobject));
        }
    }

    /**
     * Gets the response string for all teams
     * @throws IOException
     */
    private void getAllTeams() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://www.thebluealliance.com/api/v3/teams/all")
                .method("GET", body)
                .addHeader("X-TBA-Auth-Key", Main.bootConfig.getTBA_KEY())
                .build();
        Response response = client.newCall(request).execute();
        this.allTeamsStr = response.toString();
    }

    /**
     * Method validates a provided URL and returns whether it exists.
     * @param url
     * @return
     */
    private boolean validateURL(String url){
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }

    /**
     * Method constructs all the urls the link extractor will search
     */
    public void constructAllURL(){
        for (TeamJsonWrapper teamJsonWrapper : this.teams){
            String team = teamJsonWrapper.getTeamNum();
            int rokYear = teamJsonWrapper.getTeamJsonObject().getInt("rookie_year");
            for (int i = rokYear; i < Calendar.getInstance().get(Calendar.YEAR);i++){
                if (validateURL(TBA_BASE_CONSTRUCT_URL+"/"+team+"/"+i)){
                    indexedSearchURIs.add(TBA_BASE_CONSTRUCT_URL+"/"+team+"/"+i);
                }
            }
        }
    }

    public ArrayList<String> getIndexedSearchURIs() {
        return indexedSearchURIs;
    }
}
