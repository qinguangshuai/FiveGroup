package com.bw.movie.my.myoption.prosenter;

import com.bw.movie.base.BasePresenter;
import com.bw.movie.my.myoption.bean.MyOptionEntity;
import com.bw.movie.my.myoption.model.MyOptionModel;
import com.bw.movie.my.myoption.view.MyOpitionView;
import com.bw.movie.net.HttpCallBack;

/*
  我的意见presenter层
* */
public class MyOptionPresenter extends BasePresenter<MyOpitionView> {

    private MyOptionModel mMyOptionModel;

    public MyOptionPresenter(MyOpitionView iBaseView) {
        super(iBaseView);
        mMyOptionModel = new MyOptionModel();
    }

    public void getOption(String context) {
        mMyOptionModel.getOpition(context, new HttpCallBack<MyOptionEntity>() {
            @Override
            public void onSuccess(MyOptionEntity name) {
                getiBaseView().onDataSuccess(name);
            }

            @Override
            public void onFailer(String result) {
                getiBaseView().onDataFailer(result);
            }
        });
    }
}
