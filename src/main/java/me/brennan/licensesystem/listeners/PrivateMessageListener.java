package me.brennan.licensesystem.listeners;

import me.brennan.licensesystem.LicenseSystem;
import me.brennan.licensesystem.api.model.License;
import me.brennan.licensesystem.util.TextUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class PrivateMessageListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        super.onPrivateMessageReceived(event);
        if(event.getAuthor() != LicenseSystem.INSTANCE.getJda().getSelfUser()) {
            final String content = event.getMessage().getContentRaw();
            final Message message = event.getMessage();

            if(content.startsWith("!")) {
                String command = content.split(" ")[0].replaceFirst("!", "");
                if(command.equalsIgnoreCase("hwid")) {
                    String[] args = null;
                    if (content.split(" ").length > 1) {
                        args = content.replaceFirst("!" + command + " ", "").split(" ");
                    }

                    if(args != null) {
                        final String hwid = args[0];

                        if(!LicenseSystem.INSTANCE.getApi().isDiscordRegistered(event.getAuthor().getId())) {
                            event.getMessage().getChannel().sendMessage("There is no license hooked to your account!.").queue();
                            return;
                        }

                        if(LicenseSystem.INSTANCE.getApi().isHWID(hwid)) {
                            event.getMessage().getChannel().sendMessage("That HWID is already hooked to a license.").queue();
                            return;
                        }
                        final License license = LicenseSystem.INSTANCE.getApi().getDiscordLicense(event.getAuthor().getId());
                        if(license != null) {
                            license.setHwid(hwid);

                            final License updatedLicense = LicenseSystem.INSTANCE.getApi().updateLicense(license);

                            if(updatedLicense != null) {
                                final EmbedBuilder embedBuilder = new EmbedBuilder()
                                        .setColor(Color.GREEN)
                                        .setDescription("Updated your license.")
                                        .addField("HWID", updatedLicense.getHwid(), false)
                                        .addField("License Code", updatedLicense.getLicenseCode(), false);
                                TextUtil.sendPrivateEmbed(message, embedBuilder.build());
                            } else {
                                final EmbedBuilder embedBuilder = new EmbedBuilder()
                                        .setColor(Color.RED)
                                        .setDescription("Could not update your license at this time!");
                                TextUtil.sendPrivateEmbed(message, embedBuilder.build());
                            }
                        } else {
                            final EmbedBuilder embedBuilder = new EmbedBuilder()
                                    .setColor(Color.RED)
                                    .setDescription("Could not verify that there is a license hooked to your discord!")
                                    .addField("ERROR", "If you believe this to be a error please contact staff.", false);
                            TextUtil.sendPrivateEmbed(message, embedBuilder.build());
                        }


                    }

                }
            } else {
                if(LicenseSystem.INSTANCE.getApi().isDiscordRegistered(event.getAuthor().getId())) {
                    event.getMessage().getChannel().sendMessage("A license is already hooked to your discord.").queue();
                    return;
                }

                final License license = LicenseSystem.INSTANCE.getApi().verifyLicense(content, event.getAuthor().getId());
                if(license != null) {
                    final EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setColor(Color.GREEN)
                            .setDescription("License has been hooked to your discord!")
                            .addField("HWID", "To set your HWID please message me !hwid <hwid>.", false)
                            .addField("Reset HWID", "To reset your HWID please contact a staff member.", false)
                            .addField("Releasing","To unhook your discord from a license please contact staff.", false);
                    TextUtil.sendPrivateEmbed(message, embedBuilder.build());

                    if(LicenseSystem.INSTANCE.getGuild() != null) {
                        LicenseSystem.INSTANCE
                                .getGuild()
                                .addRoleToMember(event.getAuthor().getId(), LicenseSystem.INSTANCE.getUserRole()).queue();
                    }
                } else {
                    final EmbedBuilder embedBuilder = new EmbedBuilder()
                            .setColor(Color.RED)
                            .setDescription("Could not verify license! Contact a staff member if you believe this ia error.");
                    TextUtil.sendPrivateEmbed(message, embedBuilder.build());
                }
            }
        }
    }
}
