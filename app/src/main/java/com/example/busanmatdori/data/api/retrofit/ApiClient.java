package com.example.busanmatdori.data.api.retrofit;

import static com.example.busanmatdori.AppConf.BASE_URL;

import retrofit2.Retrofit;

/**
 * [공통] 레트로핏 API Client
 * @author 김성민
 * @since 2025/01/06
 */
public class ApiClient {

    private static Retrofit retrofit = null;

    public static ApiService getApiService() {
        if (retrofit == null) {
            retrofit = NetworkModule.provideRetrofit(BASE_URL);  // Base URL 설정
        }
        return retrofit.create(ApiService.class);  // ApiService 인터페이스를 생성
    }
}