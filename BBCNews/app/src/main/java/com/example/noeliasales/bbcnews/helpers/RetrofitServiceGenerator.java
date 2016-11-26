package com.example.noeliasales.bbcnews.helpers;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Retrofit service generator.
 * Author: Noelia Sales <noelia.salesmontes@gmail.com
 *
 * It creates a new retrofit service with XML converter and logging enabled.
 */
public class RetrofitServiceGenerator {
    public static final String API_BASE_URL = "http://feeds.bbci.co.uk/";

    /**
     * Creates a basic retrofit service.
     * @param serviceClass service class
     * @return new service instance
     */
    public static <S> S  createService(Class<S> serviceClass) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .baseUrl(API_BASE_URL);

        // Enable logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
