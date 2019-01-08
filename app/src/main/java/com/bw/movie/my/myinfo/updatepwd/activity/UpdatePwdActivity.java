package com.bw.movie.my.myinfo.updatepwd.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.login.LoginActivity;
import com.bw.movie.login.pwd.EncryptUtil;
import com.bw.movie.my.myinfo.updatepwd.bean.UpdatePwdEntity;
import com.bw.movie.my.myinfo.updatepwd.presenter.UpdatePwdPresenter;
import com.bw.movie.my.myinfo.updatepwd.view.UpdatePwdView;
import com.bw.movie.util.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*
   修改密码activity
* */
public class UpdatePwdActivity extends BaseActivity<UpdatePwdPresenter> implements UpdatePwdView<UpdatePwdEntity> {

    @BindView(R.id.update_oldpwd)
    EditText mUpdateOldpwd;
    @BindView(R.id.update_newPwd)
    EditText mUpdateNewPwd;
    @BindView(R.id.update_recoverpwd)
    EditText mUpdateRecoverpwd;
    @BindView(R.id.updatePwdback)
    Button mUpdatePwdback;
    @BindView(R.id.miMaBack)
    ImageView mMiMaBack;
    private UpdatePwdPresenter pwdPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        pwdPresenter = new UpdatePwdPresenter(this);
        String pwd = SpUtil.getString("pwd", "");
        mUpdateOldpwd.setText(pwd);

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
        return R.layout.activity_update_pwd;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public UpdatePwdPresenter initPresenter() {
        return null;
    }

    @Override
    public void onDataSuccess(UpdatePwdEntity updatePwdEntity) {
        String message = updatePwdEntity.getMessage();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.update_oldpwd, R.id.update_newPwd, R.id.update_recoverpwd, R.id.updatePwdback, R.id.miMaBack})
    public void onClick(View v) {
        String oldPwd = mUpdateOldpwd.getText().toString();
        String newPwd = mUpdateNewPwd.getText().toString();
        String copyPwd = mUpdateRecoverpwd.getText().toString();

        String oldPwd1 = EncryptUtil.encrypt(oldPwd);
        String newPwd1 = EncryptUtil.encrypt(newPwd);
        String copyPwd1 = EncryptUtil.encrypt(copyPwd);
        switch (v.getId()) {
            default:
                break;
            case R.id.update_oldpwd:
                break;
            case R.id.update_newPwd:
                break;
            case R.id.update_recoverpwd:
                break;
            case R.id.updatePwdback:
                if (TextUtils.isEmpty(newPwd) && TextUtils.isEmpty(copyPwd)){
                    Toast.makeText(this,"新密码为空",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    pwdPresenter.getPwd(oldPwd1, newPwd1, copyPwd1);
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                }
                break;
            case R.id.miMaBack:
                finish();
                break;
        }
    }
}
