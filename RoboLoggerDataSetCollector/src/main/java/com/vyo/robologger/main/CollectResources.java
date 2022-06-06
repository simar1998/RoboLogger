package com.vyo.robologger.main;

import com.vyo.robologger.main.db.DatabaseHelper;
import com.vyo.robologger.main.db.ImageData;
import com.vyo.robologger.main.links.LinkExtractor;
import com.vyo.robologger.main.links.LinkSearch;
import com.vyo.robologger.main.url.IndexedURLWrapper;
import com.vyo.robologger.main.url.UrlConstructor;

import java.io.*;
import java.net.URL;
import java.util.UUID;

/**
 * Collects resources (e.g. image files)  from urls generated from the api and
 */
public class CollectResources {

    /**
     * Starts collection
     */
    public void startCollection(){
        System.out.println("Starting collection");
        try {
            extractImgurLinks();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Method takes image url downloads it and saves it to location which it also takes from param
     * @param imageUrl
     * @param imageSavePath
     * @throws IOException
     */
    private void saveImageFromUrl(String imageUrl, String imageSavePath) throws IOException {
        URL url = new URL(imageUrl);
        InputStream in = null;
        try {
            in = new BufferedInputStream(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(imageSavePath);
        fos.write(response);
        fos.close();
    }

    /**
     * Main method that constcuts team urls and then scrapes imgur links from them and downloads and appends image file locationr refrences to mysql database
     * @throws IOException
     */
    private void extractImgurLinks() throws IOException {
        //Construct valid urls
        UrlConstructor urlConstructor = new UrlConstructor();
        urlConstructor.constructAllURL();

        //Append valid urls to linkExtractor list
        for (IndexedURLWrapper link: urlConstructor.getIndexedSearchURIs()){
            link.setLinkExtractor(new LinkExtractor(link.getUrl()).extractLinks());
        }

        //Init Imgur Link Search on individual submitted urls
        //TODO add image file download to location and append information to ImageData hibernate entity
        for (IndexedURLWrapper links : urlConstructor.getIndexedSearchURIs()){
            LinkSearch linkSearch = new LinkSearch(links.getLinkExtractor(),"imgur","jpg");
            linkSearch.search();
            for (String imgurLink :  linkSearch.getSearchResults()){
                //Constructs the save file location for the image to be downloaded
                String imageSavePath = Main.bootConfig.getDOWNLOAD_LOCATION()+links.getTeam()+"_"+ UUID.randomUUID()+".jpg";
                //Saves the image from the url to the path
                saveImageFromUrl(imgurLink,imageSavePath);
                //Constructs the Image Data object
                ImageData imageData = new ImageData();
                imageData.setUrl(imgurLink);
                imageData.setImageLocation(imageSavePath);
                imageData.setTeamNum(Integer.parseInt(links.getTeam()));
                imageData.setRetrievalTime(String.valueOf(java.time.LocalTime.now()));
                //Save the image data object to the database
                DatabaseHelper.insertImageData(imageData);
            }
        }

    }

}
