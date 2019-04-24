package me.samuel.vyma.backend.core.vmodules.modules.discord.command;

import me.samuel.vyma.backend.core.vmodules.VManager;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.List;

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
            if(!msg_prefix.equals(PREFIX) ) {
                return;
            }

            String message = event.getMessageContent();

            String[] args = message.replaceFirst(PREFIX, "").split(" ");

            if(args.length <= 0)
                return;

            String cmd_label = args[0];

            String[] cmd_args = parseArgs(message.replaceFirst(PREFIX,""),cmd_label, " ".toCharArray()[0]);
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

    public static String[] parseArgs(String text, String cmdName, char placeholder) {

        List<String> args = new ArrayList<>();

        String currentArg = "";

        int i = 0;
        for(char c : text.toCharArray()) {
            i++;
            if(c != placeholder) {
                currentArg += c;
            }else {
                if(currentArg.length() > 1 && !currentArg.equals(cmdName)) {
                    args.add(currentArg);
                }
                currentArg = "";
            }
            if(i >= text.toCharArray().length) {
                if(currentArg.length() > 1 && !currentArg.equals(cmdName)) {
                    args.add(currentArg);
                }
                currentArg = "";
            }
        }
        if(args.size() > 0) {
            String[] arrg = new String[args.size()];
            for(i = 0; i < args.size(); i++) {
                arrg[i] = args.get(i);
            }
            return arrg;
        }
        return new String[]{};
    }
}
