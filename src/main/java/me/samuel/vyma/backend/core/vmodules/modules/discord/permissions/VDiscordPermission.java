package me.samuel.vyma.backend.core.vmodules.modules.discord.permissions;

import me.samuel.vyma.backend.core.vmodules.VModule;

public class VDiscordPermission extends VModule {

    /**
     * VModule base constructor
     *
     * @param name
     * @param version
     */
    public VDiscordPermission(String name, double version) {
        super("v_discord_permission_"+name, version);
    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onPostLoad() {

    }

    @Override
    public void onUnload() {

    }

    @Override
    public void onPostUnload() {

    }
}
