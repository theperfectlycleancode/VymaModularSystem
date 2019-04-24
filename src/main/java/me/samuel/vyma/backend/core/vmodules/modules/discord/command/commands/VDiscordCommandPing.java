package me.samuel.vyma.backend.core.vmodules.modules.discord.command.commands;

import me.samuel.vyma.backend.core.vmodules.modules.discord.command.VDiscordCommand;
import org.javacord.api.event.message.MessageCreateEvent;

public class VDiscordCommandPing extends VDiscordCommand {

    public static final String NAME = "VDiscordPingCommand";

    /**
     * VModule base constructor
     */
    public VDiscordCommandPing() {
        super(NAME, "ping", null, 1.0);
    }

    @Override
    public void execute(String[] args, MessageCreateEvent contextEvent) {
        contextEvent.getMessage().getChannel().sendMessage("Pong!");
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }
}
