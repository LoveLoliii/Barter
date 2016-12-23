package com.ss.barter.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ss.barter.R;
import com.ss.barter.presenler.ILoginPresenter;
import com.ss.barter.presenler.LoginPresenterCompl;


public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private Button mLoginBtn;
    private EditText mUserName;
    private EditText mPassword;


    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: 2016/12/22 是否曾登陆以及检查用户的设置（是否记住登陆）
        // 曾经正确登陆 且 用户记住登陆 则直接跳转MainActivity。

        setContentView(R.layout.activity_login);



       //findView
        mUserName = (EditText) findViewById(R.id.user_name);
        mPassword = (EditText) findViewById(R.id.user_pwd);
        mLoginBtn = (Button) findViewById(R.id.login_btn);

        //setListener
        mLoginBtn.setOnClickListener(this);

        //init
        loginPresenter = new LoginPresenterCompl(this);





    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                loginPresenter.doLogin(mUserName.getText().toString(),mPassword.getText().toString());
                break;
        }
    }

    @Override
    public void onClearText() {

    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        if (result){
            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onSetProgressBarVisibility(int visibility) {

    }
}
