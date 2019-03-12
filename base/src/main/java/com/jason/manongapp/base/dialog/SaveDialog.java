package com.jason.manongapp.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.jason.manongapp.base.R;
import com.jason.manongapp.base.R2;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaveDialog extends Dialog {

    private OnCancelListener onCancelListener;

    private OnYesListener onYesListener;


    public SaveDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_save_dialog);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.base_btn_cancel)
    public void onCancel(View view){
        onCancelListener.OnCancel();
    }
    @OnClick(R2.id.base_btn_confirm)
    public void onYes(View view){
        onYesListener.OnYes();
    }

    public void setOnCancelListener(OnCancelListener onCancelListener){
        this.onCancelListener = onCancelListener;
    }

    public void setOnYesListener(OnYesListener onYesListener){
        this.onYesListener = onYesListener;
    }

    public interface OnCancelListener{
        void OnCancel();
    }

    public interface  OnYesListener{
        void OnYes();
    }

}
