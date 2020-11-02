package me.brennan.licensesystem.api;

import me.brennan.licensesystem.api.model.License;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public interface ApiWrapper {

    @POST("licenses")
    Call<License> createLicense();

    @GET("licenses/{code}")
    Call<License> getLicense(@Path("code") String code);

    @PUT("licenses/{code}")
    Call<License> updateLicense(@Path("code") String code, @Body License license);

    @DELETE("licenses/{code}")
    Call<License> deleteLicense(@Path("code") String code);

    @PUT("licenses/verify/{code}/{discord_id}")
    Call<License> verifyLicense(@Path("code") String code, @Path("discord_id") String id);

    @GET("licenses/check/discord/{discord_id}")
    Call<License> checkDiscord(@Path("discord_id") String id);

    @GET("licenses/check/hwid/{hwid}")
    Call<License> checkHWID(@Path("hwid") String hwid);

}
