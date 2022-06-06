package com.vyo.robologger.main;

import com.google.gson.Gson;
import com.vyo.robologger.main.boot.BootConfig;
import com.vyo.robologger.main.db.DatabaseInit;


import java.io.IOException;
//TODO create code for clean boot is necessary or remove the boot behaviour if deemed proper.
/**
 * Main program entry point
 */
public class Main {

    //Public statically accessible bootConfig object
    public static BootConfig bootConfig;
    public static void main(String... args) throws IOException {

        assert args.length > 0 : "Boot Config file path argument not submitted, Can not continue!";

        //Loads config vars
        bootConfigLoad(args[1]);

        //Inits Database
        DatabaseInit.buildSessionFactory();

        //Inits resource collection and database reference building
        CollectResources collectResources = new CollectResources();
        collectResources.startCollection();
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
