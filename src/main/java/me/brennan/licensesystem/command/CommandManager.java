package me.brennan.licensesystem.command;

import me.brennan.licensesystem.command.impl.VerifyCommand;
import me.brennan.licensesystem.command.impl.staff.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class CommandManager {
    private final List<Command> commands = new LinkedList<>();

    public CommandManager() {
        //everyone commands
        this.commands.add(new VerifyCommand());

        //Staff commands
        this.commands.add(new GetLicenseCommand());
        this.commands.add(new CreateLicenseCommand());
        this.commands.add(new DeleteLicenseCommand());
        this.commands.add(new AddNotesCommand());
        this.commands.add(new CheckDiscordCommand());
    }

    public Command getCommand(String name) {
        for (Command command : commands)
            if (command.getName().equalsIgnoreCase(name)) {
                return command;
        }

        return null;
    }

    public List<Command> getCommands() {
        return commands;
    }
}
