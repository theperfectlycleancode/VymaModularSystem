package me.samuel.vyma.backend.core.vmodules.modules.configs;

import me.samuel.vyma.backend.core.vmodules.modules.VConfig;
import org.json.JSONObject;

public class VDiscordConfig extends VConfig {


    public static final String NAME = "discord";

    /**
     * VModule base constructor
     */
    public VDiscordConfig() {
        super(NAME, 1.0);
    }

    @Override
    public void setDefaults() {
        getJson().put("discord",new JSONObject());
        getJson().getJSONObject("discord").put("token", "token");
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
