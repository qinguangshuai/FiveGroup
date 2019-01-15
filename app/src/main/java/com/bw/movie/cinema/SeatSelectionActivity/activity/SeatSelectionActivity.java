package com.bw.movie.cinema.SeatSelectionActivity.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
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
import com.bw.movie.custom.SeatTable;
import com.bw.movie.my.ticket.ticketnet.bean.TicketBean;
import com.bw.movie.my.ticket.ticketnet.presenter.TiketPresenter;
import com.bw.movie.my.ticket.ticketnet.view.TicketView;
import com.bw.movie.util.MDSUtil;
import com.bw.movie.util.ToastUtil;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.bean.OrderSuccessBean;
import com.bw.movie.wxapi.presenter.OrderSuccessPresenter;
import com.bw.movie.wxapi.view.OrderSuccessView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SeatSelectionActivity extends BaseActivity {

    @BindView(R.id.seatView)
    SeatTable seatTableView;
    @BindView(R.id.cinemaprice)
    TextView cinemaprice;
    @BindView(R.id.sure)
    ImageView sure;
    @BindView(R.id.seyno)
    ImageView seyno;
    private String paerprice;
    private String paername;
    private double price;
    private int cinemaids;
    private String orderId;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        seatTableView.setGetData(new SeatTable.getDatas() {
            @Override
            public void getDate(final int sizes) {
                getPopup(sizes);
            }


            public void getPopup(final int sizes) {
                double v = Double.parseDouble(paerprice);
                price = v * sizes;
                cinemaprice.setText(price + "");
                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sizes==0){
                            ToastUtil.Toast("请选择作为后下单");
                        }else{
                            getZhiFu(sizes);
                            getIsPopup(v);
                        }

                    }
                });
            }
        });
        seyno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //订单
    public void getZhiFu(int sizes) {
        SharedPreferences login = getSharedPreferences("login", Context.MODE_PRIVATE);
        int userId = login.getInt("userId", -1);
        String s = userId + "" + cinemaids + "" + sizes + "" + "movie";
        String encrypt = MDSUtil.MD5(s);
        new TiketPresenter(new TicketView<TicketBean>() {
            @Override
            public void onDataSuccess(TicketBean ticketBean) {
                Toast.makeText(SeatSelectionActivity.this, ticketBean.getMessage(), Toast.LENGTH_SHORT).show();
                orderId = ticketBean.getOrderId();
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
        }).getTicket(cinemaids, sizes, encrypt);

    }


    @Override
    public void initListener() {


    }

    public void getIsPopup(View v) {
        View view = View.inflate(SeatSelectionActivity.this, R.layout.seatpopuitem, null);
        WindowManager windowManager = getWindowManager();
        int height = windowManager.getDefaultDisplay().getHeight();
        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.popwin_anim_style);
        popupWindow.showAtLocation(v.getRootView(), Gravity.BOTTOM, 0, 0);
        RadioButton radioButton = view.findViewById(R.id.weixinfu);
        RadioButton radioButton1 = view.findViewById(R.id.zhufubaofu);
        final Button button = view.findViewById(R.id.fukuan);
        ImageView imageView = view.findViewById(R.id.returnfanhui);
        Button fukuan = view.findViewById(R.id.fukuan);
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    button.setText("微信支付" + price);
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    button.setText("支付宝支付" + price);
                }
            }
        });
        //点击支付按钮，调用支付接口
        fukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getOrder(popupWindow);

            }
        });
    }

    //支付接口调用
    public void getOrder(final PopupWindow popupWindow) {
        new OrderSuccessPresenter(new OrderSuccessView<OrderSuccessBean>() {
            @Override
            public void onDataSuccess(OrderSuccessBean orderSuccessBean) {
                Toast.makeText(SeatSelectionActivity.this, orderSuccessBean.getMessage(), Toast.LENGTH_SHORT).show();
                WeiXinUtil.weiXinPay(orderSuccessBean);
                popupWindow.dismiss();
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
        }).getOeder(1, orderId);
    }

    @Override
    public void initData() {
        final Intent intent = getIntent();
        paername = intent.getStringExtra(Constant.PARTNAME);
        paerprice = intent.getStringExtra(Constant.PARTID);
        cinemaids = intent.getIntExtra(Constant.CINEMAID, -1);
        seatTableView = (SeatTable) findViewById(R.id.seatView);
        seatTableView.setScreenName(paername);//设置屏幕名称
        seatTableView.setMaxSelected(4);//设置最多选中

        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if (row == 6 && column == 6) {
                    return true;
                }
                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        seatTableView.setData(10, 15);

    }


    @Override
    public int initLayoutId() {
        return R.layout.activity_seat_selection;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }


}
