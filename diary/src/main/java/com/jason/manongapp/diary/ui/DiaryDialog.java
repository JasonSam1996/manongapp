package com.jason.manongapp.diary.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.jason.manongapp.diary.R;
import com.jason.manongapp.diary.R2;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DiaryDialog extends Dialog {

    @BindView(R2.id.diary_dialog_edlink)
    EditText edLink;

    private OnCancelOnclickListener onCancelOnclickListener;

    private OnConfirmOnclickListener onConfirmOnclickListener;

    public DiaryDialog(@NonNull Context context) {
        super(context,R.style.MyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_dialog_link);
        ButterKnife.bind(this);
        setCancelable(false);
    }

    public String getEdLinkText(){
        return edLink.getText().toString();
    }

    @OnClick(R2.id.diary_dialog_cancel)
    public void onCancelCallBack(View view){
        if (onCancelOnclickListener != null) {
            onCancelOnclickListener.onNoClick();
        }
    }

    @OnClick(R2.id.diary_dialog_confirm)
    public void onConfirmCallBack(View view){
        if (onConfirmOnclickListener != null) {
            onConfirmOnclickListener.onYesClick();
        }
    }

    public void setCanceloOnclickListener(OnCancelOnclickListener onCancelOnclickListener) {
        this.onCancelOnclickListener = onCancelOnclickListener;
    }

    public void setConfirmOnclickListener(OnConfirmOnclickListener onConfirmOnclickListener){
        this.onConfirmOnclickListener = onConfirmOnclickListener;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface OnConfirmOnclickListener {
        void onYesClick();
    }

    public interface OnCancelOnclickListener {
        void onNoClick();
    }


}
