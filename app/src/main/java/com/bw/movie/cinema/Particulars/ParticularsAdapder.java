package com.bw.movie.cinema.Particulars;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.cinema.Particulars.bean.MovieResultBean;
import com.bw.movie.cinema.SeatSelectionActivity.activity.SeatSelectionActivity;
import com.bw.movie.cinema.findmovieschedulelist.adapder.FindMovieScheduleListAdapder;
import com.bw.movie.cinema.findmovieschedulelist.bean.FindMovieScheduleListBean;
import com.bw.movie.cinema.findmovieschedulelist.bean.FindResultBean;
import com.bw.movie.cinema.findmovieschedulelist.presenter.FindMovieScheduleListProsenter;
import com.bw.movie.cinema.findmovieschedulelist.view.FindMovieScheduleListView;
import java.util.List;
import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;


public class ParticularsAdapder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int id;
    private Context mContext;
    private List<String> mList;
    private ParticularsViewHolder mParticularsViewHolder;
    private FindMovieScheduleListAdapder mFindMovieScheduleListAdapder = new FindMovieScheduleListAdapder();
    private int cinema;

    public void setId(int id) {
        this.id = id;
    }

    public void setCinema(int cinema) {
        cinema = cinema;
    }

    private List<FindResultBean> resultBeans;

    public ParticularsAdapder(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.particularadapderitem, viewGroup, false);
        mParticularsViewHolder = new ParticularsViewHolder(inflate);
        return mParticularsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        //轮播数据
        final PartCoverFlowAdapder partCoverFlowAdapder = new PartCoverFlowAdapder(result, mContext);
        mParticularsViewHolder.recyclerCoverFlow.setAdapter(partCoverFlowAdapder);
        mParticularsViewHolder.recyclerCoverFlow.smoothScrollToPosition(30);
        mParticularsViewHolder.recyclerCoverFlow.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                final int i1 = position % result.size();

                new FindMovieScheduleListProsenter(new FindMovieScheduleListView<FindMovieScheduleListBean>() {
                    @Override
                    public void onDataSuccess(final FindMovieScheduleListBean findMovieScheduleListBean) {
                        mFindMovieScheduleListAdapder.setResultBeans(findMovieScheduleListBean.getResult());
                        //列表数据  适配器
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mParticularsViewHolder.recyclerView.setLayoutManager(linearLayoutManager);
                        mParticularsViewHolder.recyclerView.setAdapter(mFindMovieScheduleListAdapder);
                        mFindMovieScheduleListAdapder.notifyDataSetChanged();
                        mFindMovieScheduleListAdapder.setIsSeatSelection(new FindMovieScheduleListAdapder.IsSeatSelection() {
                            @Override
                            public void getSeatSelection(View view, int posiiton) {
                                Intent intent = new Intent(mContext, SeatSelectionActivity.class);
                                intent.putExtra(Constant.PARTID, findMovieScheduleListBean.getResult().get(i).getPrice() + "");
                                intent.putExtra(Constant.PARTNAME, findMovieScheduleListBean.getResult().get(i).getScreeningHall());
                                intent.putExtra(Constant.CINEMAID, findMovieScheduleListBean.getResult().get(i).getId());
                                mContext.startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onDataFailer(String msg) {
                    }

                    @Override
                    public void onShowLoading() {
                    }

                    @Override
                    public void onHideLoading() {
                    }
                }).getFindMovieScheduleList(1, result.get(i1).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private List<MovieResultBean> result;

    public void Lunbo(List<MovieResultBean> result) {
        if (result != null && result.size() > 0) {
            this.result = result;
        }
    }


    class ParticularsViewHolder extends RecyclerView.ViewHolder {
        RecyclerCoverFlow recyclerCoverFlow;
        RecyclerView recyclerView;

        public ParticularsViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerCoverFlow = itemView.findViewById(R.id.RecyclerCoverFlow_adapder);

            recyclerView = itemView.findViewById(R.id.FindMovieScheduleListrecy);
        }
    }

    private IsId isId;

    public void setIsId(IsId isId) {
        this.isId = isId;
    }

    public interface IsId {
        void getid(int id);
    }
}
