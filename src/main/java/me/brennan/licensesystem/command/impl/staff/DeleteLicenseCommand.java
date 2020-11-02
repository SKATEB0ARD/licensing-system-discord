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
public class DeleteLicenseCommand extends Command {

    public DeleteLicenseCommand() {
        super("delete", "Deletes a license", EnumPermission.STAFF);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args != null) {
            if(args.length > 1) {
                final License license = LicenseSystem.INSTANCE.getApi().deleteLicense(args[0]);
                if(license != null) {
                    final EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setColor(Color.RED)
                            .setDescription("Deleted License.");
                    TextUtil.sendEmbed(message, embedBuilder.build(), 5);
                } else {
                    final EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setColor(Color.RED)
                            .setDescription("Could not delete license.");
                    TextUtil.sendEmbed(message, embedBuilder.build(), 5);
                }
            }
        }
    }
}
