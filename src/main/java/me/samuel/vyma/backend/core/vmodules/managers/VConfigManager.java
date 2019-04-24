package me.samuel.vyma.backend.core.vmodules.managers;

import com.github.cliftonlabs.json_simple.Jsoner;
import me.samuel.vyma.backend.core.vmodules.VManager;
import me.samuel.vyma.backend.core.vmodules.modules.VConfig;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;

public class VConfigManager extends VManager<VConfig> {


    /** Config main directory*/
    private Path configDirectory;

    /**
     * VConfigManager constructor
     *
     * @param configDirectory ConfigManagers base config directory
     */
    public VConfigManager(Path configDirectory) {
        super("VConfigManager", 1.0);
        this.configDirectory = configDirectory;
    }

    /**
     * Config save method
     *
     * @param name Name of the config
     * */
    public boolean save(String name) {
        try {
            if(!(new File(configDirectory.toString()).exists()))
                new File(configDirectory.toString()).mkdirs();
            FileWriter fileWriter = new FileWriter(new File(configDirectory.toString(),name+".vconfig.json"));

            fileWriter.write(Jsoner.prettyPrint(get(name).getJson().toString()));

            fileWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }
    /**
     * Config load method
     *
     * @param name Name of the config
     * */
    public boolean load(String name) {
        if(!exist(name))
            return false;
        try {
            String json = FileUtils.readFileToString(new File(configDirectory.toString(),name+".vconfig.json"));

            if(get(name) != null) {
                get(name).load(json);
                return true;
            }else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Config exist check
     *
     * @param name Name of the config
     * */
    public boolean exist(String name) {
        File cfgFile = new File(configDirectory.toString(), name+".vconfig.json");
        return cfgFile.exists();
    }

    /** Init the defaults for all registered configs*/
    public void init() {
        for(VConfig config : this.get()) {
            if(!exist(config.getName())) {
                config.setDefaults();
                save(config.getName());
            }
        }
    }
}
