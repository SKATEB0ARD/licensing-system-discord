package me.brennan.licensesystem.util.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.brennan.licensesystem.util.config.model.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Brennan
 * @since 11/2/2020
 **/
public class ConfigUtil {
    private final static Gson gson = new GsonBuilder()
            .create();

    public static Config readConfig() throws Exception {
        final File configFile = new File("config.json");
        if(!configFile.exists()) {
            System.out.println("PLEASE CREATE A CONFIG FILE");
            return null;
        }

        final BufferedReader bufferedReader = new BufferedReader(new FileReader(configFile));

        return gson.fromJson(bufferedReader, Config.class);
    }

}
