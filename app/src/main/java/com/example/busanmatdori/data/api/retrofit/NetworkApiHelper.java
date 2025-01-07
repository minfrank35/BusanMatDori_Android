package com.example.busanmatdori.data.api.retrofit;

import com.example.busanmatdori.data.api.req.BASE_REQ;
import com.example.busanmatdori.data.api.res.BASE_RES;
import com.example.busanmatdori.ui.components.loading.LoadingController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* 사용예시
*
*       // 1) GET 요청 (fetchUsers)
        //    첫 번째 인자: ApiService에 있는 메서드 이름 (fetchUsers)
        //    두 번째 인자: 요청 식별용 reqCode (REQ_FETCH_USERS)
        NetworkApiHelper.sendGet(
                "fetchUsers",
                REQ_CODE_FETCH_USERS,
                new NetworkApiHelper.NetworkApiHelperListener<List<UserResponse>>() {
                    @Override
                    public void onSuccess(BASE_RES<List<UserResponse>> res, String reqCode) {
                        if (REQ_CODE_FETCH_USERS.equals(reqCode)) {
                            // 성공 처리
                            List<UserResponse> userList = res.getResultData();
                            if (userList != null && !userList.isEmpty()) {
                                for (UserResponse user : userList) {
                                    System.out.println("조회된 사용자: " + user.getName());
                                }
                            }
                            System.out.println("서버 응답 메시지: " + res.getResultMsg());
                        }
                    }

                    @Override
                    public void onFailure() {
                        // 실패 처리
                        System.out.println("사용자 목록 조회 실패");
                    }
                }
        );
*
* */
/**
 * [공통] 네트워크 API 호출 헬퍼
 * Reflection을 통해 특정 메서드를 동적으로 호출하여 Retrofit 요청을 처리한다.
 *
 * @author 김성민
 * @since 2025/01/06
 */
public class NetworkApiHelper {

    private NetworkApiHelper() {
        // 유틸 클래스, 인스턴스화 방지
    }

    public interface NetworkApiHelperListener<T> {
        void onSuccess(BASE_RES<T> res, String reqCode);
        void onFailure();
    }

    /**
     * POST 요청
     * @param loadingController 로딩 UI를 제어할 수 있는 객체(인터페이스)
     * @param methodName        ApiService에 선언된 메서드 이름
     * @param reqCode           요청 식별자
     * @param reqData           요청할 데이터
     * @param listener          요청 결과 콜백
     */
    public static <K, T> void sendPost(
            LoadingController loadingController,
            String methodName,
            String reqCode,
            K reqData,
            final NetworkApiHelperListener<T> listener
    ) {
        try {
            // [1] 요청 시작 시 로딩 표시
            if (loadingController != null) {
                loadingController.showLoading("로딩 중...");
            }

            // [2] BASE_REQ 생성
            BASE_REQ<K> baseReq = new BASE_REQ<>();
            baseReq.setPayload(reqData);

            // [3] Reflection으로 ApiService 메서드 찾기
            Method method = ApiClient.getApiService().getClass()
                    .getMethod(methodName, BASE_REQ.class);

            // [4] 해당 메서드를 호출하여 Call<BASE_RES<T>> 반환
            @SuppressWarnings("unchecked")
            Call<BASE_RES<T>> call = (Call<BASE_RES<T>>) method.invoke(ApiClient.getApiService(), baseReq);

            // [5] 비동기 요청
            call.enqueue(new Callback<BASE_RES<T>>() {
                @Override
                public void onResponse(Call<BASE_RES<T>> call, Response<BASE_RES<T>> response) {
                    // 응답이 오면 로딩 숨김
                    if (loadingController != null) {
                        loadingController.hideLoading();
                    }

                    if (response.isSuccessful() && response.body() != null) {
                        listener.onSuccess(response.body(), reqCode);
                    } else {
                        listener.onFailure();
                    }
                }

                @Override
                public void onFailure(Call<BASE_RES<T>> call, Throwable t) {
                    // 실패 시 로딩 숨김
                    if (loadingController != null) {
                        loadingController.hideLoading();
                    }
                    listener.onFailure();
                }
            });

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            if (loadingController != null) {
                loadingController.hideLoading();
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * GET 요청
     */
    public static <T> void sendGet(
            LoadingController loadingController,
            String methodName,
            String reqCode,
            final NetworkApiHelperListener<T> listener
    ) {
        try {
            // [1] 로딩 표시
            if (loadingController != null) {
                loadingController.showLoading("로딩 중...");
            }

            // [2] Reflection으로 ApiService 메서드 찾기
            Method method = ApiClient.getApiService().getClass().getMethod(methodName);

            // [3] 호출하여 Call<BASE_RES<T>> 반환
            @SuppressWarnings("unchecked")
            Call<BASE_RES<T>> call = (Call<BASE_RES<T>>) method.invoke(ApiClient.getApiService());

            // [4] 비동기 요청
            call.enqueue(new Callback<BASE_RES<T>>() {
                @Override
                public void onResponse(Call<BASE_RES<T>> call, Response<BASE_RES<T>> response) {
                    if (loadingController != null) {
                        loadingController.hideLoading();
                    }

                    if (response.isSuccessful() && response.body() != null) {
                        listener.onSuccess(response.body(), reqCode);
                    } else {
                        listener.onFailure();
                    }
                }

                @Override
                public void onFailure(Call<BASE_RES<T>> call, Throwable t) {
                    if (loadingController != null) {
                        loadingController.hideLoading();
                    }
                    listener.onFailure();
                }
            });

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            if (loadingController != null) {
                loadingController.hideLoading();
            }
            throw new RuntimeException(e);
        }
    }
}
