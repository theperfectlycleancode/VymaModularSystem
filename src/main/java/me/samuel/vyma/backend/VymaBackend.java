package me.samuel.vyma.backend;

import me.samuel.vyma.backend.core.vmodules.bootstrap.Bootstrap;
import me.samuel.vyma.backend.core.vmodules.managers.VConfigManager;
import me.samuel.vyma.backend.core.vmodules.managers.VDiscordManager;
import me.samuel.vyma.backend.core.vmodules.modules.configs.VDiscordConfig;
import me.samuel.vyma.backend.core.vmodules.modules.configs.VMainConfig;
import me.samuel.vyma.backend.core.vmodules.modules.discord.VDiscord;

import java.nio.file.Paths;

public class VymaBackend {

    private static VymaBackend instance;

    private int initI = 0;

    private Bootstrap bootstrap;

    private VymaBackend(String[] args) {
        instance = this;
        bootstrap = new Bootstrap();
    }

    private void init() {
        if(initI != 0)
            return;
        initI = 1;

        bootstrap.load(new VConfigManager(Paths.get("/home/nullptr/IdeaProjects/VymaBackend/system")));
        bootstrap.get(VConfigManager.class).register(new VMainConfig());
        bootstrap.get(VConfigManager.class).register(new VDiscordConfig());
        bootstrap.get(VConfigManager.class).load(VMainConfig.NAME);
        bootstrap.get(VConfigManager.class).load(VDiscordConfig.NAME);
        bootstrap.get(VConfigManager.class).init();
        bootstrap.load(new VDiscordManager());
        bootstrap.get(VDiscordManager.class).register(new VDiscord());
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public static VymaBackend getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        new VymaBackend(args).init();
    }
}
