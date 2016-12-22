package com.ss.barter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ss.model.User;
import com.ss.util.HttpUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mLoginBtn;
    private EditText mUserName;
    private EditText mPassword;
    private final  HttpUtils httpUntils = new HttpUtils();
    private   OkHttpClient okHttpClient ;
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("hehe","hehe");

        init();



    }


    private void init() {
        mUserName = (EditText) findViewById(R.id.user_name);
        mPassword = (EditText) findViewById(R.id.user_pwd);
        mLoginBtn = (Button) findViewById(R.id.login_btn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

    private void execute() throws IOException {
        RequestBody rb = new FormBody.Builder()
                .add("mName", String.valueOf(mUserName.getText()))
                .add("mPassword", String.valueOf(mPassword.getText()))
                .build();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url("http://172.16.18.133:8888/BarterServer/userwebservice/login?mName="+mUserName.getText()+"&mPassword="+mPassword.getText())

                .build();
        Response response = okHttpClient.newCall(request).execute();
        Log.i("login state", String.valueOf(response.code()));
        Log.i("login data", String.valueOf(response.body().charStream()));

        User u = gson.fromJson(response.body().charStream(),User.class);
        if( u !=null){
            Log.i("json data:",u.toString());
        }else{
            Log.i("json data:","null");
        }

    }
}
