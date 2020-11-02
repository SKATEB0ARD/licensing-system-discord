package me.brennan.licensesystem.command.impl.staff;

import me.brennan.licensesystem.LicenseSystem;
import me.brennan.licensesystem.api.model.License;
import me.brennan.licensesystem.command.Command;
import me.brennan.licensesystem.util.TextUtil;
import me.brennan.licensesystem.util.UserUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.List;

/**
 * @author Brennan
 * @since 11/2/2020
 **/
public class CheckDiscordCommand extends Command {

    public CheckDiscordCommand() {
        super("checkdiscord", "Checks if a user is hooked to a license", EnumPermission.STAFF);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(args != null) {
            if(args.length == 1) {
                final List<Member> members = message.getMentionedMembers();
                if(members.size() > 1) {
                    TextUtil.sendEmbed(message, new EmbedBuilder().setColor(Color.RED).setDescription("You can only @ one person!").build(), 5);
                    return;
                }
                final Member member = members.get(0);
                if(member != null) {
                    final License license = LicenseSystem.INSTANCE.getApi().getDiscordLicense(member.getId());
                    if(license != null) {
                        final EmbedBuilder embedBuilder = new EmbedBuilder()
                                .setColor(Color.GREEN)
                                .setDescription("License Found")
                                .addField("License Code", license.getLicenseCode(), false)
                                .addField("Status", String.valueOf(license.getStatus()), false);
                        if(license.getHwid() != null)
                            embedBuilder.addField("HWID", license.getHwid(), false);
                        if(license.getDiscordID() != null) {
                            final User user = LicenseSystem.INSTANCE.getJda().getUserById(license.getDiscordID());
                            if(user != null) {
                                embedBuilder.addField("Discord User", user.getAsTag(), false);
                                embedBuilder.setThumbnail(user.getAvatarUrl());
                            } else {
                                embedBuilder.addField("Discord User", "Couldn't Fetch", false);
                            }
                        }

                        if(!license.getNotes().isEmpty())
                            license.getNotes().forEach(note -> embedBuilder.addField("Note", note, false));
                        UserUtil.sendPrivateMessage(message.getAuthor(), embedBuilder.build());
                    } else {
                        TextUtil.sendEmbed(message, new EmbedBuilder().setColor(Color.RED).setDescription("No License found under that discord ID!").build(), 5);
                    }
                } else {
                    TextUtil.sendEmbed(message, new EmbedBuilder().setColor(Color.RED).setDescription("User not found on discord!").build(), 5);
                }
            }
        }
    }
}
