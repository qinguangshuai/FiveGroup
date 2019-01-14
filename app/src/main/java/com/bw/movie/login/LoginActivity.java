package com.bw.movie.login;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Constant;
import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.ShowActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.bean.LoginUser;
import com.bw.movie.login.bean.LoginUserInfoBean;
import com.bw.movie.login.bean.XinUser;
import com.bw.movie.login.presenter.LoginPresenter;
import com.bw.movie.login.presenter.XinPresenter;
import com.bw.movie.login.pwd.EncryptUtil;
import com.bw.movie.login.view.LoginView;
import com.bw.movie.login.view.XinView;
import com.bw.movie.registe.RegisteActivity;
import com.bw.movie.util.ButtonUtils;
import com.bw.movie.util.LogUtil;
import com.bw.movie.util.NotifyUtil;
import com.bw.movie.util.SpUtil;
import com.bw.movie.util.WeiXinUtil;
import com.bw.movie.wxapi.event.FinishEvent;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
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
    @BindView(R.id.remeberbox)
    CheckBox mRemeberbox;
    @BindView(R.id.login_fan)
    TextView loginFan;
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
    private NotifyUtil currentNotify;
    private int requestCode = (int) SystemClock.uptimeMillis();
    private String mMessage;

    private boolean isClick=true;

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe
    public void getFinishLogin(FinishEvent finishEvent) {
        if (finishEvent.getFinishlogin() == Constant.LOGINFNISH) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initListener() {
        sp = getSharedPreferences("login", MODE_PRIVATE);
        edit = sp.edit();
        if (sp.getBoolean("loginbox", false)) {
            loginedit1.setText(sp.getString("phone", mEdit1));
            loginedit2.setText(sp.getString("pwd", mEdit2));
            loginbox.setChecked(true);
            if (mRemeberbox.isChecked()){
                mRemeberbox.setChecked(true);
                loginbox.setChecked(true);
            }else {
                mRemeberbox.setChecked(false);
            }
        }
        if (sp.getBoolean("mRemeberbox",false)){
            startActivity(new Intent(this,ShowActivity.class));
            finish();
            return;
        }


    }


    @Override
    public void initData() {
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisteActivity.class));
                finish();
            }
        });
        setRemeberboxData();
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
//                if (!ButtonUtils.isFastDoubleClick(R.id.loginbtn,2000)) {
//                    //写你相关操作即可
//                    ButtonUtils.isFastDoubleClick(1, 2000);
//                }

                if(isClick){
                    isClick=false;

                    String encrypt = EncryptUtil.encrypt(mEdit2);
                    if (TextUtils.isEmpty(mEdit1) && TextUtils.isEmpty(mEdit2)) {
                        Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (loginedit1.equals(mEdit1)) {
                        isChinaPhoneLegal(mEdit1);
                        Toast.makeText(this, "手机号错误", Toast.LENGTH_LONG).show();
                        return;
                    } else {
                        presenter.getLogin(mEdit1, encrypt);
                    }

                    XGPushManager.registerPush(this, new XGIOperateCallback() {
                        @Override
                        public void onSuccess(Object data, int flag) {
                            //token在设备卸载重装的时候有可能会变
                            String da = String.valueOf(data);
                            new XinPresenter(new XinView<XinUser>() {

                                @Override
                                public void onDataSuccess(XinUser xinUser) {
                                    String message = xinUser.getMessage();
                                    Toast.makeText(LoginActivity.this, message + "", Toast.LENGTH_SHORT).show();
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
                            }).getLogin(da, 1);
                        }

                        @Override
                        public void onFail(Object data, int errCode, String msg) {
                            Log.d("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                        }
                    });

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
        if (!WeiXinUtil.success(LoginActivity.this)) {
            Toast.makeText(this, "您还未安装微信客户端", Toast.LENGTH_SHORT).show();
            return;
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "diandi_wx_login";
            WeiXinUtil.reg(LoginActivity.this).sendReq(req);
        }
    }

    @Override
    public void onDataSuccess(LoginUser loginUser) {
        String s = loginUser.getMessage();
        if (s.contains("成功")) {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            mMessage = loginUser.getMessage();
            mSessionId = loginUser.getResult().getSessionId();
            mUserId = loginUser.getResult().getUserId();
            LoginUserInfoBean userInfo = loginUser.getResult().getUserInfo();
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

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            PendingIntent pIntent = PendingIntent.getActivity(this,
                    requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            int smallIcon = R.mipmap.ic_launcher;
            int largeIcon = R.mipmap.ffanhui;
            String ticker = "您有一条新通知";
            String title = "登录";
            String content = "用户" + mNickName + "登陆成功";
            ArrayList<String> messageList = new ArrayList<String>();
            messageList.add("");
            String content1 = "[" + messageList.size() + "条]" + title + ": " + messageList.get(0);

            NotifyUtil notify1 = new NotifyUtil(this, 1);
            notify1.notify_mailbox(pIntent, smallIcon, largeIcon, messageList, ticker,
                    title, content, true, true, false);
            currentNotify = notify1;
        }

        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void getSp() {
        if (loginbox.isChecked()) {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("phone", mEdit1);
            edit.putString("pwd", mEdit2);
            edit.putString("nickName", mNickName);
            edit.putBoolean("loginbox", loginbox.isChecked());
            edit.putBoolean("mRemeberbox",mRemeberbox.isChecked());
            edit.commit();
        } else {
            edit.putString("phone", "");
            edit.putString("pwd", "");
            edit.putString("headPic", mHeadPic);
            edit.putString("nickName", mNickName);
            edit.putBoolean("loginbox", loginbox.isChecked());
            edit.putBoolean("mRemeberbox",mRemeberbox.isChecked());
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

    //给按钮监听
    public void setRemeberboxData() {
        mRemeberbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginbox.setChecked(mRemeberbox.isChecked());
            }
        });
    }

    @OnClick(R.id.login_fan)
    public void onViewClicked() {
        AppManager.getAppManager().finishAllActivity();
    }
}
