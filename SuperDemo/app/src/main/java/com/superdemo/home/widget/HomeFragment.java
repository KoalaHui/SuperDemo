package com.superdemo.home.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.android.core.control.Toast;
import com.android.core.control.XRecyclerViewHelper;
import com.android.core.ui.BaseFragment;
import com.android.core.widget.CustomViewpager;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.superdemo.R;
import com.superdemo.adapter.CustomViewPageAdapter;
import com.superdemo.adapter.HomeRecyclerAdapter;
import com.superdemo.bean.Classify;
import com.superdemo.home.view.HomeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/8/4 0004.
 */

public class HomeFragment extends BaseFragment implements HomeView<Classify>,XRecyclerView.LoadingListener {


    @Bind(R.id.recly_view)
    XRecyclerView mRecyclerView;

    private Context         mContext;
    private CustomViewpager mCustomViewpager;
    private List<Classify.TngouEntity> classifys = new ArrayList<>();
    private HomeRecyclerAdapter mHomeRecyclerAdapter;

    private int page = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitView() {

    }

    @Override
    protected void onInitData() {
        mContext = getActivity();

    }

    // 广告数据
    public static List<Integer> getAdData() {
        List<Integer> adList = new ArrayList<>();
        adList.add(R.mipmap.abc_adv_1);
        adList.add(R.mipmap.abc_adv_2);
        adList.add(R.mipmap.abc_adv_3);
        return adList;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置一些recyclerview的参数
        XRecyclerViewHelper.init().setLinearLayout(mContext, mRecyclerView);

        //设置广告栏的数据
        View header = View.inflate(mContext, R.layout.abc_viewpager_view, null);
        mCustomViewpager = (CustomViewpager) header.findViewById(R.id.viewpager);
        mRecyclerView.addHeaderView(header);
        CustomViewPageAdapter adapter = new CustomViewPageAdapter(mContext, getAdData());
        mCustomViewpager.setAdapter(adapter);
        mCustomViewpager.updateIndicatorView(getAdData().size(), 0);
        mCustomViewpager.startScorll();

        //设置recyclerView的数据
        mHomeRecyclerAdapter = new HomeRecyclerAdapter(mContext, R.layout.item_compete_classitfy,classifys);
        mRecyclerView.setAdapter(mHomeRecyclerAdapter);
        mRecyclerView.setLoadingListener(this);
        showLoadingView();
        onRefresh();
    }

    @Override
    public void onLoadComplete() {
        Toast.show("请求成功");
        //加载完成需要做的操作
        hideLoadingView();
    }

    /**
     * 网络加载成功后显示数据
     */
    @Override
    public void onShowListData(Classify listData, boolean isMore) {
        if (listData.isStatus()) {
            if (!isMore)
                classifys.clear();
            classifys.addAll(listData.getTngou());
            mHomeRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // mHomeLogic.onLoadRemoteData(false, 1);
                mRecyclerView.refreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                //mHomeLogic.onLoadRemoteData(true, page);
                mRecyclerView.loadMoreComplete();
            }
        }, 2000);
    }
}
