package com.jason.manongapp.more.mvpfragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jason.manongapp.base.http.utils.SPUtils;
import com.jason.manongapp.base.mvp.MVPBaseFragment;
import com.jason.manongapp.more.R;
import com.jason.manongapp.more.R2;
import com.jason.manongapp.more.adapter.RecyclerviewGridAdapter;
import com.jason.manongapp.more.adapter.RecyclerviewLinearAdapter;
import com.jason.manongapp.more.bean.SettingItemBean;
import com.jason.manongapp.more.bean.UserItemBean;
import com.jason.manongapp.more.codelogin.bean.SMSCodeCallBackBean;
import com.jason.manongapp.more.decoration.MyItemDecoration;
import com.jason.manongapp.more.login.LoginActivity;
import com.jason.manongapp.more.login.bean.SerializableMap;
import com.jason.manongapp.more.login.bean.UserInfo;
import com.jason.manongapp.setting.SettingActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class MoreFragment extends MVPBaseFragment<MoreContract.View, MorePresenter> implements MoreContract.View {

    @BindView(R2.id.more_user_rlv)
    RecyclerView gridRecyclerView;

    @BindView(R2.id.more_setting_rlv)
    RecyclerView linearRecyclerView;

    @BindView(R2.id.more_login_head_portrait)
    SimpleDraweeView mSimpleDraweeView;

    @BindView(R2.id.more_login_username)
    TextView loginUsername;

    private List<UserItemBean> userItemBeanList;
    private List<SettingItemBean> settingItemBeans;

    private RecyclerviewGridAdapter gridAdapter;
    private RecyclerviewLinearAdapter linearAdapter;

    private int[] userImages;
    private int[] settingImages;
    private int[] userTexts;
    private int[] settingTexts;

    private String username;

    private boolean isLogin;

    @Override
    public void initView() {
        String auth_msg = SPUtils.get("auth_msg", "");
        username = SPUtils.get("username","");
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(auth_msg)) {
            if (!TextUtils.isEmpty(SPUtils.get("image_url",""))) {
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                roundingParams.setBorder(R.color.red, 1.0f);
                roundingParams.setRoundAsCircle(true);
                mSimpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
                mSimpleDraweeView.setImageURI(Uri.parse(SPUtils.get("image_url","")));
            }
            loginUsername.setText(username);
            isLogin = true;
        }
        mPresenter.initView();

    }

    @Override
    public void initData() {
        userImages = new int[]{
                R.drawable.collect,
                R.drawable.draft,
                R.drawable.bill,
                R.drawable.chart,
                R.drawable.album,
                R.drawable.message
        };
        settingImages = new int[]{
                R.drawable.user,
                R.drawable.setting
        };
        userTexts = new int[]{
                R.string.more_collect_text,
                R.string.more_draft_text,
                R.string.more_bill_text,
                R.string.more_chart_text,
                R.string.more_album_text,
                R.string.more_message_text,
        };
        settingTexts = new int[]{
                R.string.more_usercenter_text,
                R.string.more_setting_text
        };
    }

    @Override
    public int initLayout() {
        return R.layout.more_fragment;
    }

    @Override
    public void setGridRecyclerView() {
        userItemBeanList = new ArrayList<>();
        for (int i = 0; i < userTexts.length; i++) {
            userItemBeanList.add(new UserItemBean(userImages[i], userTexts[i]));
        }
        gridAdapter = new RecyclerviewGridAdapter(userItemBeanList);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, RecyclerView.VERTICAL, false));
        gridRecyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
        gridRecyclerView.setAdapter(gridAdapter);
    }

    @Override
    public void setLinearRecyclerView() {
        settingItemBeans = new ArrayList<>();
        for (int i = 0; i < settingTexts.length; i++) {
            settingItemBeans.add(new SettingItemBean(settingImages[i], settingTexts[i]));
        }
        linearAdapter = new RecyclerviewLinearAdapter(settingItemBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        linearRecyclerView.setLayoutManager(linearLayoutManager);
        linearRecyclerView.setAdapter(linearAdapter);
        linearRecyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R2.id.more_login_head_portrait)
    public void startLogin(View view) {
        if (isLogin) {
            Intent intent = new Intent(getContext(), SettingActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        }else {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            this.startActivity(intent);
            getActivity().overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
        }
    }

    @Override
        public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        Logger.i(""+outState);

        outState.putString("more_login_username",username);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){//加上判断
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginCallBack(String string){
        com.orhanobut.logger.Logger.i("loginCallBack："+string);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginCallBack(UserInfo info){
        String image_url = "https://iplaygame91.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/f/i/file_9.png";
        SPUtils.put("image_url",image_url);
        isLogin = true;
        username = info.getUsername();
        loginUsername.setText(username);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(R.color.red, 1.0f);
        roundingParams.setRoundAsCircle(true);
        mSimpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
        mSimpleDraweeView.setImageURI(Uri.parse(image_url));
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginCallBack(SMSCodeCallBackBean smsCodeCallBackBean){
        String image_url = "https://iplaygame91.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/f/i/file_9.png";
        SPUtils.put("image_url",image_url);
        isLogin = true;
        username = smsCodeCallBackBean.getUsername();
        loginUsername.setText(username);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setBorder(R.color.red, 1.0f);
        roundingParams.setRoundAsCircle(true);
        mSimpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
        mSimpleDraweeView.setImageURI(Uri.parse(image_url));
//        com.orhanobut.logger.Logger.i("loginCallBack："+string);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void LoginCallBack(Map<String, String> qqMap){
        String name = qqMap.get("name");
        String imageUrl = qqMap.get("profile_image_url");
        SPUtils.put("username",name);
        SPUtils.put("image_url",imageUrl);
        if (name != null && imageUrl != null) {
            isLogin = true;
            loginUsername.setText(name);
            RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
            roundingParams.setBorder(R.color.red, 1.0f);
            roundingParams.setRoundAsCircle(true);
            mSimpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
            mSimpleDraweeView.setImageURI(Uri.parse(imageUrl));
        }
//        com.orhanobut.logger.Logger.i("loginCallBack："+qqMap.toString());
    }

}
