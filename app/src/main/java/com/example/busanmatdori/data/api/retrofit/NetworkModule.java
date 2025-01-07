package com.example.busanmatdori.data.api.retrofit;

import com.example.busanmatdori.AppConf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * [공통] 레트로핏 네트워크 모듈
 * @author 김성민
 * @since 2025/01/06
 */
public class NetworkModule {

    // Retrofit 인스턴스를 생성하는 메소드
    public static Retrofit provideRetrofit(String baseUrl) {
        OkHttpClient okHttpClient = provideOkHttpClient();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .build();
    }

    // Gson 객체 제공
    private static Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    // OkHttpClient 객체 제공
    private static OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        if(AppConf.IS_DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }


        return builder.build();
    }
}