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
import android.widget.Toast;

import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.Particulars.ParticularsAdapder;
import com.bw.movie.cinema.Particulars.bean.MovieListByCinemaIdBean;
import com.bw.movie.cinema.Particulars.bean.MovieResultBean;
import com.bw.movie.cinema.Particulars.presenter.MovieListByCinemaIdPresenter;
import com.bw.movie.cinema.Particulars.view.MovieListByCinemaIdView;
import com.bw.movie.cinema.good.bean.GoodBean;
import com.bw.movie.cinema.good.event.GoodEvent;
import com.bw.movie.cinema.good.presenter.GoodPresenter;
import com.bw.movie.cinema.good.view.GoodView;
import com.bw.movie.cinema.mdetails.bean.MdetailsBean;
import com.bw.movie.cinema.mdetails.presenter.MdetailsPresenter;
import com.bw.movie.cinema.mdetails.view.MdetailsView;
import com.bw.movie.cinema.mevaluate.adapder.MevaluateAdapder;
import com.bw.movie.cinema.mevaluate.bean.MevaResultBean;
import com.bw.movie.cinema.mevaluate.bean.MevaluateBean;
import com.bw.movie.cinema.mevaluate.presenter.MevaluatePresenter;
import com.bw.movie.cinema.mevaluate.view.MevaluateView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private int id;
    private List<MevaResultBean> result;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        List<String> list = new ArrayList<>();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

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
                getPopup(v);
            }
        });
    }

    public void getPopup(View v) {
        View view = View.inflate(ParticularsActivity.this, R.layout.detailedinformation_popu, null);
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ImageView mdowm_detaildin = view.findViewById(R.id.dowm_detaildin);
        int height = getWindowManager().getDefaultDisplay().getHeight();
        popupWindow.showAsDropDown(v, 0, height/2-800);
        RadioButton mevaluate_detaildin = view.findViewById(R.id.evaluate_detaildin);
        RadioButton mdetails_detaildin = view.findViewById(R.id.details_detaildin);
        final LinearLayout mdetails_layout = view.findViewById(R.id.details_layout);
        final LinearLayout mevaluate_layout = view.findViewById(R.id.evaluate_layout);
        TextView textViewTool = view.findViewById(R.id.toll);
        TextView textViewTooltou = view.findViewById(R.id.tolltou);
        TextView textViewMetro = view.findViewById(R.id.metro);
        TextView textViewMetrotou = view.findViewById(R.id.metrotou);
        TextView textViewBus = view.findViewById(R.id.bus);
        TextView textViewBustou = view.findViewById(R.id.bustou);
        TextView textViewTelephone = view.findViewById(R.id.telephone);
        TextView address = view.findViewById(R.id.address);
        RecyclerView recyclerView = view.findViewById(R.id.MecaluateRecy);
        isModetails(textViewTool, textViewMetro, textViewBus, textViewTelephone, address, textViewTooltou, textViewMetrotou, textViewBustou);
        getMevaluate(recyclerView);
        mdetails_detaildin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mdetails_layout.setVisibility(View.GONE);
                mevaluate_layout.setVisibility(View.VISIBLE);
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


    //点赞
    @Subscribe
    public void onSetGood(final com.bw.movie.cinema.event.GoodEvent goodEvent) {
        if (goodEvent.isChecked()) {
            new GoodPresenter(new GoodView<GoodBean>() {

                @Override
                public void onDataSuccess(GoodBean goodBean) {
                    Toast.makeText(ParticularsActivity.this, goodBean.getMessage(), Toast.LENGTH_SHORT).show();
                    if (goodBean.getMessage().contains("成功")) {
                        goodEvent.getmCheckBox().setButtonDrawable(R.drawable.com_icon_praise_selected_hdpi);

                    }
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
            }).getGodos(result.get(goodEvent.getIndex()).getCommentId());
        } else {
            new GoodPresenter(new GoodView<GoodBean>() {

                @Override
                public void onDataSuccess(GoodBean goodBean) {
                    Toast.makeText(ParticularsActivity.this, goodBean.getMessage(), Toast.LENGTH_SHORT).show();
                    if (goodBean.getMessage().contains("成功")) {
                        goodEvent.getmCheckBox().setButtonDrawable(R.drawable.com_icon_praise_selected_hdpi);
                    }
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
            }).getGodos(result.get(goodEvent.getIndex()).getCommentId());
        }
    }


    public void getMevaluate(final RecyclerView recyclerView) {
        new MevaluatePresenter(new MevaluateView<MevaluateBean>() {

            @Override
            public void onDataSuccess(final MevaluateBean mevaluateBean) {
                if (mevaluateBean.getMessage().contains("成功")) {
                    result = mevaluateBean.getResult();
                    MevaluateAdapder mevaluateAdapder = new MevaluateAdapder(result, ParticularsActivity.this);
                    recyclerView.setAdapter(mevaluateAdapder);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ParticularsActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                }


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
        }).getMevaluate(id, 1, 5);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String paricularstlogo = intent.getStringExtra(Constant.LOGO);
        String paricularstname = intent.getStringExtra(Constant.NAME);
        String paricularstaddress = intent.getStringExtra(Constant.ADDRESS);
        String stringExtra = intent.getStringExtra(Constant.TUIJIANID);

        id = Integer.valueOf(stringExtra);

        partimage.setImageURI(Uri.parse(paricularstlogo));
        partname.setText(paricularstname);
        partaddress.setText(paricularstaddress);
        getData();
        particularsAdapder.setCinema(id);


    }


    public void getData() {

        if (id < 6) {
            new MovieListByCinemaIdPresenter(new MovieListByCinemaIdView<MovieListByCinemaIdBean>() {
                @Override
                public void onDataSuccess(MovieListByCinemaIdBean movieListByCinemaIdBean) {

                    List<MovieResultBean> result = movieListByCinemaIdBean.getResult();
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
            }).getMovieByBean(id);
        } else {
            new MovieListByCinemaIdPresenter(new MovieListByCinemaIdView<MovieListByCinemaIdBean>() {
                @Override
                public void onDataSuccess(MovieListByCinemaIdBean movieListByCinemaIdBean) {

                    List<MovieResultBean> result = movieListByCinemaIdBean.getResult();
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

    public void isModetails(final TextView textViewTool, final TextView textViewMetro, final TextView textViewBus, final TextView telephone, final TextView address, final TextView viewTool, final TextView viewMetro, TextView viewBus) {
        new MdetailsPresenter(new MdetailsView<MdetailsBean>() {

            @Override
            public void onDataSuccess(MdetailsBean mdetailsBean) {

                String vehicleRoute = mdetailsBean.getResult().getVehicleRoute();
                String[] split = vehicleRoute.split("。");
                if (split.length == 0) {
                    split = null;
                } else if (split.length == 1) {
                    textViewBus.setText(split[0]);
                    telephone.setText(mdetailsBean.getResult().getPhone());
                    address.setText(mdetailsBean.getResult().getAddress());
                    viewTool.setVisibility(View.GONE);
                    viewMetro.setVisibility(View.GONE);
                } else {
                    telephone.setText(mdetailsBean.getResult().getPhone());
                    address.setText(mdetailsBean.getResult().getAddress());
                    textViewBus.setText(split[1]);
                    textViewTool.setText(split[2]);
                    textViewMetro.setText(split[0]);
                }


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
        }).getMdetails(id);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
