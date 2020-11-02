package me.brennan.licensesystem.util;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class UserUtil {

    public static void sendPrivateMessage(User user, MessageEmbed messageEmbed) {
        user.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(messageEmbed).queue());
    }

    public static void sendPrivateMessage(User user, String content) {
        user.openPrivateChannel().queue(channel -> channel.sendMessage(content).queue());
    }

}
