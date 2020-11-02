package me.brennan.licensesystem.util;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.concurrent.TimeUnit;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class TextUtil {

    public static void sendEmbed(Message event, MessageEmbed messageEmbed, int delay) {
        event.getChannel().sendMessage(messageEmbed).queue(message -> message.delete().queueAfter(delay, TimeUnit.SECONDS));
        event.delete().queue();
    }

    public static void sendPrivateEmbed(Message event, MessageEmbed messageEmbed) {
        event.getChannel().sendMessage(messageEmbed).queue();
    }

}
