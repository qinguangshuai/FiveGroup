package com.bw.movie.film.activity;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.film.bean.CinemaBean;
import com.bw.movie.film.p.FilmProsenter;
import com.bw.movie.film.v.CinemaView;
import com.bw.movie.util.EmptyUtil;
import com.bw.movie.util.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

public class AffiliatedTheaterActivity extends BaseActivity {


    @BindView(R.id.title_affiliated)
    TextView mTitleAffiliated;
    @BindView(R.id.recy_affiliated)
    RecyclerView mRecyAffiliated;
    private int id;
    private EmptyUtil emptyUtil = new EmptyUtil();
    private ToastUtil toast = new ToastUtil();

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", -1);
        getData(id);
        setmRecyAffiliated();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_affiliated_theater;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    //setRecyclerView 数据
    public void setmRecyAffiliated() {

    }

    //请求数据
    public void getData(int id) {
        new FilmProsenter(new CinemaView<CinemaBean>() {
            @Override
            public void onDataSuccess(CinemaBean cinemaBean) {
                setAdapter(cinemaBean);
            }

            @Override
            public void onDataFailer(String msg) {
                new ToastUtil().Toast(msg);
            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onHideLoading() {

            }
        }).getCinemaBeanObservable(id);
    }


    //adapter
    public void setAdapter(final CinemaBean cinemaBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyAffiliated.setLayoutManager(linearLayoutManager);
        mRecyAffiliated.setAdapter(new RecyclerView.Adapter() {
            class Holder extends RecyclerView.ViewHolder {

                private final SimpleDraweeView mImg;
                private final TextView mName;
                private final TextView mDetails;
                private final TextView mKm;
                private final CheckBox mGood;

                public Holder(View view) {
                    super(view);
                    mImg = view.findViewById(R.id.img_item_affililated);
                    mName = view.findViewById(R.id.name_item_affililated);
                    mDetails = view.findViewById(R.id.details_item_affililated);
                    mKm = view.findViewById(R.id.km_item_affililated);
                    mGood = view.findViewById(R.id.good_item_affililated);
                }
            }
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_affililated, viewGroup, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                CinemaBean.ResultBean resultBean = cinemaBean.getResult().get(i);
                Holder holder = (Holder) viewHolder;
                holder.mGood.setChecked(resultBean.getFollowCinema()==1?true:false);
                holder.mName.setText(resultBean.getName());
                holder.mImg.setImageURI(Uri.parse(resultBean.getLogo()));
                holder.mKm.setText("待获取");
                holder.mDetails.setText(resultBean.getAddress());
            }

            @Override
            public int getItemCount() {
                if (emptyUtil.isNull(cinemaBean.getResult()) == false) {
                    return cinemaBean.getResult().size();
                } else {
                    return 0;
                }
            }
        });

    }


}
