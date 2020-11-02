package me.brennan.licensesystem.listeners;

import me.brennan.licensesystem.LicenseSystem;
import me.brennan.licensesystem.api.model.License;
import me.brennan.licensesystem.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * @author Brennan
 * @since 10/26/2020
 **/
public class MessageListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        if (event.getAuthor() != event.getJDA().getSelfUser()) {
            final String content = event.getMessage().getContentRaw();
            if (content.startsWith("!")) {
                String commandID = content.split(" ")[0].replaceFirst("!", "");
                for (Command command : LicenseSystem.INSTANCE.getCommandManager().getCommands()) {
                    if (command.getName().equalsIgnoreCase(commandID)) {
                        event.getMessage().delete().queue();
                        switch (command.getEnumPermission()) {
                            case STAFF:
                                if(doesUserHaveRole(LicenseSystem.INSTANCE.getStaffRole(), event.getAuthor(), event.getGuild()))
                                    dispatch(command, content, event.getMessage(), commandID);
                                break;
                            case USER:
                                if(doesUserHaveRole(LicenseSystem.INSTANCE.getUserRole(), event.getAuthor(), event.getGuild()))
                                    dispatch(command, content, event.getMessage(), commandID);
                                break;
                            case EVERYONE:
                                dispatch(command, content, event.getMessage(), commandID);
                                break;
                        }
                    }
                }
            }
        }
    }

    private void dispatch(Command command, String content, Message message, String commandID) {
        String[] args = null;
        if (content.split(" ").length > 1) {
            args = content.replaceFirst("!" + commandID + " ", "").split(" ");
        }
        command.execute(message, args);
    }

    private boolean doesUserHaveRole(Role role, User user, Guild guild) {
        return guild.getMember(user).getRoles().contains(role);
    }

}
