import com.google.gson.Gson;
import com.vyo.robologger.main.boot.BootBehaviour;
import com.vyo.robologger.main.boot.Config;

/**
 * Boot file test string generator
 */
public class BootConfigStrGen {

    public static void main(String... args){
        Gson gson = new Gson();
        Config config = new Config();
        config.setTBA_KEY("tbakeyhere");
        config.setMYSQL_USR("mysqlusrhere");
        config.setMYSQL_PASSWD("mysqlpasswd");
        config.setMYSQL_URL("mysqlurl");
        config.setBootBehaviour(BootBehaviour.update_boot);
        config.setDOWNLOAD_LOCATION("downloadlocationhere");
        System.out.println(gson.toJson(config));
    }
}
