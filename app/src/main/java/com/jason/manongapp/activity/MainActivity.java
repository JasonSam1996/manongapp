package com.jason.manongapp.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jason.manongapp.R;
import com.jason.manongapp.base.BaseActivity;
import com.jason.manongapp.blog.fragment.BlogFragment;
import com.jason.manongapp.diary.fragment.DiaryFragment;
import com.jason.manongapp.find.fragment.FindFragment;
import com.jason.manongapp.index.fragment.IndexFragment;
import com.jason.manongapp.more.mvpfragment.MoreFragment;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_bar_rbtally)
    RadioButton main_bar_rbtally;

    @BindView(R.id.main_bar_rbbiary)
    RadioButton main_bar_rbbiary;

//    @BindView(R.id.main_bar_rbblog)
//    RadioButton main_bar_rbblog;

    @BindView(R.id.main_bar_rbfind)
    RadioButton main_bar_rbfind;

    @BindView(R.id.main_bar_rbmore)
    RadioButton main_bar_rbmore;

    @BindView(R.id.main_bar_rg)
    RadioGroup main_bar_rg;

    private Fragment tallyFragment;

    private FragmentTransaction transaction;

    private Fragment currentFragment = new Fragment();
    private DiaryFragment diaryFragment = new DiaryFragment();
//    private BlogFragment blogFragment = new BlogFragment();
    private FindFragment findFragment = new FindFragment();
    private MoreFragment moreFragment = new MoreFragment();

    /*@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView() {
        main_bar_rg.check(R.id.main_bar_rbtally);
        tallyFragment = new IndexFragment();
//        transaction.show(tallyFragment).commit();
        switchFragment(tallyFragment).commit();
//        transaction.replace(R.id.mian_framelayout,tallyFragment);
//        transaction.commit();
        main_bar_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_bar_rbtally:
//                switchContent(tallyFragment,tallyFragment);
                        switchFragment(tallyFragment).commit();
                        break;
                    case R.id.main_bar_rbbiary:
                        switchFragment(diaryFragment).commit();
                        break;
                    case R.id.main_bar_rbfind:
                        switchFragment(findFragment).commit();
                        break;
                    case R.id.main_bar_rbmore:
//                boolean isFirst = true;
                        switchFragment(moreFragment).commit();


                /*if (getToolBarGone()) {
                    loadingDialog.dismiss();
                }*/
                        break;
                    default:
                        Logger.i("监听错误");
                }
            }
        });
    }

    /*@OnClick({R.id.main_bar_rbtally, R.id.main_bar_rbbiary, R.id.main_bar_rbblog, R.id.main_bar_rbfind, R.id.main_bar_rbmore})
    public void onClick(View view) {

    }*/


    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.mian_framelayout, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

}
