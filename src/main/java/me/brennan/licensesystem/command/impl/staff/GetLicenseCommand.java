package me.brennan.licensesystem.command.impl.staff;

import me.brennan.licensesystem.LicenseSystem;
import me.brennan.licensesystem.api.model.License;
import me.brennan.licensesystem.command.Command;
import me.brennan.licensesystem.util.TextUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class GetLicenseCommand extends Command {

    public GetLicenseCommand() {
        super("getlicense", "Get information about a license.", EnumPermission.STAFF);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args.length != 1) {
            final EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setDescription("Not enough args.");
            TextUtil.sendEmbed(message, embedBuilder.build(), 5);
            return;
        }
        final License license = LicenseSystem.INSTANCE.getApi().getLicense(args[0]);

        if(license != null) {
            final EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.GREEN);
            embedBuilder.addField("Code", license.getLicenseCode(), false);
            if(license.getHwid() != null) {
                embedBuilder.addField("HWID", license.getHwid(), false);
            }

            if(license.getDiscordID() != null) {
                final User user = LicenseSystem.INSTANCE.getJda().getUserById(license.getDiscordID());
                if(user != null) {
                    embedBuilder.addField("Discord User", user.getAsTag(), false);
                } else {
                    embedBuilder.addField("Discord User", "Couldn't Fetch", false);
                }
            }

            if(!license.getNotes().isEmpty()) {
                license.getNotes().forEach(note -> {
                    embedBuilder.addField("Notes", note, false);
                });
            }

            TextUtil.sendEmbed(message, embedBuilder.build(), 10);
        } else {
            final EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setColor(Color.RED)
                    .setDescription("License does not exist.");
            TextUtil.sendEmbed(message, embedBuilder.build(), 5);
        }
    }
}
