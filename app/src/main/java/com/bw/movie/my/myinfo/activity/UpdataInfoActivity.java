package com.bw.movie.my.myinfo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.my.message.activity.MyMessage;
import com.bw.movie.my.myinfo.bean.UpDateUserInfoEntity;
import com.bw.movie.my.myinfo.prosenter.UpDateUserInfoPresenter;
import com.bw.movie.my.myinfo.updatepwd.activity.UpdatePwdActivity;
import com.bw.movie.my.myinfo.view.UpDateUserInfoView;
import com.bw.movie.my.updatehaed.bean.UpdateHeadEntity;
import com.bw.movie.my.updatehaed.presenter.UpdateHeadPresenter;
import com.bw.movie.my.updatehaed.view.UpdateHeadView;
import com.bw.movie.util.ImageUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  修改信息的activity
 * */
public class UpdataInfoActivity extends BaseActivity implements UpDateUserInfoView<UpDateUserInfoEntity> {

    @BindView(R.id.mtouxiang)
    SimpleDraweeView mMtouxiang;
    @BindView(R.id.mnicheng)
    TextView mMnicheng;
    @BindView(R.id.mxingbie)
    TextView mMxingbie;
    @BindView(R.id.mshoujihao)
    TextView mMshoujihao;
    @BindView(R.id.myouxiang)
    TextView mMyouxiang;
    Button mUpdateMyinfo;
    @BindView(R.id.mriqi)
    TextView mMriqi;
    @BindView(R.id.chongzhimima)
    ImageView mChongzhimima;
    private UpDateUserInfoPresenter presenter;
    private UpdateHeadPresenter headPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int sex1 = intent.getIntExtra("sex1", 0);
        if ("1".equals(sex1)){
            mMxingbie.setText("男");
        }else if ("2".equals(sex1)){
            mMxingbie.setText("女");
        }

        String email = intent.getStringExtra("email");
        mMyouxiang.setText(email);
        String headPic = intent.getStringExtra("headPic");
        Uri uri = Uri.parse(headPic);
        mMtouxiang.setImageURI(uri);
        String nickName = intent.getStringExtra("nickName");
        mMnicheng.setText(nickName);
        String phone1 = intent.getStringExtra("phone1");
        mMshoujihao.setText(phone1);
        String s = intent.getStringExtra("s");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mMriqi.setText(df.format(gc.getTime()));


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
        return R.layout.activity_updata_info;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public UpDateUserInfoPresenter initPresenter() {
        return null;
    }


    @Override
    public void onDataSuccess(UpDateUserInfoEntity upDateUserInfoEntity) {
    }


    //上传
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            //相机
            case 0:
                if (data.getParcelableExtra("data") == null) {
                    throw new NullPointerException("请选择图片");
                } else {
                    Bitmap data2 = data.getParcelableExtra("data");
                    Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getApplication().getContentResolver(), data2, null, null));
                    String data1 = ImageUtil.getPath(getApplicationContext(), uri);
                    File file = new File(data1);
                    xj(file);

        }
                break;
            //相册
            case 1:
                if (data.getData() == null) {
                    throw new NullPointerException("请选择图片");
                } else {
                    String path = ImageUtil.getPath(getApplicationContext(), data.getData());
                    File file1 = new File(path);
                    xj(file1);

                }
                break;
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
public void xj(File file){
    new UpdateHeadPresenter(new UpdateHeadView<UpdateHeadEntity>() {


        @Override
        public void onDataSuccess(UpdateHeadEntity updateHeadEntity) {
            if (!updateHeadEntity.getStatus().equals("0000")) {
                Toast.makeText(getApplicationContext(), "上传失败", Toast.LENGTH_LONG).show();
            } else {
                String headPath = updateHeadEntity.getHeadPath();
                Uri uri = Uri.parse(headPath);
                mMtouxiang.setImageURI(uri);
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
    }).getHead(file);
}
    @OnClick({R.id.mtouxiang, R.id.mnicheng, R.id.mxingbie, R.id.mriqi, R.id.mshoujihao, R.id.myouxiang, R.id.update_myinfo, R.id.chongzhimima})
    public void onClick(View v) {
        String nickname = mMnicheng.getText().toString().trim();

        String sex = mMxingbie.getText().toString();
        //Integer sex1 = Integer.valueOf(sex);
        String email = mMyouxiang.getText().toString();

        switch (v.getId()) {
            case R.id.mtouxiang:
                mMtouxiang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View view = View.inflate(getApplication(), R.layout.item_head, null);
                        final PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        popupWindow.setOutsideTouchable(true);
                        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                        popupWindow.showAsDropDown(v, 0, 0);
                        TextView paizhao = view.findViewById(R.id.paizhao);
                        paizhao.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //相机的操作
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.addCategory("android.intent.category.DEFAULT");
                                startActivityForResult(intent, 0);
                                popupWindow.dismiss();
                            }
                        });

                        TextView xiangce = view.findViewById(R.id.xiangce);
                        xiangce.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, 1);
                                popupWindow.dismiss();
                            }
                        });

                        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });
                    }
                });
                break;
            case R.id.mnicheng:
                break;
            case R.id.mxingbie:
                break;
            case R.id.mriqi:
                break;
            case R.id.mshoujihao:
                break;
            case R.id.myouxiang:
                break;

            case R.id.update_myinfo:

                Integer q = -1;
                if ("男".equals(sex)) {
                    q = 1;
                } else if ("女".equals(sex)){
                    q = 2;
                }

                mMxingbie.setText(q + "");
                presenter = new UpDateUserInfoPresenter(this);
                presenter.getUserInfo(nickname, q, email);
                startActivity(new Intent(this, MyMessage.class));

                finish();
                break;

            case R.id.chongzhimima:
                startActivity(new Intent(this, UpdatePwdActivity.class));
                finish();
                break;
        }
    }


}
