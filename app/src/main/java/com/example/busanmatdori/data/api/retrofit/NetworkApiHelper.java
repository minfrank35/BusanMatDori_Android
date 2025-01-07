package com.example.busanmatdori.data.api.retrofit;

import android.content.Context;


import com.example.busanmatdori.ui.components.alert.AlertDisplayer;
import com.example.busanmatdori.ui.components.alert.CommAlertDialog;
import com.example.busanmatdori.ui.components.loading.CommLoadingController;
import com.example.busanmatdori.ui.components.loading.LoadingController;
import com.example.busanmatdori.util.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * [공통] 네트워크 API 호출 헬퍼 (오버로딩 버전)
 *  - 네트워크 연결 여부 체크
 *  - 로딩 표시/숨기기 (LoadingController)
 *  - AlertDisplayer를 통한 경고창
 *  - 필요한 파라미터만 설정할 수 있도록 메서드 오버로딩
 */
public class NetworkApiHelper {

    public void setLoadingController(LoadingController loadingController) {
        this.loadingController = loadingController;
    }

    public void setAlertDisplayer(AlertDisplayer alertDisplayer) {
        this.alertDisplayer = alertDisplayer;
    }

    // 공통 콜백
    public interface NetworkApiHelperListener<T> {
        void onSuccess(int apiReqCode, T res);
        void onFailure();
        void onError(int apiReqCode, String errMessage);
    }


    private final String DEFAULT_NO_NETWORK_TITLE   = "네트워크 오류";
    private final String DEFAULT_NO_NETWORK_MESSAGE = "네트워크가 연결되어 있지 않습니다.";
    private final Context context;
    private LoadingController loadingController;
    private AlertDisplayer alertDisplayer;

    public NetworkApiHelper(Context context) {
        this.context = context;
        loadingController = new CommLoadingController(context);
        alertDisplayer = new CommAlertDialog();
    }


    public <T> void sendRequest(
            int apiReqCode,
            Call<T> call,
            NetworkApiHelperListener<T> listener
    ) {
        // 1) 네트워크 체크
        if (!NetworkUtil.isNetworkAvailable(context)) {
            // AlertDisplayer로 네트워크 없음 알림 (옵션)
            if (alertDisplayer != null) {
                alertDisplayer.showAlert(context, DEFAULT_NO_NETWORK_TITLE, DEFAULT_NO_NETWORK_MESSAGE);
            }
            return;
        }

        // 3) 로딩 표시
        if (loadingController != null) {
            loadingController.showLoading("Loading...");
        }

        // 4) Retrofit 비동기 처리
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                // 로딩 숨기기
                if (loadingController != null) {
                    loadingController.hideLoading();
                }

                // 성공/실패 처리
                if (response.isSuccessful() && response.body() != null) {
                    if (listener != null) {
                        listener.onSuccess(apiReqCode, response.body());
                    }
                } else {
                    if (listener != null) {
                        listener.onError(apiReqCode,"Status Code가 실패또는 response body가 없습니다.");
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (loadingController != null) {
                    loadingController.hideLoading();
                }
                if (listener != null) {
                    listener.onError(apiReqCode,"서버에서 결과를 가져올 수 없습니다");
                }
            }
        });
    }

}
