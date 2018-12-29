package com.bw.movie.film.activity;

import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.custom.CustomViewpager;

import butterknife.BindView;
import butterknife.ButterKnife;

/*-----------
 *🖐todo:说明: 影片详情
 *  点击 进入详情
 */
public class DetailsActivity extends BaseActivity {

    @BindView(R.id.Positioning_item_carouse)
    CheckBox PositioningItemCarouse;
    @BindView(R.id.rbtn_hot)
    RadioButton rbtnHot;
    @BindView(R.id.rbtn_paly)
    RadioButton rbtnPaly;
    @BindView(R.id.rbtn_sugu)
    RadioButton rbtnSugu;
    @BindView(R.id.rg_datails)
    RadioGroup rgDatails;
    @BindView(R.id.vp_datails)
    CustomViewpager vpDatails;

    @Override
    public void initView() {
        ButterKnife.bind(this);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_details;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }





















}
