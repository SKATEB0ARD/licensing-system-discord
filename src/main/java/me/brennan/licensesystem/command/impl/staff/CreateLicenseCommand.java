package me.brennan.licensesystem.command.impl.staff;

import me.brennan.licensesystem.LicenseSystem;
import me.brennan.licensesystem.api.model.License;
import me.brennan.licensesystem.command.Command;
import me.brennan.licensesystem.util.TextUtil;
import me.brennan.licensesystem.util.UserUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class CreateLicenseCommand extends Command {

    public CreateLicenseCommand() {
        super("create", "Create a license", EnumPermission.STAFF);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args != null) {
            if(args.length == 1) {
                final int amount = Integer.parseInt(args[0]);
                if(amount > 10) {
                    final EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setColor(Color.RED)
                            .setDescription("You can only generate 10 at a time");
                    TextUtil.sendEmbed(message, embedBuilder.build(), 5);
                    return;
                }
                final List<String> licenses = new LinkedList<>();

                for(int i = 0; i < amount; i++) {
                    final License license = LicenseSystem.INSTANCE.getApi().createLicense();
                    licenses.add(license.getLicenseCode());
                }

                final EmbedBuilder embedBuilder = new EmbedBuilder().setColor(Color.GREEN);
                embedBuilder.setDescription("Created License Codes: ");
                for(String license : licenses) {
                    embedBuilder.addField("", license, false);
                }
                UserUtil.sendPrivateMessage(message.getAuthor(), embedBuilder.build());
            }
        } else {
            final License license = LicenseSystem.INSTANCE.getApi().createLicense();
            if(license != null) {
                UserUtil.sendPrivateMessage(message.getAuthor(), "License Code: " + license.getLicenseCode());
            } else {
                final EmbedBuilder embedBuilder = new EmbedBuilder()
                        .setColor(Color.RED)
                        .setDescription("Could not create license.");
                TextUtil.sendEmbed(message, embedBuilder.build(), 5);
            }
        }
    }
}
