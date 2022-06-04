package com.vyo.robologger.main;

import java.io.IOException;

public class Main {
    public static void main(String... args) throws IOException {
       LinkExtractor linkExtractor = new LinkExtractor("https://www.thebluealliance.com/team/1310/2019");
       linkExtractor.extractLinks();

       LinkSearch linkSearch = new LinkSearch(linkExtractor,"imgur","jpg");
       linkSearch.search();
        System.out.println("Printing search results below");
        for (String str:
             linkSearch.getSearchResults()) {
            System.out.println(str);
        }
    }
}
