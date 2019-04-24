package me.samuel.vyma.backend.core.vmodules.bootstrap;

import me.samuel.vyma.backend.core.vmodules.VManager;

import java.util.ArrayList;

public class Bootstrap {

    private ArrayList<VManager> managers;

    public Bootstrap() {
        managers = new ArrayList<>();
    }

    public <T extends VManager> T get(Class<T> type) {
        for(VManager manager : managers) {
            if(manager.getClass().getName().equals(type.getName()))
                return type.cast(manager);
        }
        return null;
    }

    public <T extends VManager> boolean exist(Class<T> type) {
        for(VManager manager : managers) {
            if(manager.getClass().getName().equals(type.getName()))
                return true;
        }
        return false;
    }

    public void load(VManager manager) {
        managers.add(manager);
    }
}
