package me.brennan.licensesystem.command.impl.staff;

import me.brennan.licensesystem.LicenseSystem;
import me.brennan.licensesystem.api.model.License;
import me.brennan.licensesystem.command.Command;
import me.brennan.licensesystem.util.TextUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class AddNotesCommand extends Command {

    public AddNotesCommand() {
        super("addnote", "Added a note to a license", EnumPermission.STAFF);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args != null) {
            if(args.length == 2) {
                final License license = LicenseSystem.INSTANCE.getApi().getLicense(args[0]);
                if(license != null) {
                    license.addNote(args[1]);
                    final License updatedLicense = LicenseSystem.INSTANCE.getApi().updateLicense(license);
                    if(updatedLicense != null) {
                        final EmbedBuilder embedBuilder = new EmbedBuilder()
                                .setColor(Color.GREEN)
                                .setDescription(String.format("Added %s note to %s", args[1], updatedLicense.getLicenseCode()));
                        TextUtil.sendEmbed(message, embedBuilder.build(), 5);
                    }
                } else {
                    final EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setColor(Color.RED)
                            .setDescription("License not found!");
                    TextUtil.sendEmbed(message, embedBuilder.build(), 5);
                }
            } else {
                final EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setDescription("Not enough args");
                TextUtil.sendEmbed(message, embedBuilder.build(), 5);
            }
        }
    }
}
