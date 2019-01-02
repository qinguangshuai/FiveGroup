package com.bw.movie.registe;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.login.pwd.EncryptUtil;
import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.registe.presenter.RegistePresenter;
import com.bw.movie.registe.view.RegisteView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date:2018/1/2    15:33
 * author:秦广帅(Lenovo)
 * fileName:AttCinemaAdapter
 * 注册界面
 */

public class RegisteActivity extends BaseActivity<RegistePresenter> implements RegisteView<RegisteUser> {

    @BindView(R.id.et_register_name)
    EditText etRegisterName;
    @BindView(R.id.et_register_sex)
    EditText etRegisterSex;
    @BindView(R.id.et_regsiter_date)
    EditText etRegsiterDate;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_email)
    EditText etRegisterEmail;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.button)
    Button button;
    private RegistePresenter mRegistePresenter;
    private Unbinder mUnbinder;

    @Override
    public void initView() {
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        findViewById(R.id.registetext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisteActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_registe;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public RegistePresenter initPresenter() {
        mRegistePresenter = new RegistePresenter(this);

        return mRegistePresenter;
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        Integer sex = -1;
        String etRegisterName = this.etRegisterName.getText().toString();
        String etRegisterSex = this.etRegisterSex.getText().toString();
        if ("".equals(etRegisterSex)) {
            sex = -1;
        } else {
            sex = Integer.valueOf(etRegisterSex);
        }
        String etRegsiterDate = this.etRegsiterDate.getText().toString();
        String etRegisterPhone = this.etRegisterPhone.getText().toString();
        String etRegisterEmail = this.etRegisterEmail.getText().toString();
        String etRegisterPassword = this.etRegisterPassword.getText().toString();
        String pwd = EncryptUtil.encrypt(etRegisterPassword);
        mRegistePresenter.postRegiste(etRegisterName, etRegisterPhone, pwd, pwd, sex, etRegsiterDate, "867180033786056", "华为", "5.0", "android", etRegisterEmail);
        if (TextUtils.isEmpty(etRegisterName) && TextUtils.isEmpty(etRegsiterDate) && TextUtils.isEmpty(etRegisterPhone) && TextUtils.isEmpty(etRegisterEmail) && TextUtils.isEmpty(etRegisterPassword)) {
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (!etRegisterPhone.equals(etRegisterPhone)) {
            isChinaPhoneLegal(etRegisterPhone);
            Toast.makeText(this, "手机号错误", Toast.LENGTH_LONG).show();
            return;
        }
        else if (!etRegisterEmail.equals(etRegisterEmail)){
            regexEmailAddress(etRegisterEmail);
            Toast.makeText(this, "邮箱有误", Toast.LENGTH_LONG).show();
        } else {
            mRegistePresenter.postRegiste(etRegisterName, etRegisterPhone, pwd, pwd, sex, etRegsiterDate, "867180033786056", "华为", "5.0", "android", etRegisterEmail);
        }
        //email:2103186530@qq.com  os:android  screenSize:5.0  ua:华为  imei:867180033786056

    }

    //手机号验证
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern compile = Pattern.compile(regExp);
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }

    public static boolean regexEmailAddress(String email) {
        // 邮箱匹配结果
        boolean isEmail_matcher = email.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        // 如果isEmail_matcher value is true , 则 return true , else return false
        if (isEmail_matcher) return true;
        return false;
    }

    @Override
    public void onDataSuccess(RegisteUser registeUser) {
        Toast.makeText(this, registeUser.getMessage(), Toast.LENGTH_SHORT).show();
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
