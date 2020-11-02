package me.brennan.licensesystem.command;

import net.dv8tion.jda.api.entities.Message;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public abstract class Command {
    private final String name, description;
    private final EnumPermission enumPermission;

    public Command(String name, String description, EnumPermission enumPermission) {
        this.name = name;
        this.description = description;
        this.enumPermission = enumPermission;
    }

    public abstract void execute(Message message, String[] args);

    public String getName() {
        return name;
    }

    public EnumPermission getEnumPermission() {
        return enumPermission;
    }

    public String getDescription() {
        return description;
    }

    public enum EnumPermission {
        EVERYONE,
        STAFF,
        USER
    }


}
