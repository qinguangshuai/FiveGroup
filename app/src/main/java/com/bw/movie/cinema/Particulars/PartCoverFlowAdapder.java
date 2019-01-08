package com.bw.movie.cinema.Particulars;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * date:2018/12/27    14:19
 * author:张文龙(张文龙)
 * fileName:PartCoverFlowAdapder
 */
public class PartCoverFlowAdapder extends RecyclerView.Adapter<PartCoverFlowAdapder.CoverFlowViewHolder> {

    private List<MovieListByCinemaIdBean.ResultBean> list;
    private Context mContext;
    private int p;

    public int getP() {
        return p;
    }

    public PartCoverFlowAdapder(List<MovieListByCinemaIdBean.ResultBean> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CoverFlowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.coverflowitem, viewGroup, false);
        return new CoverFlowViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CoverFlowViewHolder coverFlowViewHolder, int i) {
        int i1 = i % list.size();
        p = i1;
        coverFlowViewHolder.simpleDraweeView.setImageURI(Uri.parse(list.get(i1).getImageUrl()));
        long releaseTime = list.get(i1).getReleaseTime();
        GregorianCalendar gc = new GregorianCalendar();
        String s = String.valueOf(releaseTime);
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        coverFlowViewHolder.moviename_item_coveflow.setText(list.get(i1).getName() + "    " + df.format(gc.getTime()));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : Integer.MAX_VALUE;
    }

    class CoverFlowViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView moviename_item_coveflow;

        public CoverFlowViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.simple_coveflow);
            moviename_item_coveflow = itemView.findViewById(R.id.moviename_item_coveflow);
        }
    }
}
