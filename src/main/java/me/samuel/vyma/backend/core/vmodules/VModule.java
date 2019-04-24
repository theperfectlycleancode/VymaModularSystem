package me.samuel.vyma.backend.core.vmodules;

import me.samuel.vyma.backend.types.VModuleState;

/**VModules is the base class for any VModule*/
public abstract class VModule {

    /**VModule name*/
    private String name;

    /**VModule version*/
    private double version;

    /**VModules alive state*/
    private VModuleState state;


    /**VModule base constructor*/
    public VModule(String name, double version) {
        this.name = name;
        this.version = version;
    }

    /**Public getter method for retrieving the VModules name*/
    public String getName() {
        return name;
    }

    /**Public getter method for retrieving the VModules version*/
    public double getVersion() {
        return version;
    }

    /**VModule load method*/
    boolean load() {
        if(state == VModuleState.ALIVE)
            return false;
        onLoad();
        state = VModuleState.ALIVE;
        onPostLoad();
        return true;
    }

    /**VModule unload method*/
    boolean unload() {
        if(state != VModuleState.ALIVE)
            return false;
        onUnload();
        state = VModuleState.DEAD;
        onPostUnload();
        return true;
    }

    /**Abstract load method*/
    public abstract void onLoad();

    /**Abstract post-load method*/
    public abstract void onPostLoad();


    /**Abstract unload method*/
    public abstract void onUnload();

    /**Abstract post-unload method*/
    public abstract void onPostUnload();
}
