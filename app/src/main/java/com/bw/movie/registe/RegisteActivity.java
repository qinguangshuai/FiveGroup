package com.bw.movie.registe;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bw.movie.R;
import com.bw.movie.ShowActivity;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.error.AppManager;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.login.pwd.EncryptUtil;
import com.bw.movie.registe.bean.RegisteUser;
import com.bw.movie.registe.presenter.RegistePresenter;
import com.bw.movie.registe.view.RegisteView;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        etRegsiterDate.setFocusable(false);
        etRegsiterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerView timePickerView = new TimePickerView.Builder(RegisteActivity.this, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        etRegsiterDate.setText(getTime(date));
                    }
                }).setType(new boolean[]{true,true,true,false,false,false})
                        .setCancelText("取消")
                        .setSubmitText("确定")
                        .isCenterLabel(false)
                        .build();
                timePickerView.show();
            }
        });
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        //"YYYY-MM-DD HH:MM:SS"        "yyyy-MM-dd"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    @Override
    public void initData() {
        findViewById(R.id.registetext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisteActivity.this, LoginActivity.class));
                AppManager.getAppManager().finishActivity(RegisteActivity.this);
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
        if ("男".equals(etRegisterSex)) {
            sex = 1;
        } else if ("女".equals(etRegisterSex)){
            sex = 2;
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
        if (registeUser.getMessage().contains("成功")){
            startActivity(new Intent(this,ShowActivity.class));
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
