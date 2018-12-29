package com.bw.movie.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.ShowActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.presenter.LoginPresenter;
import com.bw.movie.login.pwd.EncryptUtil;
import com.bw.movie.login.view.LoginView;
import com.bw.movie.registe.RegisteActivity;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.SpUtil;
import com.bw.movie.util.WeiXinUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView<LoginUser> {

    @BindView(R.id.loginedit1)
    EditText loginedit1;
    @BindView(R.id.loginedit2)
    EditText loginedit2;
    @BindView(R.id.visible)
    ImageView visible;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.loginbox)
    CheckBox loginbox;
    @BindView(R.id.logintext)
    TextView logintext;
    @BindView(R.id.loginbtn)
    Button loginbtn;
    @BindView(R.id.loginimg)
    ImageView loginimg;
    private String mEdit1;
    private String mEdit2;
    private LoginPresenter presenter;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private long mBirthday;
    private String mHeadPic;
    private long mLastLoginTime;
    private int mId;
    private String mNickName;
    private String mPhone;
    private int mSex;
    private String mSessionId;
    private int mUserId;
    private Unbinder mUnbinder;
    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void initListener() {
        sp = getSharedPreferences("login", MODE_PRIVATE);
        edit = sp.edit();
        if (sp.getBoolean("loginbox", false)) {
            loginedit1.setText(sp.getString("phone", mEdit1));
            loginedit2.setText(sp.getString("pwd", mEdit2));
            loginbox.setChecked(true);
        }
    }

    @Override
    public void initData() {
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisteActivity.class));
            }
        });
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public LoginPresenter initPresenter() {
        presenter = new LoginPresenter(this);
        return presenter;
    }

    @OnClick({R.id.visible, R.id.loginbox, R.id.loginbtn, R.id.loginimg})
    public void onViewClicked(View view) {

        mEdit1 = loginedit1.getText().toString();
        mEdit2 = loginedit2.getText().toString();

        switch (view.getId()) {
            case R.id.visible:
                if (loginedit2.getInputType() == 129) {
                    loginedit2.setInputType(128);
                } else {
                    loginedit2.setInputType(129);
                }
                break;
            case R.id.loginbox:
                break;
            case R.id.loginbtn:
                String encrypt = EncryptUtil.encrypt(mEdit2);
                if (TextUtils.isEmpty(mEdit1) && TextUtils.isEmpty(mEdit2)) {
                    Toast.makeText(this, "用户名或则密码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }

                if (loginedit1.equals(mEdit1)){
                    isChinaPhoneLegal(mEdit1);
                    Toast.makeText(this, "手机号错误", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    presenter.getLogin(mEdit1, encrypt);
                }

                break;
            case R.id.loginimg:
                wxLogin();
                break;
        }
    }

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern compile = Pattern.compile(regExp);
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }

    public void wxLogin() {
        if (!WeiXinUtil.success(LoginActivity.this).isWXAppInstalled()) {
            Toast.makeText(this, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        WeiXinUtil.success(LoginActivity.this).sendReq(req);

    }

    @Override
    public void onDataSuccess(LoginUser loginUser) {
        String s = loginUser.getMessage();
        if (s.contains("成功")) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

            mSessionId = loginUser.getResult().getSessionId();
            mUserId = loginUser.getResult().getUserId();
            LoginUser.ResultBean.UserInfoBean userInfo = loginUser.getResult().getUserInfo();
            mBirthday = userInfo.getBirthday();
            mHeadPic = userInfo.getHeadPic();
            mLastLoginTime = userInfo.getLastLoginTime();
            mId = userInfo.getId();
            mNickName = userInfo.getNickName();
            mPhone = userInfo.getPhone();
            mSex = userInfo.getSex();
            LogUtil.d(mSessionId);
            LogUtil.d(mUserId + "");
            getSp();

            startActivity(new Intent(this, ShowActivity.class));
        }

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void getSp(){
        SpUtil.put("sessionId", mSessionId);
        SpUtil.put("userId", mUserId);
        SpUtil.put("birthday", mBirthday);
        SpUtil.put("headPic", mHeadPic);
        SpUtil.put("lastLoginTime", mLastLoginTime);
        SpUtil.put("nickName", mNickName);
        SpUtil.put("phone", mPhone);
        SpUtil.put("id", mId);
        SpUtil.put("sex", mSex);

        if (loginbox.isChecked()) {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("phone", mEdit1);
            edit.putString("pwd", mEdit2);
            //edit.putString("pwd", pwd);
            //edit.putString("sessionId", mSessionId);
            //edit.putString("userId", "" + mUserId);
            edit.putString("headPic", mHeadPic);
            edit.putString("nickName", mNickName);
            edit.putBoolean("loginbox", loginbox.isChecked());
            edit.commit();
        } else {
            edit.putString("phone", "");
            edit.putString("pwd", "");
            //edit.putString("sessionId", mSessionId);
            //edit.putString("userId", "" + mUserId);
            edit.putString("headPic", mHeadPic);
            edit.putString("nickName", mNickName);
            edit.putBoolean("loginbox", loginbox.isChecked());
            edit.commit();
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
}
