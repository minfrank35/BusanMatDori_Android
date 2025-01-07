package com.example.busanmatdori.data.api.res;

/**
 * [공통] 공통 REQ
 * 요청할때마다 응답데이터를 이걸로 감싸서 요청
 * @author 김성민
 * @since 2025/01/06
 */
public class BASE_RES<T> {

    // 결과 코드 (상태 코드)
    private String resultCode;

    // 결과 메시지
    private String resultMsg;

    // 결과 데이터 (제네릭 타입으로 다양한 데이터 타입을 받을 수 있음)
    private T resultData;

    // 기본 생성자
    public BASE_RES() {
    }

    // 파라미터가 있는 생성자
    public BASE_RES(String resultCode, String resultMsg, T resultData) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    // Getter 및 Setter

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getResultData() {
        return resultData;
    }

    public void setResultData(T resultData) {
        this.resultData = resultData;
    }

    // toString() 오버라이드 (디버깅 용도로 유용)
    @Override
    public String toString() {
        return "BASE_RES{" +
               "resultCode='" + resultCode + '\'' +
               ", resultMsg='" + resultMsg + '\'' +
               ", resultData=" + resultData +
               '}';
    }
}
