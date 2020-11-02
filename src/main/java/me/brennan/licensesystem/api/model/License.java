package me.brennan.licensesystem.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class License {

    @SerializedName("code")
    private String licenseCode;

    @SerializedName("hwid")
    private String hwid;

    @SerializedName("status")
    private int status;

    @SerializedName("discord_id")
    private String discordID;

    @SerializedName("notes")
    private List<String> notes;

    public void setHwid(String hwid) {
        this.hwid = hwid;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void addNote(String note) {
        this.notes.add(note);
    }

    public List<String> getNotes() {
        return notes;
    }

    public String getHwid() {
        return hwid;
    }

    public String getDiscordID() {
        return discordID;
    }

    public String getLicenseCode() {
        return licenseCode;
    }
}
