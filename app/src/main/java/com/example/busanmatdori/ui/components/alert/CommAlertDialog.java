package com.example.busanmatdori.ui.components.alert;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.example.busanmatdori.R;
import com.example.busanmatdori.util.LogM;

public class CommAlertDialog extends DialogFragment implements AlertDisplayer{

    private Button yesButton;

    private TextView tv_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        yesButton = container.findViewById(R.id.yesButton);
        tv_title = container.findViewById(R.id.confirmTextView);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    public void showAlert(Context context, String title, String message) {
        if (context instanceof FragmentActivity) {
            FragmentActivity fragmentActivity = (FragmentActivity) context;
            tv_title.setText(title);
            // DialogFragment 표시
            this.show(fragmentActivity.getSupportFragmentManager(), "CommAlertDialog");
        } else {
            LogM.e("CommAlertDialog","경고창이 뜨지 않습니다. 액티비티인지여부 확인해주세요");
        }
    }
}
