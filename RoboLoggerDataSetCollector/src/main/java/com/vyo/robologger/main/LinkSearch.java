package com.vyo.robologger.main;

import java.util.ArrayList;
import java.util.Arrays;

public class LinkSearch {

    private LinkExtractor linkExtractor;
    private String url;
    private ArrayList<String> searchStrList = new ArrayList<>();

    private ArrayList<String> searchResults = new ArrayList<>();

    public LinkSearch(LinkExtractor linkExtractor, String searchStrList) {
        this.linkExtractor = linkExtractor;
        this.url = linkExtractor.getUrl();
        this.searchStrList.add(searchStrList);
    }

    public LinkSearch(String url, String searchStrList) {
        this.url = url;
        this.linkExtractor = new LinkExtractor(url);
        this.linkExtractor.fetchLinksIfEmpty();
        this.searchStrList.add(searchStrList);
    }

    public LinkSearch(LinkExtractor linkExtractor, String... search){
        this.linkExtractor = linkExtractor;
        this.url = linkExtractor.getUrl();
        this.searchStrList.addAll(Arrays.asList(search));
    }

    public ArrayList<String> search(){

        ArrayList<String> tempList = new ArrayList<>();
        for (int i = 0; i < searchStrList.size();i++){
            System.out.println("Test"+searchStrList.get(i));
            for (String str : linkExtractor.getAllLinks()) {
                if (str.contains(searchStrList.get(i))) {
                    tempList.add(str);
                }
                if (!str.contains(searchStrList.get(i))){
                   tempList.remove(str);
                }
            }
        }
        this.searchResults.addAll(tempList);

        System.out.println("Search completed for : " + searchStrList + " in " + linkExtractor.getDoc().title());
        System.out.println("Extracted "+ searchResults.size()+ " results");
        return searchResults;
    }



    public ArrayList<String> getSearchResults() {
        return searchResults;
    }
}
