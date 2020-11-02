package me.brennan.licensesystem;

import me.brennan.licensesystem.api.API;
import me.brennan.licensesystem.command.CommandManager;
import me.brennan.licensesystem.listeners.MessageListener;
import me.brennan.licensesystem.listeners.PrivateMessageListener;
import me.brennan.licensesystem.util.config.ConfigUtil;
import me.brennan.licensesystem.util.config.model.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public enum LicenseSystem {
    INSTANCE;

    private JDA jda;

    private API api;

    private Guild guild;
    private Role userRole;
    private Role staffRole;

    private CommandManager commandManager;

    public void start() throws Exception {
        final Config config = ConfigUtil.readConfig();
        if(config == null) {
            return;
        }
        this.jda = JDABuilder
                .createDefault(config.getDiscordToken())
                .addEventListeners(new MessageListener(), new PrivateMessageListener()).build().awaitReady();

        this.api = new API(config.getApiURL(), "");

        this.guild = jda.getGuildById(config.getGuildID());

        if(guild != null) {
            this.userRole = guild.getRoleById(config.getUserRoleID());
            this.staffRole = guild.getRoleById(config.getStaffRoleID());
        }

        this.commandManager = new CommandManager();
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Role getStaffRole() {
        return staffRole;
    }

    public Role getUserRole() {
        return userRole;
    }

    public Guild getGuild() {
        return guild;
    }

    public API getApi() {
        return api;
    }

    public JDA getJda() {
        return jda;
    }
}
