package me.brennan.licensesystem.api;

import me.brennan.licensesystem.api.model.License;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class API {
    private ApiWrapper api;

    public API(String baseUrl, String token) {
        this.api = ApiWrapperService.createWrapperService(baseUrl, token);
    }

    public License createLicense() {
        final Call<License> licenseCall = api.createLicense();
        try {
            final Response<License> licenseResponse = licenseCall.execute();
            if(licenseResponse.isSuccessful())
                return licenseResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public License getLicense(String code) {
        final Call<License> licenseCall = api.getLicense(code);
        try {
            final Response<License> licenseResponse = licenseCall.execute();
            if(licenseResponse.isSuccessful())
                return licenseResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public License updateLicense(License license) {
        final Call<License> licenseCall = api.updateLicense(license.getLicenseCode(), license);
        try {
            final Response<License> licenseResponse = licenseCall.execute();
            if(licenseResponse.isSuccessful())
                return licenseResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public License deleteLicense(String code) {
        final Call<License> licenseCall = api.deleteLicense(code);
        try {
            final Response<License> licenseResponse = licenseCall.execute();
            if(licenseResponse.isSuccessful())
                return licenseResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isDiscordRegistered(String id) {
        final Call<License> licenseCall = api.checkDiscord(id);
        try {
            final Response<License> licenseResponse = licenseCall.execute();
            if(licenseResponse.isSuccessful())
                return licenseResponse.body() != null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public License getDiscordLicense(String id) {
        final Call<License> licenseCall = api.checkDiscord(id);
        try {
             final Response<License> licenseResponse = licenseCall.execute();
             if(licenseResponse.isSuccessful())
                 return licenseResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isHWID(String hwid) {
        final Call<License> licenseCall = api.checkHWID(hwid);
        try {
            final Response<License> licenseResponse = licenseCall.execute();
            if(licenseResponse.isSuccessful())
                return licenseResponse.body() != null;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public License verifyLicense(String code, String id) {
        final Call<License> licenseCall = api.verifyLicense(code, id);
        try {
            final Response<License> licenseResponse = licenseCall.execute();
            if(licenseResponse.isSuccessful())
                return licenseResponse.body();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
