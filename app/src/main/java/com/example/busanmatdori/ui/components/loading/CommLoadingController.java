package com.example.busanmatdori.ui.components.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.busanmatdori.R;

public class CommLoadingController extends Dialog implements LoadingController{

    public CommLoadingController(@NonNull Context context) {
        super(context);
    }

    public CommLoadingController(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CommLoadingController(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_loading_circular_bar);

        setCancelable(false);

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void showLoading(String message) {
        this.show();
    }

    @Override
    public void hideLoading() {
        this.hide();
    }
}
