package me.brennan.licensesystem.command.impl;

import me.brennan.licensesystem.LicenseSystem;
import me.brennan.licensesystem.command.Command;
import me.brennan.licensesystem.util.UserUtil;
import net.dv8tion.jda.api.entities.Message;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class VerifyCommand extends Command {

    public VerifyCommand() {
        super("verify", "Verify a license", EnumPermission.EVERYONE);
    }

    @Override
    public void execute(Message message, String[] args) {
        if(LicenseSystem.INSTANCE.getApi().isDiscordRegistered(message.getAuthor().getId())) {
            UserUtil.sendPrivateMessage(message.getAuthor(), "Your discord is already hooked to a license.");
            return;
        }
        UserUtil.sendPrivateMessage(message.getAuthor(), "Please send me your license code.");
    }
}
