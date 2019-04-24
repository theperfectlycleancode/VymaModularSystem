package me.samuel.vyma.backend.core.vmodules.modules.configs;

import me.samuel.vyma.backend.core.vmodules.modules.VConfig;
import org.json.JSONObject;

public class VMainConfig extends VConfig {


    public static final String NAME = "main";

    /**
     * VModule base constructor
     */
    public VMainConfig() {
        super(NAME, 1.02);
    }

    @Override
    public void setDefaults() {
        getJson().put("vyma",new JSONObject());
        getJson().getJSONObject("vyma").put("sys.name", "VymaSystem");
        getJson().getJSONObject("vyma").put("sys.version", 1.0D);
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onPostLoad() {
        try {
        }catch (Exception e) {}
    }

    @Override
    public void onUnload() {

    }

    @Override
    public void onPostUnload() {

    }
}
