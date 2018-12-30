package com.bw.movie.film.fragment;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.bean.HotPlayBean;
import com.bw.movie.film.bean.PlayingBean;
import com.bw.movie.film.p.FilmProsenter;
import com.bw.movie.film.v.HotPlayView;
import com.bw.movie.film.v.PlayingView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *作者:ash
 *TODO:
 *      正在上映
 */
public class PlayingFragment extends BaseFragment {

    private EmptyUtil emptyUtil = new EmptyUtil();
    private ToastUtil toast = new ToastUtil();
    private PlayAdapter mPlayAdapter = new PlayAdapter();


    @BindView(R.id.RecyclerView_detailsfragment)
    RecyclerView mRecyclerViewDetailsfragment;
    Unbinder unbinder;

    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        setRecyclerViewData();
        setData();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
    @Override
    public int initLayoutId() {
        return R.layout.detailsfragment;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initVarisble() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //set recyclerview 数据
    public void setRecyclerViewData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewDetailsfragment.setAdapter(mPlayAdapter);
        mRecyclerViewDetailsfragment.setLayoutManager(linearLayoutManager);
    }

    //set数据
    public void setData(){
        new FilmProsenter(new PlayingView<PlayingBean>() {
            @Override
            public void onDataSuccess(PlayingBean playingBean) {
                List<PlayingBean.ResultBean> result = playingBean.getResult();
                mPlayAdapter.setPlayResult(playingBean.getResult());
                mPlayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onDataFailer(String msg) {
                toast.Toast(msg + "sorry");
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getPlayingBeanObservable(1, 10);
    }

}


/*-----------
 *🖐说明:
 *  适配器
 */
class PlayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private EmptyUtil emptyUtil = new EmptyUtil();
    // 即将上映 || 正在上映 数据 list
    private List<PlayingBean.ResultBean> playresult = new ArrayList<>();

    //即将上映 || 正在上映  数据 set 方法
    public void setPlayResult(List<PlayingBean.ResultBean> playresult) {
        if (emptyUtil.isNull(this.playresult) == false) {
            this.playresult.clear();
        }
        this.playresult.addAll(playresult);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_details, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PlayingBean.ResultBean resultBean = playresult.get(i);
        Holder holder = (Holder) viewHolder;
        holder.setData(resultBean);
    }

    @Override
    public int getItemCount() {
        if(emptyUtil.isNull(playresult)==false){
            return playresult.size();
        }
        return 0;
    }

    private class Holder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView mSimpleDraweeView;
        private final TextView mName;
        private final TextView mIntroduction;

        public Holder(View view) {
            super(view);
            mSimpleDraweeView = view.findViewById(R.id.SimpleDraweeView_detaitem);
            mName = view.findViewById(R.id.name_detaitem);
            mIntroduction = view.findViewById(R.id.Introduction_detaitem);
        }

        public void setData(PlayingBean.ResultBean resultBean) {
            mSimpleDraweeView.setImageURI(Uri.parse(resultBean.getImageUrl()));
            mName.setText(resultBean.getName());
            mIntroduction.setText(resultBean.getSummary());
        }

    }
}