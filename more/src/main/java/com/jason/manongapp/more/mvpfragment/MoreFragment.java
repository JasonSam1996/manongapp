package com.jason.manongapp.more.mvpfragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
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

import java.util.ArrayList;
import java.util.List;

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
        setGridRecyclerView();
        setLinearRecyclerView();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            String model = data.getStringExtra("model");
            if (model.equals("user")) {
                UserInfo userInfo = (UserInfo) data.getSerializableExtra("user");
                isLogin = data.getBooleanExtra("isLogin",false);
                username = userInfo.getUsername();
                loginUsername.setText(username);
                mSimpleDraweeView.setBackgroundResource(R.drawable.login_success_userimg);
            }else if (model.equals("sms")) {
                SMSCodeCallBackBean smsCodeCallBackBean = (SMSCodeCallBackBean) data.getSerializableExtra("smsuser");
                isLogin = data.getBooleanExtra("isLogin",false);
                username = smsCodeCallBackBean.getUsername();
                loginUsername.setText(username);
                mSimpleDraweeView.setBackgroundResource(R.drawable.login_success_userimg);
            }else if (model.equals("qqLogin")) {
                isLogin = data.getBooleanExtra("isLogin",false);
                Bundle bundle = data.getExtras();
                String name = null;
                String imageUrl = null;
                if (bundle != null) {
                    SerializableMap serializableMap = (SerializableMap) bundle.get("qqLogin");
                    if (serializableMap != null) {
                        name = serializableMap.getMap().get("name");
                        imageUrl = serializableMap.getMap().get("profile_image_url");
                    }
                }
                if (name != null && imageUrl != null) {
                    loginUsername.setText(name);
                    RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                    roundingParams.setBorder(R.color.red, 1.0f);
                    roundingParams.setRoundAsCircle(true);
                    mSimpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
                    mSimpleDraweeView.setImageURI(Uri.parse(imageUrl));
                }
            }

        }
    }

    @OnClick(R2.id.more_login_head_portrait)
    public void startLogin(View view) {
        if (isLogin) {
            showToast("已经登录");
            return;
        }else {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            this.startActivityForResult(intent, 100);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(false);
    }
}
