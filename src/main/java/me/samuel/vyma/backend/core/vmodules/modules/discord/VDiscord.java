package me.samuel.vyma.backend.core.vmodules.modules.discord;

import me.samuel.vyma.backend.VymaBackend;
import me.samuel.vyma.backend.core.vmodules.VModule;
import me.samuel.vyma.backend.core.vmodules.managers.VConfigManager;
import me.samuel.vyma.backend.core.vmodules.modules.configs.VDiscordConfig;
import me.samuel.vyma.backend.core.vmodules.modules.discord.command.VDiscordCommandManager;
import me.samuel.vyma.backend.core.vmodules.modules.discord.command.commands.VDiscordCommandHelp;
import me.samuel.vyma.backend.core.vmodules.modules.discord.command.commands.VDiscordCommandPing;
import me.samuel.vyma.backend.core.vmodules.modules.discord.command.commands.VDiscordCommandUserInfo;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.util.logging.ExceptionLogger;

public class VDiscord extends VModule {

    public static final String NAME = "VDiscord";

    private VDiscordCommandManager commandManager;

    private Thread thread;

    /**
     * VDiscord constructor
     */
    public VDiscord() {
        super(NAME, 1.0);
        commandManager = new VDiscordCommandManager();
    }

    private static DiscordApi api;

    static int _i = 0;

    private void init() {
        try {
            api = (DiscordApi) new DiscordApiBuilder().setToken((String) VymaBackend.getInstance().getBootstrap().get(VConfigManager.class).get(VDiscordConfig.NAME).getJson().getJSONObject("discord").get("token")).login().thenAccept(api -> {
                // Add a listener which answers with "Pong!" if someone writes "!ping"
                api.addMessageCreateListener(event -> {
                    commandManager.onMessageCreateEvent(event);
                });
                api.updateActivity(ActivityType.WATCHING, "you...");
                // Print the invite url of your bot
                System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
            }).exceptionally(ExceptionLogger.get()).thenAccept(event -> {

            }).exceptionally(ExceptionLogger.get());
            System.out.println("Started...");

        }catch (Exception ignored) {}
    }

    public static DiscordApi getApi() {
        return api;
    }

    @Override
    public void onLoad() {
        commandManager.register(new VDiscordCommandPing());
        commandManager.register(new VDiscordCommandHelp());
        commandManager.register(new VDiscordCommandUserInfo());
    }

    @Override
    public void onPostLoad() {
        thread = new Thread(this::init);
        thread.start();
    }

    @Override
    public void onUnload() {
        thread.stop();
    }

    @Override
    public void onPostUnload() {

    }
}
