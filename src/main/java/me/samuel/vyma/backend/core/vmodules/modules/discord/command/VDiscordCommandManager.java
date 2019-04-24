package me.samuel.vyma.backend.core.vmodules.modules.discord.command;

import me.samuel.vyma.backend.core.vmodules.VManager;
import org.javacord.api.event.message.MessageCreateEvent;

public class VDiscordCommandManager extends VManager<VDiscordCommand> {

    private static final String PREFIX = "§";

    /**
     * VManager base constructor
     */
    public VDiscordCommandManager() {
        super("VDiscordCommandManager", 1.0);
    }

    public void onMessageCreateEvent(MessageCreateEvent event) {
        try {
            if(event.getMessageContent() == null || event.getMessageContent().length() <= 0) {
                return;
            }
            String msg_prefix = event.getMessageContent().substring(0,1);
            String message = event.getMessageContent();

            String[] args = message.replaceFirst(PREFIX, "").split(" ");

            if(args.length <= 0)
                return;

            String cmd_label = args[0];

            String[] cmd_args = message.replaceFirst(cmd_label,"").split(" ");

            /*
            if(args.length > 1) {
                for(int i = 1; i < args.length; i++) {
                    cmd_args[i-1] = args[i];
                }
            }*/

            for(VDiscordCommand command : get()) {
                if(command.getLabel().equalsIgnoreCase(cmd_label)) {
                    command.execute(cmd_args, event);
                }else if(command.getAliases() != null && command.getAliases().length > 1) {
                    for(String alias : command.getAliases()) {
                        if(alias.equalsIgnoreCase(cmd_label)) {
                            command.execute(cmd_args, event);
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("Exception caught while executing command!");
            e.printStackTrace();
        }
    }
}
