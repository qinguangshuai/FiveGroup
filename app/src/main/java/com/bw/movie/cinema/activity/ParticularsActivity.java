package com.bw.movie.cinema.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.Particulars.ParticularsAdapder;
import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.bw.movie.cinema.Particulars.presenter.MovieListByCinemaIdPresenter;
import com.bw.movie.cinema.Particulars.view.MovieListByCinemaIdView;
import com.bw.movie.cinema.mdetails.bean.MdetailsBean;
import com.bw.movie.cinema.mdetails.presenter.MdetailsPresenter;
import com.bw.movie.cinema.mdetails.view.MdetailsView;
import com.bw.movie.cinema.mevaluate.adapder.MevaluateAdapder;
import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;
import com.bw.movie.cinema.mevaluate.presenter.MevaluatePresenter;
import com.bw.movie.cinema.mevaluate.view.MevaluateView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 影院详情页面
 */
public class ParticularsActivity extends BaseActivity {


    @BindView(R.id.partname)
    TextView partname;
    @BindView(R.id.partaddress)
    TextView partaddress;
    @BindView(R.id.partimage)
    SimpleDraweeView partimage;

    @BindView(R.id.recylerview_part)
    RecyclerView recylerviewPart;
    @BindView(R.id.finnishback)
    ImageView finnishback;
    @BindView(R.id.detailedinformation)
    LinearLayout detailedinformation;
    private ParticularsAdapder particularsAdapder;
    private int i;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();

        particularsAdapder = new ParticularsAdapder(this, list);
        recylerviewPart.setAdapter(particularsAdapder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recylerviewPart.setLayoutManager(linearLayoutManager);


    }


    @Override
    public void initListener() {
        //返回键的监听
        finnishback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //电影院信息监听
        detailedinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(ParticularsActivity.this, R.layout.detailedinformation_popu, null);
                final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                ImageView mdowm_detaildin = view.findViewById(R.id.dowm_detaildin);
                popupWindow.showAsDropDown(v, 150, 150);
                RadioButton mevaluate_detaildin = view.findViewById(R.id.evaluate_detaildin);
                RadioButton mdetails_detaildin = view.findViewById(R.id.details_detaildin);
                final LinearLayout mdetails_layout = view.findViewById(R.id.details_layout);
                final LinearLayout mevaluate_layout = view.findViewById(R.id.evaluate_layout);
                TextView textViewTool = view.findViewById(R.id.toll);
                TextView textViewMetro = view.findViewById(R.id.metro);
                TextView textViewBus = view.findViewById(R.id.bus);
                TextView textViewTelephone = view.findViewById(R.id.telephone);
                TextView address = view.findViewById(R.id.address);
                RecyclerView recyclerView = view.findViewById(R.id.MecaluateRecy);
                isModetails(textViewTool, textViewMetro, textViewBus, textViewTelephone, address);
                getMevaluate(recyclerView);
                mdetails_detaildin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mdetails_layout.setVisibility(View.GONE);
                        mevaluate_layout.setVisibility(View.VISIBLE);
                        //  isModetails();
                    }
                });

                mevaluate_detaildin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mdetails_layout.setVisibility(View.VISIBLE);
                        mevaluate_layout.setVisibility(View.GONE);
                    }
                });

                mdowm_detaildin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    public void getMevaluate(final RecyclerView recyclerView) {
        new MevaluatePresenter(new MevaluateView<MevaluateBean>() {

            @Override
            public void onDataSuccess(MevaluateBean mevaluateBean) {
                List<MevaluateBean.ResultBean> result = mevaluateBean.getResult();
                MevaluateAdapder mevaluateAdapder = new MevaluateAdapder(result, ParticularsActivity.this);
                recyclerView.setAdapter(mevaluateAdapder);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ParticularsActivity.this);
                recyclerView.setLayoutManager(linearLayoutManager);
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
        }).getMevaluate(i, 1, 5);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String paricularstlogo = intent.getStringExtra(Constant.LOGO);
        String paricularstname = intent.getStringExtra(Constant.NAME);
        String paricularstaddress = intent.getStringExtra(Constant.ADDRESS);
        String stringExtra = intent.getStringExtra(Constant.TUIJIANID);

            i = Integer.valueOf(stringExtra);

        partimage.setImageURI(Uri.parse(paricularstlogo));
        partname.setText(paricularstname);
        partaddress.setText(paricularstaddress);
        getData();
        particularsAdapder.setCinema(i);


    }


    public void getData() {

        if (i < 6) {
            new MovieListByCinemaIdPresenter(new MovieListByCinemaIdView<MovieListByCinemaIdBean>() {
                @Override
                public void onDataSuccess(MovieListByCinemaIdBean movieListByCinemaIdBean) {

                    List<MovieListByCinemaIdBean.ResultBean> result = movieListByCinemaIdBean.getResult();
                    particularsAdapder.Lunbo(result);
                    particularsAdapder.notifyDataSetChanged();
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
            }).getMovieByBean(i);
        } else {
            new MovieListByCinemaIdPresenter(new MovieListByCinemaIdView<MovieListByCinemaIdBean>() {
                @Override
                public void onDataSuccess(MovieListByCinemaIdBean movieListByCinemaIdBean) {

                    List<MovieListByCinemaIdBean.ResultBean> result = movieListByCinemaIdBean.getResult();
                    particularsAdapder.Lunbo(result);
                    particularsAdapder.notifyDataSetChanged();

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
            }).getMovieByBean(1);
        }

    }

    public void isModetails(final TextView textViewTool, final TextView textViewMetro, final TextView textViewBus, final TextView telephone, final TextView address) {
        new MdetailsPresenter(new MdetailsView<MdetailsBean>() {

            @Override
            public void onDataSuccess(MdetailsBean mdetailsBean) {

                String vehicleRoute = mdetailsBean.getResult().getVehicleRoute();
                String[] split = vehicleRoute.split("。");
                textViewBus.setText(split[1]);
                textViewTool.setText(split[2]);
                textViewMetro.setText(split[0]);
                telephone.setText(mdetailsBean.getResult().getPhone());
                address.setText(mdetailsBean.getResult().getAddress());

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
        }).getMdetails(i);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_particulars;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {

        return null;
    }
}
