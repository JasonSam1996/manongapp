package com.jason.manongapp.setting;

import android.app.Dialog;

import com.jason.manongapp.base.mvp.BasePresenter;
import com.jason.manongapp.base.mvp.BaseView;
import com.jason.manongapp.setting.bean.SettingBean;
import com.jason.manongapp.setting.bean.SettingCallBackBean;

/**
 * MVPPlugin
 */

public class SettingContract {
    interface View extends BaseView {

        void setCacheText(String cacheText);

        String getCacheText();

        boolean cleanCache();

        void showLoading();
        void dismisLoading();

        void showToast(String msg);
        String getVersionName();

        void setVersionNameText(String versionNameText);

        String getOldPwd();

        String getNewPwd();

        String getConfirmPwd();

        void reSetOpenLogin();

        String getObjectId();


    }


    interface Model {
        void reSetPwd(String objectId, String oldPassword, String newPassword, Presenter presenter, Dialog dialog);
    }

    interface Presenter extends BasePresenter<View> {
        void reSetPwdSuccess(SettingCallBackBean settingCallBackBean);

        void reSetPwdError(String errorMsg);
    }

}
