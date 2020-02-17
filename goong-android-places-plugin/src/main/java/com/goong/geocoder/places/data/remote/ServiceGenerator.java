package com.goong.geocoder.places.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS);

    private static Retrofit.Builder searchBuilder = new Retrofit.Builder()
            .baseUrl(ServerConstants.HOST_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());
    private static SearchApi searchApi = createService(SearchApi.class, searchBuilder, httpClientBuilder);

    private static <S> S createService(Class<S> serviceClass, Retrofit.Builder retrofitBuilder,
                                       OkHttpClient.Builder okHttpClientBuilder) {
        Retrofit retrofit = retrofitBuilder.client(okHttpClientBuilder.build()).build();
        return retrofit.create(serviceClass);
    }

    public static SearchApi getSearchApi() {
        if (searchApi == null) {
            searchApi = createService(SearchApi.class, searchBuilder, httpClientBuilder);
        }
        return searchApi;
    }

}