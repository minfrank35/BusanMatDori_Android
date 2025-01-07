package com.example.busanmatdori.util;

import android.util.Log;

import com.example.busanmatdori.AppConf;

/**
 * [공통] 개발용 로그 출력 클래스
 * @author 김성민
 * @since 2025/01/06
 */
public class LogM {

    // 로그를 출력할 기준을 설정 (AppConf.IS_DEBUG)
    private static final boolean IS_DEBUG = AppConf.IS_DEBUG;

    // Debug 로그
    public static void d(String tag, String message) {
        if (IS_DEBUG) {
            Log.d(tag, message);
        }
    }

    // Info 로그
    public static void i(String tag, String message) {
        if (IS_DEBUG) {
            Log.i(tag, message);
        }
    }

    // Warning 로그
    public static void w(String tag, String message) {
        if (IS_DEBUG) {
            Log.w(tag, message);
        }
    }

    // Error 로그
    public static void e(String tag, String message) {
        if (IS_DEBUG) {
            Log.e(tag, message);
        }
    }

    // Verbose 로그
    public static void v(String tag, String message) {
        if (IS_DEBUG) {
            Log.v(tag, message);
        }
    }

    // Assert 로그
    public static void wtf(String tag, String message) {
        if (IS_DEBUG) {
            Log.wtf(tag, message);
        }
    }
}
