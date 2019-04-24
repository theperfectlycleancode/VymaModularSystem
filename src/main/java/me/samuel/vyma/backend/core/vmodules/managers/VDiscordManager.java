package me.samuel.vyma.backend.core.vmodules.managers;

import me.samuel.vyma.backend.core.vmodules.VManager;
import me.samuel.vyma.backend.core.vmodules.modules.discord.VDiscord;

public class VDiscordManager extends VManager<VDiscord> {


    /**
     * VManager base constructor
     */
    public VDiscordManager() {
        super("VDiscordManager", 1.0);
    }
}
