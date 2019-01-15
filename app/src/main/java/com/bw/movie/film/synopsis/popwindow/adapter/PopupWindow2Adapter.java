package com.bw.movie.film.synopsis.popwindow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.film.details.bean.DetailBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/*
 *作者:ash
 *TODO:
 *
 */
public class PopupWindow2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<DetailBean.ResultBean.ShortFilmListBean> shortFilmList = new ArrayList<>();

    public void setShortFilmList(List<DetailBean.ResultBean.ShortFilmListBean> shortFilmList) {
        this.shortFilmList = shortFilmList;
    }

    public PopupWindow2Adapter(Context context) {
        this.mContext = context;
    }

    class Holder extends RecyclerView.ViewHolder {
        private final JCVideoPlayerStandard mJcVideoPlayerStandard;

        public Holder(View view) {
            super(view);
            mJcVideoPlayerStandard = view.findViewById(R.id.video_item_popvideo);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popvideo, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Holder holder = (Holder) viewHolder;
        holder.mJcVideoPlayerStandard.setUp(shortFilmList.get(i).getVideoUrl(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL);
        Picasso.with(mContext)
                .load(shortFilmList.get(i).getImageUrl())
                .into(holder.mJcVideoPlayerStandard.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return shortFilmList.size();
    }

}
