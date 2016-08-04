package com.android.core.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.core.model.control.LogicProxy;
import com.android.core.widget.LoadingView;

import butterknife.ButterKnife;
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    private LoadingView mLoadinView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutResource(), container, false);
        ButterKnife.bind(this, rootView);
        mLoadinView = new LoadingView(getActivity());
        onInitView();
        onInitData();
        return rootView;
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView();

    protected abstract void onInitData();

    public void showLoadingView() {
        mLoadinView.show();
    }

    public void hideLoadingView() {
        mLoadinView.hide();
    }

    //获得该页面的实例
    public <T> T getLogicImpl(Class cls, Object o) {
        return LogicProxy.getInstance().bind(cls, o);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
