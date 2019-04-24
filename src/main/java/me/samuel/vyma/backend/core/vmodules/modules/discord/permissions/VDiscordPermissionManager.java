package me.samuel.vyma.backend.core.vmodules.modules.discord.permissions;

import me.samuel.vyma.backend.core.vmodules.VManager;

public class VDiscordPermissionManager extends VManager<VDiscordPermission> {

    /**
     * VManager base constructor
     */
    public VDiscordPermissionManager() {
        super("VDiscordPermissionManager", 1.0);
    }


    @Override
    public VDiscordPermission get(String name) {
        return super.get("v_discord_permission_"+name);
    }
}
