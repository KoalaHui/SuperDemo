package com.android.core.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.android.core.model.control.LogicProxy;
import com.android.core.widget.LoadingView;

import butterknife.ButterKnife;
public abstract class BaseActivity extends FragmentActivity {

    private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Logcat.d("Activity Location (%s.java:0)", getClass().getSimpleName());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
        mLoadingView = new LoadingView(this);
        onInitView();
        onInitData();
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    protected abstract void onInitData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        // 打开Activity动画
    }

    public void showLoadView() {
        mLoadingView.show();
    }

    public void hideLoadView() {
        mLoadingView.hide();
    }

    //获得该页面的实例
    public <T> T getLogicImpl(Class cls, Object o) {
        return LogicProxy.getInstance().bind(cls, o);
    }

    @Override
    public void finish() {
        super.finish();
        // 关闭动画
    }
}
