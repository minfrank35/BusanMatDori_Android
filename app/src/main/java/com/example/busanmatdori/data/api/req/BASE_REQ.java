package com.example.busanmatdori.data.api.req;

import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * [공통] 공통 REQ
 * 요청할때마다 요청데이터를 이걸로 감싸서 요청
 * @author 김성민
 * @since 2025/01/06
 */
public class BASE_REQ<T> {

    private String timestamp;    // 요청 시간 (ISO 8601 형식)
    private String osInfo;       // OS 정보
    private T payload;           // 요청 데이터

    // 기본 생성자
    public BASE_REQ() {
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).format(new Date());
        this.osInfo = "Android " + Build.VERSION.RELEASE;
    }

    // 파라미터가 있는 생성자
    public BASE_REQ(T payload) {
        this();
        this.payload = payload;
    }

    // Getter 및 Setter
    public String getTimestamp() {
        return timestamp;
    }

    public String getOsInfo() {
        return osInfo;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    // toString() 오버라이드
    @Override
    public String toString() {
        return "BASE_REQ{" +
                "timestamp='" + timestamp + '\'' +
                ", osInfo='" + osInfo + '\'' +
                ", payload=" + payload +
                '}';
    }
}
