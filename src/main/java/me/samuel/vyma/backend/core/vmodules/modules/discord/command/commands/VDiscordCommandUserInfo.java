package me.samuel.vyma.backend.core.vmodules.modules.discord.command.commands;

import me.samuel.vyma.backend.core.vmodules.modules.discord.command.VDiscordCommand;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class VDiscordCommandUserInfo extends VDiscordCommand {

    public static final String NAME = "VDiscordPingCommand";

    /**
     * VModule base constructor
     */
    public VDiscordCommandUserInfo() {
        super(NAME, "userInfo", null, 1.0);
    }

    @Override
    public void execute(String[] args, MessageCreateEvent contextEvent) {
        TextChannel channel = contextEvent.getMessage().getChannel();
        List<User> users = contextEvent.getMessage().getMentionedUsers();

        if (users.size() > 1) {
            if(contextEvent.getMessage().getAuthor().asUser().isPresent()) {
                EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor("Error");
                embedBuilder.setColor(new Color(64, 0, 9, 200));
                embedBuilder.addField(contextEvent.getMessage().getAuthor().asUser().get().getMentionTag(), " please only enter one user at a time!");

                channel.sendMessage(embedBuilder);
                contextEvent.getMessage().delete();
            }
            else {
                EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor("Error");
                embedBuilder.setColor(new Color(64, 0, 9, 200));
                embedBuilder.addField("Undefined behaviour!", "Undefined behaviour!");

                channel.sendMessage(embedBuilder);
                contextEvent.getMessage().delete();

            }
            return;
        }

        CompletableFuture<Message> message = channel.sendMessage("Responding...");

        contextEvent.getMessage().delete();

        if(users.size() <= 0) {
            try {
                EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor("Error");
                embedBuilder.setColor(new Color(64, 0, 9, 200));
                embedBuilder.addField("Not a user!", "Could not fetch userData, please specify a target!");

                message.get().edit("", embedBuilder);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return;
        }

        User target = users.get(0);

        EmbedBuilder embedBuilder = new EmbedBuilder().setAuthor("§UserInfo");

        embedBuilder.setColor(new Color(0, 64, 3, 200));
        embedBuilder.addField("userName", target.getName());
        embedBuilder.addField("userMentionTag", target.getMentionTag());
        embedBuilder.addField("userId", target.getIdAsString());
        if (target.getActivity().isPresent()) {
            embedBuilder.addField("userActivity", target.getActivity().get().getName());
            if (target.getActivity().get().getName().equalsIgnoreCase("Spotify")) {
                if (target.getActivity().get().getState().isPresent()) {
                    embedBuilder.addInlineField("Song artist", target.getActivity().get().getState().get());
                    if (target.getActivity().get().getDetails().isPresent()) {
                        embedBuilder.addInlineField("Song name", target.getActivity().get().getDetails().get());
                    }
                }
            }
        }
        embedBuilder.setThumbnail(target.getAvatar());

        try {
            message.get().edit("", embedBuilder);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }
}
