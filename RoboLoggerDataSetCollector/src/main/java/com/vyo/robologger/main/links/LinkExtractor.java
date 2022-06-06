package com.vyo.robologger.main.links;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Iteratively searches through html file for DOM objects for href tags then invokes Link Search to filter results to link that have pic host domain e.g. imgur
 */
public class LinkExtractor {

    String url;

    private ArrayList<String> mediaLinks = new ArrayList<>();
    private ArrayList<String> importLinks = new ArrayList<>();
    private ArrayList<String> links = new ArrayList<>();
    private ArrayList<String> allLinks = new ArrayList<>();

    Document doc;


    public LinkExtractor(String url){
        this.url = url;
    }

    public void extractLinks() throws IOException {
        print("Fetching %s...", url);

        doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        print("\nMedia: (%d)", media.size());
        for (Element src : media) {
            if (src.normalName().equals("img"))
                print(" * %s: <%s> %sx%s (%s)",mediaLinks,
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        trim(src.attr("alt"), 20));
            else
                print(" * %s: <%s>",mediaLinks, src.tagName(), src.attr("abs:src"));
        }

        print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            print(" * %s <%s> (%s)",importLinks, link.tagName(),link.attr("abs:href"), link.attr("rel"));
        }

        print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", this.links, link.attr("abs:href"), trim(link.text(), 35));
        }

        System.out.println("Appending all links to one collection");

        allLinks.addAll(mediaLinks);
        allLinks.addAll(importLinks);
        allLinks.addAll(this.links);
    }

    private static void print(String msg,ArrayList<String> list, Object... args) {
        list.add(String.format(msg, args));
        System.out.println(String.format(msg, args));
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<String> getMediaLinks() {
        return mediaLinks;
    }

    public ArrayList<String> getImportLinks() {
        return importLinks;
    }

    public ArrayList<String> getLinks() {
        return links;
    }

    public ArrayList<String> getAllLinks() {
        return allLinks;
    }

    public Document getDoc() {
        return doc;
    }

    public void fetchLinksIfEmpty(){
        if (allLinks.isEmpty()) {
            try {
                extractLinks();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
