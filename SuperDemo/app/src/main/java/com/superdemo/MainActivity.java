package com.superdemo;

import android.widget.LinearLayout;

import com.android.core.ui.BaseActivity;
import com.android.core.widget.TabStripView;
import com.superdemo.home.widget.HomeFragment;
import com.superdemo.image.widget.ImageFragment;
import com.superdemo.movie.widget.MovieFragment;
import com.superdemo.our.widget.OurFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tbv_main)
    TabStripView navigateTabBar;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        //设置对应Fragment的显示控件
        navigateTabBar.setFrameLayoutId(R.id.fg_main);
        //对应xml中的navigateTabTextColor导航字体默认颜色
        navigateTabBar.setTabTextColor(getResources().getColor(R.color.gray));
        //对应xml中的navigateTabSelectedTextColor导航字体的选中颜色
        navigateTabBar.setSelectedTabTextColor(getResources().getColor(R.color.gre));

        //添加对应的Fragment、图标、标题名
        navigateTabBar.addTab(HomeFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_home, R.mipmap.ic_tab_bar_home_selected, R.string.abc_tab_text_home));
        navigateTabBar.addTab(ImageFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_find, R.mipmap.ic_tab_bar_find_selected, R.string.abc_tab_text_image));
        navigateTabBar.addTab(MovieFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_find, R.mipmap.ic_tab_bar_find_selected, R.string.abc_tab_text_movie));
        navigateTabBar.addTab(OurFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_bar_person, R.mipmap.ic_tab_bar_person_selected, R.string.abc_tab_text_our));
    }

    @Override
    protected void onInitData() {

    }

}
