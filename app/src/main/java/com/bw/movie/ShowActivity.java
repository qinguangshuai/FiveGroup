package com.bw.movie;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.bw.movie.cinema.fragment.CinemaFragment;
import com.bw.movie.custom.CustomViewpager;
import com.bw.movie.film.fragment.FilmFragment;
import com.bw.movie.my.MyFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * fragment联动
 */
public class ShowActivity extends AppCompatActivity {


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        doPermission(); //动态权限
        ButterKnife.bind(this);
        //初始化控件
        initView();
        //初始化监听
        initListener();
        initData();
        rg_show.check(R.id.film_show);
        ObjectAnimator anim = ObjectAnimator.ofFloat(lingdai, "alpha", 1f, 0f);
        anim.setDuration(0);// 动画持续时间
        anim.start();

        ObjectAnimator ra1 = ObjectAnimator.ofFloat(image_populer, "translationY", 0, 200);

        AnimatorSet aa = new AnimatorSet();
        aa.playTogether(ra1);
        aa.setDuration(300);
        aa.start();
    }

    private void initData() {
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

    private void initListener() {

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
                // vp_show.setCurrentItem(checkedId - 1);
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

    private void initView() {
        vp_show = findViewById(R.id.vp_show);
        rg_show = (RadioGroup) findViewById(R.id.rg_show);
        film_show = (RadioButton) findViewById(R.id.film_show);
        cinema_show = (RadioButton) findViewById(R.id.cinema_show);
        my_show = (RadioButton) findViewById(R.id.my_show);

        image_populer = (ImageView) findViewById(R.id.image_populer);

        lingdai = (ImageView) findViewById(R.id.lingdai);

    }

    //权限申请
    public void doPermission(){
        //版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查相机
            int i = ContextCompat.checkSelfPermission(ShowActivity.this, permissions[0]);
            //权限是否已经 授权 GRANTED—授权 DINIED—拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                //如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this,permissions,100);

            }
            //检查GPS
            int i2 = ContextCompat.checkSelfPermission(ShowActivity.this, permissions[1]);
            //权限是否已经 授权 GRANTED—授权 DINIED—拒绝
            if (i2 != PackageManager.PERMISSION_GRANTED) {
                //如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this,permissions,101);

            }

            //检查读取
            int i3 = ContextCompat.checkSelfPermission(ShowActivity.this, permissions[2]);
            //权限是否已经 授权 GRANTED—授权 DINIED—拒绝
            if (i3 != PackageManager.PERMISSION_GRANTED) {
                //如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this,permissions,102);

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


}
