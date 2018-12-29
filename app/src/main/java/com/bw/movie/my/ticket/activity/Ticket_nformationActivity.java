package com.bw.movie.my.ticket.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.my.ticket.bean.TicketFoemationEntity;
import com.bw.movie.my.ticket.prosenter.TicketformationPresenter;
import com.bw.movie.my.ticket.view.TicketformationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Ticket_nformationActivity extends BaseActivity<TicketformationPresenter> implements TicketformationView<TicketFoemationEntity> {

    @BindView(R.id.user_shop_token_adapter_name)
    TextView mUserShopTokenAdapterName;
    @BindView(R.id.user_shop_token_adapter_dingdan)
    TextView mUserShopTokenAdapterDingdan;
    @BindView(R.id.user_shop_token_adapter_cimera_name)
    TextView mUserShopTokenAdapterCimeraName;
    @BindView(R.id.user_shop_token_adapter_address)
    TextView mUserShopTokenAdapterAddress;
    @BindView(R.id.user_shop_token_adapter_date)
    TextView mUserShopTokenAdapterDate;
    @BindView(R.id.user_shop_token_adapter_num)
    TextView mUserShopTokenAdapterNum;
    @BindView(R.id.user_shop_token_adapter_money)
    TextView mUserShopTokenAdapterMoney;
    @BindView(R.id.user_shop_token_adapter_nopay)
    Button mUserShopTokenAdapterNopay;
    @BindView(R.id.ticket_back)
    ImageView mTicketBack;
    @BindView(R.id.user_shop_token_adapter_dingdan_text)
    TextView mUserShopTokenAdapterDingdanText;
    @BindView(R.id.user_shop_token_adapter_cimera_name_text)
    TextView mUserShopTokenAdapterCimeraNameText;
    @BindView(R.id.user_shop_token_adapter_address_text)
    TextView mUserShopTokenAdapterAddressText;
    @BindView(R.id.user_shop_token_adapter_date_text)
    TextView mUserShopTokenAdapterDateText;
    @BindView(R.id.user_shop_token_adapter_num_text)
    TextView mUserShopTokenAdapterNumText;
    @BindView(R.id.user_shop_token_adapter_money_text)
    TextView mUserShopTokenAdapterMoneyText;
    private TicketformationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        presenter = new TicketformationPresenter(this);

    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_ticket_nformation;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public TicketformationPresenter initPresenter() {
        return null;
    }


    @Override
    public void onDataSuccess(TicketFoemationEntity ticketFoemationEntity) {
        Toast.makeText(this, ticketFoemationEntity.getMessage().toString(), Toast.LENGTH_LONG).show();
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

    @OnClick({R.id.user_shop_token_adapter_name, R.id.user_shop_token_adapter_dingdan, R.id.user_shop_token_adapter_cimera_name, R.id.user_shop_token_adapter_address, R.id.user_shop_token_adapter_date, R.id.user_shop_token_adapter_num, R.id.user_shop_token_adapter_money, R.id.user_shop_token_adapter_nopay, R.id.ticket_back, R.id.user_shop_token_adapter_dingdan_text, R.id.user_shop_token_adapter_cimera_name_text, R.id.user_shop_token_adapter_address_text, R.id.user_shop_token_adapter_date_text, R.id.user_shop_token_adapter_num_text, R.id.user_shop_token_adapter_money_text})
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
            case R.id.user_shop_token_adapter_name:
                break;
            case R.id.user_shop_token_adapter_dingdan:
                break;
            case R.id.user_shop_token_adapter_cimera_name:
                break;
            case R.id.user_shop_token_adapter_address:
                break;
            case R.id.user_shop_token_adapter_date:
                break;
            case R.id.user_shop_token_adapter_num:
                break;
            case R.id.user_shop_token_adapter_money:
                break;
            case R.id.user_shop_token_adapter_nopay:
                break;
            case R.id.ticket_back:
                finish();
                break;
            case R.id.user_shop_token_adapter_dingdan_text:
                break;
            case R.id.user_shop_token_adapter_cimera_name_text:
                break;
            case R.id.user_shop_token_adapter_address_text:
                break;
            case R.id.user_shop_token_adapter_date_text:
                break;
            case R.id.user_shop_token_adapter_num_text:
                break;
            case R.id.user_shop_token_adapter_money_text:
                break;
        }
    }
}
