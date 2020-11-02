package me.brennan.licensesystem.util.config.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Brennan
 * @since 11/2/2020
 **/
public class Config {
    @SerializedName("api_url")
    private String apiURL;

    @SerializedName("api_token")
    private String apiToken;

    @SerializedName("discord_token")
    private String discordToken;

    @SerializedName("guild_id")
    private String guildID;

    @SerializedName("user_role_id")
    private String userRoleID;

    @SerializedName("staff_role_id")
    private String staffRoleID;


    public String getApiToken() {
        return apiToken;
    }

    public String getApiURL() {
        return apiURL;
    }

    public String getDiscordToken() {
        return discordToken;
    }

    public String getGuildID() {
        return guildID;
    }

    public String getStaffRoleID() {
        return staffRoleID;
    }

    public String getUserRoleID() {
        return userRoleID;
    }
}
