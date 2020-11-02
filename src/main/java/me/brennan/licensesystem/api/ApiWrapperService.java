package me.brennan.licensesystem.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Brennan
 * @since 10/28/2020
 **/
public class ApiWrapperService {

    public static ApiWrapper createWrapperService(String baseUrl, String token) {
        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            final Request original = chain.request();
            final Request request = original.newBuilder()
                    .header("Accept", "application/json")
                    .header("Authorization", token)
                    .method(original.method(), original.body()).build();
            return chain.proceed(request);
        }).build();
        final Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .create();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        return retrofit.create(ApiWrapper.class);
    }

}
