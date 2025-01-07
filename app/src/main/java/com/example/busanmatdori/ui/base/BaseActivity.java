package com.example.busanmatdori.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.busanmatdori.ui.components.loading.LoadingController;

/**
 * [공통] BaseActivity
 * 여러 액티비티에서 공통적으로 사용할 로직(로딩 다이얼로그, 토스트 등)을
 * 상속받아 재사용하기 쉽게 만든 추상 클래스.
 */
public abstract class BaseActivity extends AppCompatActivity implements LoadingController {

    private ProgressDialog progressDialog;


    // ------------------------------------------------------------------------
    // LoadingController 구현부
    // ------------------------------------------------------------------------
    @Override
    public void showLoading(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);  // 필요 시 true로 변경 가능
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    // ------------------------------------------------------------------------
    // BaseActivity에서 추가 제공하는 편의 메서드
    // ------------------------------------------------------------------------

    /**
     * 기본 메시지("Loading...")로 로딩 다이얼로그를 표시한다.
     */
    public void showLoading() {
        showLoading("Loading...");
    }

    /**
     * 짧은 토스트 메시지
     * @param message 표시할 메시지
     */
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 긴 토스트 메시지
     * @param message 표시할 메시지
     */
    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
