package com.bw.movie.film.synopsis.popwindow.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class StillsAdapder extends BaseMultiItemQuickAdapter<StillsItem, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public StillsAdapder(List<StillsItem> data) {
        super(data);
        addItemType(StillsItem.IMGBIG, R.layout.stillsbig);
        addItemType(StillsItem.IMGSMALL, R.layout.stillssmall);
    }

    @Override
    protected void convert(BaseViewHolder helper,StillsItem item) {
        int position = helper.getPosition();
        if (position<5){
            switch (item.getItemType()) {
                case StillsItem.IMGBIG:

                    Glide.with(mContext).load(posterList.get(position)).into((ImageView) helper.getView(R.id.imagebig));
                    break;
                case StillsItem.IMGSMALL:
                    Glide.with(mContext).load(posterList.get(position)).into((ImageView) helper.getView(R.id.imagesmall_one));
                    Glide.with(mContext).load(posterList.get(position)).into((ImageView) helper.getView(R.id.imagesmall_two));

                    break;
            }
        }else if (position>5){
          position=0;
        }

    }


    private List<String> posterList;

    public void setPoster(List<String> posterList) {
        this.posterList = posterList;
    }
}

