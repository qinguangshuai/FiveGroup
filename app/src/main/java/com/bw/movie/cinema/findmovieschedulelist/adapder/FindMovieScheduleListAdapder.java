package com.bw.movie.cinema.findmovieschedulelist.adapder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.MyApp;
import com.bw.movie.R;
import com.bw.movie.cinema.findmovieschedulelist.bean.FindMovieScheduleListBean;
import com.bw.movie.cinema.findmovieschedulelist.bean.FindResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2018/12/27    19:37
 * author:张文龙(张文龙)
 * fileName:FindMovieScheduleListAdapder(在poartadapder里适用)
 */
public class FindMovieScheduleListAdapder extends RecyclerView.Adapter<FindMovieScheduleListAdapder.FindMovieScheduleListViewHolder> {

    private List<FindResultBean> resultBeans = new ArrayList<>();

    public void setResultBeans(List<FindResultBean> resultBeans) {

        if (this.resultBeans != null && this.resultBeans.size() > 0) {
            this.resultBeans.clear();
        }
        this.resultBeans.addAll(resultBeans);
    }

    @NonNull
    @Override
    public FindMovieScheduleListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.findmovieschedulelistitem, viewGroup, false);
        return new FindMovieScheduleListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FindMovieScheduleListViewHolder findMovieScheduleListViewHolder, int i) {
        findMovieScheduleListViewHolder.textViewstart.setText(resultBeans.get(i).getBeginTime());
        findMovieScheduleListViewHolder.textViewmovepull.setText(resultBeans.get(i).getScreeningHall());
        findMovieScheduleListViewHolder.textViewovetime.setText(resultBeans.get(i).getEndTime());
        findMovieScheduleListViewHolder.textViewprice.setText(resultBeans.get(i).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        if (resultBeans != null && resultBeans.size() > 0) {
            return resultBeans.size();
        }
        return 0;
    }

    class FindMovieScheduleListViewHolder extends RecyclerView.ViewHolder {
        TextView textViewmovepull;
        TextView textViewstart;
        TextView textViewovetime;
        TextView textViewprice;
        ImageView imageView;

        public FindMovieScheduleListViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewstart = itemView.findViewById(R.id.starttime);
            textViewmovepull = itemView.findViewById(R.id.video_holl);
            textViewprice = itemView.findViewById(R.id.movieprice);
            textViewovetime = itemView.findViewById(R.id.overtime);
            imageView = itemView.findViewById(R.id.nextimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isSeatSelection.getSeatSelection(v,getAdapterPosition());
                }
            });
        }
    }

    private IsSeatSelection isSeatSelection;

    public void setIsSeatSelection(IsSeatSelection isSeatSelection) {
        this.isSeatSelection = isSeatSelection;
    }

    public interface IsSeatSelection {
        void getSeatSelection(View view, int posiiton);
    }
}
