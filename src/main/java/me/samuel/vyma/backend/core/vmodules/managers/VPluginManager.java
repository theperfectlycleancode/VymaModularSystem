package me.samuel.vyma.backend.core.vmodules.managers;

import me.samuel.vyma.backend.core.vmodules.VManager;
import me.samuel.vyma.backend.core.vmodules.modules.plugins.VPlugin;

public class VPluginManager extends VManager<VPlugin> {

    private static final String NAME = "VPluginManager";

    /**
     * VManager base constructor
     */
    public VPluginManager() {
        super(NAME, 1.0);
    }
}
