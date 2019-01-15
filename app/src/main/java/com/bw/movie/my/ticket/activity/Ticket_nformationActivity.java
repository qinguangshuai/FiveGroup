package com.bw.movie.my.ticket.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.error.AppManager;
import com.bw.movie.my.ticket.fragment.TickFragmentOne;
import com.bw.movie.my.ticket.fragment.TicketFragmentTwo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 购票activity
 * */

public class Ticket_nformationActivity extends BaseActivity {
    @BindView(R.id.ticket_rb1)
    RadioButton ticketRb1;
    @BindView(R.id.ticket_rb2)
    RadioButton ticketRb2;
    @BindView(R.id.ticket_rg)
    RadioGroup ticketRg;
    @BindView(R.id.ticket_fragment)
    FrameLayout ticketFragment;
    @BindView(R.id.ticket_pager)
    ViewPager ticketPager;
    @BindView(R.id.ticket_image)
    ImageView ticketImage;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initListener() {
        mManager = getSupportFragmentManager();
        mTransaction = mManager.beginTransaction();

        final TickFragmentOne tickFragmentOne = new TickFragmentOne();
        final TicketFragmentTwo ticketFragmentTwo = new TicketFragmentTwo();

        mTransaction.add(R.id.ticket_fragment,tickFragmentOne);
        mTransaction.add(R.id.ticket_fragment,ticketFragmentTwo);

        mTransaction.show(tickFragmentOne);
        mTransaction.hide(ticketFragmentTwo);

        mTransaction.commit();

        ticketRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = mManager.beginTransaction();
                switch (checkedId){
                    case R.id.ticket_rb1:
                        fragmentTransaction.show(tickFragmentOne);
                        fragmentTransaction.hide(ticketFragmentTwo);
                        break;
                    case R.id.ticket_rb2:
                        fragmentTransaction.show(ticketFragmentTwo);
                        fragmentTransaction.hide(tickFragmentOne);
                        break;
                }
                fragmentTransaction.commit();
            }
        });
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
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick(R.id.ticket_image)
    public void onViewClicked() {
        AppManager.getAppManager().finishActivity(this);
    }
}
