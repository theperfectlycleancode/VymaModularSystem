package me.samuel.vyma.backend.core.vmodules;

import java.util.ArrayList;

/**VManager is the base class for any VModuleManager*/

public abstract class VManager<T> {

    /**VManager name for registration*/
    private String name;

    /**List for storing VModules that are registered*/
    private ArrayList<T> vModules;

    private double version;

    /**
     * VManager base constructor
     *
     * @param name Manager name
     * @param version Manager version
     * */
    public VManager(String name, double version) {
        vModules = new ArrayList<>();
        this.name = name;
        this.version = version;
    }

    /**Public getter method for retrieving the VManager name*/
    public String getName() {
        return name;
    }

    /**Public getter method for retrieving the VManager version*/
    public double getVersion() {
        return version;
    }

    /**Public register method for loading and registering VModules*/
    public void register(T module) {
        if(module instanceof VModule)
            if((((VModule) module)).load())
                vModules.add(module);
    }

    /**Public unregister method for unloading and unregistering VModules*/
    public void unregister(T module) {
        if(module instanceof VModule)
            if((((VModule) module)).unload())
                vModules.remove(module);
    }
    /**Public getter method for retrieving registered VModules*/
    public T get(String name) {
        for(T module : vModules) {
            if((((VModule) module)).getName().equals(name)) {
                return module;
            }
        }
        return null;
    }

    public ArrayList<T> get() {
        return vModules;
    }
}
