package com.example.busanmatdori.data.api.retrofit;

import com.example.busanmatdori.data.api.res.TodoItem;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * [공통] 레트로핏 API 인터페이스
 * @author 김성민
 * @since 2025/01/06
 */

// TODO :: Retrofit api 명세 구현
public interface ApiService {

    //TODO : TEST용 API start
    @GET("todos/1")
    public Call<TodoItem> test();

    //TODO : TEST용 API STOP
}
