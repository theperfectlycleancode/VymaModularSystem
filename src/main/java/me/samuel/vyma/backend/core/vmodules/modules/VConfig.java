package me.samuel.vyma.backend.core.vmodules.modules;

import me.samuel.vyma.backend.VymaBackend;
import me.samuel.vyma.backend.core.vmodules.VModule;
import me.samuel.vyma.backend.core.vmodules.managers.VConfigManager;
import org.json.JSONObject;

public abstract class VConfig extends VModule {

    private JSONObject configJson;

    /**
     * VModule base constructor
     *
     * @param name Config Name
     */
    public VConfig(String name, double version) {
        super(name, version);
        configJson = new JSONObject();
        configJson.put("config", new JSONObject());
        configJson.getJSONObject("config").put("name",getName());
        configJson.getJSONObject("config").put("version",getVersion());
    }

    public void load(String json) {
        configJson = new JSONObject(json);
        try {
            try {
                if(configJson.getJSONObject("config") != null) {
                    if(configJson.getJSONObject("config").get("version") != null) {
                        double v1 = configJson.getJSONObject("config").getDouble("version");
                        if(v1 < getVersion()) {
                            configJson.put("config", new JSONObject());
                            configJson.getJSONObject("config").put("name",getName());
                            configJson.getJSONObject("config").put("version",getVersion());
                            setDefaults();
                            VymaBackend.getInstance().getBootstrap().get(VConfigManager.class).save(getName());
                        }
                    }else {
                        configJson.put("config", new JSONObject());
                        configJson.getJSONObject("config").put("name",getName());
                        configJson.getJSONObject("config").put("version",getVersion());
                        setDefaults();
                        VymaBackend.getInstance().getBootstrap().get(VConfigManager.class).save(getName());
                    }
                }else {
                    configJson.put("config", new JSONObject());
                    configJson.getJSONObject("config").put("name",getName());
                    configJson.getJSONObject("config").put("version",getVersion());
                    setDefaults();
                    VymaBackend.getInstance().getBootstrap().get(VConfigManager.class).save(getName());
                }
            }catch (Exception e) {
                configJson.put("config", new JSONObject());
                configJson.getJSONObject("config").put("name",getName());
                configJson.getJSONObject("config").put("version",getVersion());
                setDefaults();
                VymaBackend.getInstance().getBootstrap().get(VConfigManager.class).save(getName());
            }
        }catch (Exception e) { }
    }
    public JSONObject getJson() {
        return configJson;
    }

    public abstract void setDefaults();
}
