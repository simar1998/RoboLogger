package com.vyo.robologger.main;

import com.google.gson.Gson;
import com.vyo.robologger.main.boot.BootConfig;
import com.vyo.robologger.main.links.LinkExtractor;
import com.vyo.robologger.main.links.LinkSearch;
import com.vyo.robologger.main.url.UrlConstructor;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Main program entry point
 */
public class Main {

    //Public statically accessible bootConfig object
    public static BootConfig bootConfig;
    public static void main(String... args) throws IOException {

       assert args.length > 0 : "Boot Config file path argument not submitted, Can not continue!";

       bootConfigLoad(args[1]);

       extractImgurLinks();
    }

    private static void extractImgurLinks(){
        //Construct valid urls
        UrlConstructor urlConstructor = new UrlConstructor();
        urlConstructor.constructAllURL();

        //Append valid urls to linkExtractor list
        ArrayList<LinkExtractor> linkExtractors = new ArrayList<>();
        for (String link: urlConstructor.getIndexedSearchURIs()){
            linkExtractors.add(new LinkExtractor(link));
        }

        //Init Imgur Link Search on individual submitted urls
        //TODO add image file download to location and append information to ImageData hibernate entity
        for (LinkExtractor linkExtractor : linkExtractors){
            LinkSearch linkSearch = new LinkSearch(linkExtractor,"imgur","jpg");
            linkSearch.search();
            for (String imgurLink :  linkSearch.getSearchResults()){
                //TODO Hibernate and download code here
            }
        }

    }

    /**
     * Loads the boot config
     * @param filePath
     */
    private static void bootConfigLoad(String filePath){
        Gson gson = new Gson();
        bootConfig = gson.fromJson(Helper.fileRead(filePath),BootConfig.class).rectifyDownloadLocationStr();
    }

}
