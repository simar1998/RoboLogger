import com.google.gson.Gson;
import com.vyo.robologger.main.boot.BootBehaviour;
import com.vyo.robologger.main.boot.BootConfig;

/**
 * Boot file test string generator
 */
public class BootConfigStrGen {

    public static void main(String... args){
        Gson gson = new Gson();
        BootConfig bootConfig = new BootConfig();
        bootConfig.setTBA_KEY("tbakeyhere");
        bootConfig.setMYSQL_USR("mysqlusrhere");
        bootConfig.setMYSQL_PASSWD("mysqlpasswd");
        bootConfig.setMYSQL_URL("mysqlurl");
        bootConfig.setBootBehaviour(BootBehaviour.update_boot);
        bootConfig.setDOWNLOAD_LOCATION("downloadlocationhere");
        System.out.println(gson.toJson(bootConfig));
    }
}
