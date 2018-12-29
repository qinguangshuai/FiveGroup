package com.bw.movie.base;

public interface IBaseView<T> {
    void onDataSuccess(T t);
    void onDataFailer(String msg);
    void onShowLoading();
    void onHideLoading();
}
