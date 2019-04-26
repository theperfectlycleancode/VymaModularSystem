package me.samuel.vyma.backend.core.vmodules.managers;

import me.samuel.vyma.backend.core.vmodules.VManager;
import me.samuel.vyma.backend.core.vmodules.modules.plugins.VPlugin;

import java.io.File;
import java.nio.file.Path;

public class VPluginManager extends VManager<VPlugin> {



    private static final String NAME = "VPluginManager";


    private Path pluginDirectory;


    /**
     * VManager base constructor
     */
    public VPluginManager(Path pluginDirectory) {
        super(NAME, 1.0);
        this.pluginDirectory = pluginDirectory;

        if(!pluginDirectory.toFile().exists()) {
            pluginDirectory.toFile().mkdirs();
        }
    }

    @Override
    public void register(VPlugin module) {
        super.register(module);
    }

    public void load(String name) {
        System.out.println("loading plugin... "+name);
        register(new VPlugin(name,1.0,new File(pluginDirectory.toAbsolutePath().toString(),name)));
    }
}
