package com.bw.movie.cinema.SeatSelectionActivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Constant;
import com.bw.movie.MyApp;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.SeatSelectionActivity.popu.SeatPopuwindow;
import com.bw.movie.cinema.event.SeatEvent;
import com.bw.movie.custom.SeatTable;
import com.bw.movie.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    private double v2;
    private double v21;


    @Override
    public void initView() {
        ButterKnife.bind(this);
        seatTableView.setGetData(new SeatTable.getDatas() {
            @Override
            public void getDate(int sizes) {
                double v = Double.parseDouble(paerprice);
                v21 = v * sizes;
                cinemaprice.setText(v21+"");
            }
        });

    }
    @Override
    public void initListener() {

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(SeatSelectionActivity.this, R.layout.seatpopuitem, null);
                final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.showAsDropDown(v, -280, -280);
                RadioButton radioButton = view.findViewById(R.id.weixinfu);
                RadioButton radioButton1 = view.findViewById(R.id.zhufubaofu);
                final Button button = view.findViewById(R.id.fukuan);
                ImageView imageView = view.findViewById(R.id.returnfanhui);
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            button.setText("微信支付" + v21);
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
                            button.setText("支付宝支付" + v21);
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

    @Override
    public void initData() {
        final Intent intent = getIntent();
        paername = intent.getStringExtra(Constant.PARTNAME);
        paerprice = intent.getStringExtra(Constant.PARTID);


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
