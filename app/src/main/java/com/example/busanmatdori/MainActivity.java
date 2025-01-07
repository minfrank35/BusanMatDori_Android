package com.example.busanmatdori;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.busanmatdori.data.api.res.TodoItem;
import com.example.busanmatdori.data.api.retrofit.ApiClient;
import com.example.busanmatdori.data.api.retrofit.ApiService;
import com.example.busanmatdori.data.api.retrofit.NetworkApiHelper;
import com.example.busanmatdori.ui.base.BaseActivity;
import com.example.busanmatdori.util.LogM;

import retrofit2.Call;

public class MainActivity extends BaseActivity {

    public int TEST_REQ_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //TODO : TEST용 API
        ApiService apiService = ApiClient.getApiService();
        Call<TodoItem> call = apiService.test();
        new NetworkApiHelper(this).sendRequest(TEST_REQ_CODE, call, new NetworkApiHelper.NetworkApiHelperListener<TodoItem>() {
            @Override
            public void onSuccess(int apiReqCode, TodoItem res) {
                LogM.d("test", res.toString());
                showToast(res.toString());
            }

            @Override
            public void onFailure() {

            }

            @Override
            public void onError(int apiReqCode, String errMessage) {
                LogM.d("test", errMessage);
            }
        });

        //TODO : TEST용 API STOP
    }
}