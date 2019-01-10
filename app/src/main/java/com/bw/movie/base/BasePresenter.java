package com.bw.movie.base;
/*
  basepresenter
* */
public class BasePresenter<T extends IBaseView> {

    private T iBaseView;

    public BasePresenter(T iBaseView) {
        this.iBaseView = iBaseView;
    }

    public T getiBaseView() {
        return iBaseView;
    }

    public void onDestory(){
        if(iBaseView!=null){
            iBaseView=null;
        }
    }
}
