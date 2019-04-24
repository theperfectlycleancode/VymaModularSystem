package me.samuel.vyma.backend.core.vmodules.modules.discord.command;

import me.samuel.vyma.backend.core.vmodules.VModule;
import org.javacord.api.event.message.MessageCreateEvent;

public abstract class VDiscordCommand extends VModule {

    private String label;

    private String[] aliases;
    /**
     * VModule base constructor
     */
    public VDiscordCommand(String name, String label, String[] aliases, double version) {
        super(name, version);

        this.label = label;
        this.aliases = aliases;
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

    public String getLabel() {
        return label;
    }

    public String[] getAliases() {
        return aliases;
    }

    public abstract void execute(String[] args, MessageCreateEvent contextEvent);
}
