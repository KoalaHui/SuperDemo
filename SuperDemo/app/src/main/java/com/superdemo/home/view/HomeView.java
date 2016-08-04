package com.superdemo.home.view;

/**
 * Created by KoalaHui on 2016/8/4 0004.
 */

public interface HomeView<T> {
    void onLoadComplete();

    void onShowListData(T response, boolean isMore);
}
