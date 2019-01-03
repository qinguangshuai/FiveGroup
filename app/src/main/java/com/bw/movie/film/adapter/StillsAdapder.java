package com.bw.movie.film.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.film.bean.DetailBean;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class StillsAdapder extends BaseMultiItemQuickAdapter<DetailBean.ResultBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public StillsAdapder(List<DetailBean.ResultBean> data) {
        super(data);
        addItemType(StillsItem.IMGBIG, R.layout.stillsbig);
        addItemType(StillsItem.IMGSMALL, R.layout.stillssmall);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailBean.ResultBean item) {
        switch (item.getItemType()) {
            case StillsItem.IMGBIG:
               Glide.with(mContext).load(posterList.get(0)).into((ImageView) helper.getView(R.id.imagebig));

                break;
            case StillsItem.IMGSMALL:
              Glide.with(mContext).load(posterList.get(1)).into((ImageView) helper.getView(R.id.imagesmall_one));
              Glide.with(mContext).load(posterList.get(2)).into((ImageView) helper.getView(R.id.imagesmall_two));

                break;
        }
    }


    private List<String> posterList;

    public void setPoster(List<String> posterList) {
        this.posterList = posterList;
    }
}

