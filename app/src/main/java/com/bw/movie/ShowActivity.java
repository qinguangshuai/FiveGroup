package com.bw.movie;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.cinema.fragment.CinemaFragment;
import com.bw.movie.custom.CustomViewpager;
import com.bw.movie.film.event.NetEvent;
import com.bw.movie.film.fragment.FilmFragment;
import com.bw.movie.my.MyFragment;
import com.bw.movie.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * fragment联动
 */
public class ShowActivity extends BaseActivity {

    @BindView(R.id.showMap)
    MapView showMap;
    AMap aMap;
    private RadioGroup rg_show;
    private CustomViewpager vp_show;
    private RadioButton film_show;
    private RadioButton cinema_show;
    private RadioButton my_show;
    private ImageView image_populer;
    private ImageView lingdai;

    //权限
    private String[] permissions = {Manifest.permission.CAMERA,                     //相机
            Manifest.permission.ACCESS_COARSE_LOCATION,     //GPS定位
            Manifest.permission.READ_EXTERNAL_STORAGE,     //读取
            Manifest.permission.WRITE_EXTERNAL_STORAGE,     //写入
    };


    @Override
    public void initData() {

        film_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ObjectAnimator ra = ObjectAnimator.ofFloat(film_show, "rotation", 0f, 360f);
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(film_show, "scaleX", 1, 1.2f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(film_show, "scaleY", 1, 1.2f);
                    AnimatorSet aa1 = new AnimatorSet();
                    aa1.playTogether(ra, scaleX, scaleY);
                    aa1.setDuration(300);
                    aa1.start();
                } else {

                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(film_show, "scaleX", 1.2f, 1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(film_show, "scaleY", 1.2f, 1f);
                    AnimatorSet aa1 = new AnimatorSet();
                    aa1.playTogether(scaleX, scaleY);
                    aa1.setDuration(300);
                    aa1.start();
                }
            }
        });
        cinema_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(image_populer, "alpha", 1f, 1f);
                    ObjectAnimator ra1 = ObjectAnimator.ofFloat(image_populer, "translationY", 0, 0);
                    ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(cinema_show, "scaleX", 1, 1.2f);
                    ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(cinema_show, "scaleY", 1, 1.2f);
                    AnimatorSet aa = new AnimatorSet();
                    aa.playTogether(anim, ra1, scaleX1, scaleY1);
                    aa.setDuration(300);
                    aa.start();
                } else {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(image_populer, "alpha", 0f, 2f);
                    ObjectAnimator ra1 = ObjectAnimator.ofFloat(image_populer, "translationY", 0, 200);
                    ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(cinema_show, "scaleX", 1.2f, 1f);
                    ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(cinema_show, "scaleY", 1.2f, 1f);
                    AnimatorSet aa = new AnimatorSet();
                    aa.playTogether(anim, ra1, scaleX1, scaleY1);
                    aa.setDuration(300);
                    aa.start();
                }
            }
        });
        my_show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                AlphaAnimation
                if (isChecked) {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(lingdai, "alpha", 0f, 0.8f);
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(my_show, "scaleX", 1, 1.2f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(my_show, "scaleY", 1, 1.2f);
                    AnimatorSet aa = new AnimatorSet();
                    aa.playTogether(anim, scaleX, scaleY);
                    aa.setDuration(300);
                    aa.start();
                } else {
                    ObjectAnimator anim = ObjectAnimator.ofFloat(lingdai, "alpha", 0.8f, 0f);
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(my_show, "scaleX", 1.2f, 1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(my_show, "scaleY", 1.2f, 1f);
                    AnimatorSet aa = new AnimatorSet();
                    aa.playTogether(anim, scaleX, scaleY);
                    aa.setDuration(300);
                    aa.start();
                }
            }
        });
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_show;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void initListener() {

        final List<Fragment> list = new ArrayList<>();
        list.add(new FilmFragment());
        list.add(new CinemaFragment());
        list.add(new MyFragment());
        //fragment联动
        vp_show.setOffscreenPageLimit(2);
        vp_show.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

//         Radiogroup监听
        rg_show.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.film_show:
                        vp_show.setCurrentItem(0);

                        break;
                    case R.id.cinema_show:
                        vp_show.setCurrentItem(1);

                        break;
                    case R.id.my_show:
                        vp_show.setCurrentItem(2);

                        break;
                }
            }
        });
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        vp_show = findViewById(R.id.vp_show);
        rg_show = (RadioGroup) findViewById(R.id.rg_show);
        film_show = (RadioButton) findViewById(R.id.film_show);
        cinema_show = (RadioButton) findViewById(R.id.cinema_show);
        my_show = (RadioButton) findViewById(R.id.my_show);

        image_populer = (ImageView) findViewById(R.id.image_populer);

        lingdai = (ImageView) findViewById(R.id.lingdai);


        doPermission(); //动态权限
        ButterKnife.bind(this);
        rg_show.check(R.id.film_show);
        ObjectAnimator anim = ObjectAnimator.ofFloat(lingdai, "alpha", 1f, 0f);
        anim.setDuration(0);// 动画持续时间
        anim.start();
        ObjectAnimator ra1 = ObjectAnimator.ofFloat(image_populer, "translationY", 0, 200);
        AnimatorSet aa = new AnimatorSet();
        aa.playTogether(ra1);
        aa.setDuration(300);
        aa.start();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }, 1000);
    }



    @Subscribe
    public void getNet(NetEvent netEvent){
        if (Constant.GETNET==netEvent.getId()) {
            showContent();
        }else {
            showloading();
        }

    }



    //权限申请
    public void doPermission() {
        //版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查相机
            int i = ContextCompat.checkSelfPermission(ShowActivity.this, permissions[0]);
            //权限是否已经 授权 GRANTED—授权 DINIED—拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                //如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, permissions, 100);

            }
            //检查GPS
            int i2 = ContextCompat.checkSelfPermission(ShowActivity.this, permissions[1]);
            //权限是否已经 授权 GRANTED—授权 DINIED—拒绝
            if (i2 != PackageManager.PERMISSION_GRANTED) {
                //如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, permissions, 101);

            }

            //检查读取
            int i3 = ContextCompat.checkSelfPermission(ShowActivity.this, permissions[2]);
            //权限是否已经 授权 GRANTED—授权 DINIED—拒绝
            if (i3 != PackageManager.PERMISSION_GRANTED) {
                //如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, permissions, 102);

            }


            //检查写入
            int i4 = ContextCompat.checkSelfPermission(ShowActivity.this, permissions[3]);
            //权限是否已经 授权 GRANTED—授权 DINIED—拒绝
            if (i4 != PackageManager.PERMISSION_GRANTED) {
                //如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, permissions, 103);

            }

        }
    }

    //重写该 方法 响应 申请
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            ToastUtil.Toast("再按一次退出程序");
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        } else {
            this.finish();
            System.exit(0);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
