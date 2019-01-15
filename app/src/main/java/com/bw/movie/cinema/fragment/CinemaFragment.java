package com.bw.movie.cinema.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.bw.movie.Constant;
import com.bw.movie.R;
import com.bw.movie.base.BaseEvent;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.activity.ParticularsActivity;
import com.bw.movie.cinema.bean.AddressUser;
import com.bw.movie.cinema.search.bean.SearchBean;
import com.bw.movie.cinema.search.presenter.SearchPresenter;
import com.bw.movie.custom.CustomViewpager;
import com.bw.movie.custom.SearchView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 影院页面
 */
public class CinemaFragment extends BaseFragment {
    //推荐影院
    @BindView(R.id.recommendcinema)
    RadioButton recommendcinema;
    //附近影院
    @BindView(R.id.neighbouringcinema)
    RadioButton neighbouringcinema;
    @BindView(R.id.rg_cinema)
    RadioGroup rgCinema;
    @BindView(R.id.vp_cinema)
    CustomViewpager vpCinema;
    Unbinder unbinder;
    @BindView(R.id.zuoBiaoImage)
    ImageView zuoBiaoImage;
    @BindView(R.id.zuoBiaoText)
    TextView zuoBiaoText;
    private SearchView mSearchView;

    //初始化控件
    @Override
    public void initView() {
        unbinder = ButterKnife.bind(this, rootView);
        rgCinema.check(R.id.recommendcinema);
        if (!EventBus.getDefault().isRegistered(this)) {
            BaseEvent.register(this);
        }
        mSearchView = rootView.findViewById(R.id.serch);
        mSearchView.setClick(new SearchView.Click() {
            @Override
            public void onClickListener(View v, String s) {


                if (TextUtils.isEmpty(s)){
                    Toast.makeText(mActivity, "请输入查询信息", Toast.LENGTH_SHORT).show();
                }else{
                    new SearchPresenter(new com.bw.movie.cinema.search.view.SearchView<SearchBean>() {

                        @Override
                        public void onDataSuccess(SearchBean searchBean) {

                            if (searchBean.getResult()!=null && searchBean.getResult().size()>0){
                                //跳转到ParticularsActivity页面
                                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                                //获取推荐的logo的
                                String logo = searchBean.getResult().get(0).getLogo();
                                //获取推荐姓名
                                String name = searchBean.getResult().get(0).getName();
                                //获取推荐的地址
                                String address = searchBean.getResult().get(0).getAddress();
                                int id = searchBean.getResult().get(0).getId();
                                intent.putExtra(Constant.TUIJIANID, id + "");
                                intent.putExtra(Constant.LOGO, logo);
                                intent.putExtra(Constant.NAME, name);
                                intent.putExtra(Constant.ADDRESS, address);
                                startActivity(intent);
                            }else {
                                Toast.makeText(mActivity, "请输入正确电影院信息", Toast.LENGTH_SHORT).show();
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
                    }).getSreach(1,5,s);
                }

            }
        });

    }

    //初始化监听
    @Override
    public void initListener() {
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new NeighbouringFragment());
        list.add(new RecommendFragment());
        list.add(new RecommendFragment());
        vpCinema.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        rgCinema.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.recommendcinema:
                        vpCinema.setCurrentItem(0);
                        break;
                    case R.id.neighbouringcinema:
                        vpCinema.setCurrentItem(1);
                        break;
                }
            }
        });
    }

    //逻辑处理
    @Override
    public void initData() {

    }

    //初始化布局
    @Override
    public int initLayoutId() {
        return R.layout.fragment_cinema;
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



    @Subscribe
    public void setAddress(AddressUser address){
        zuoBiaoText.setText(address.getCity()+"  "+address.getCid());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        BaseEvent.unregister(this);
    }
}
