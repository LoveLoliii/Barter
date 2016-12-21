package com.ss.barter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ss.util.HttpUtils;

import java.io.IOException;

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
    private final  OkHttpClient okHttpClient = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("hehe","hehe");

        init();

   /*     new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
*/
/*

        httpUntils.OnStart(new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                httpUntils.getJson("http://publicobject.com/helloworld.txt", new HttpUtils.HttpCallBack() {
                    @Override
                    public void onSusscess(String data) {
                        Log.i("data",data);
                    }
                });
            }
        });
*/


    }

   /* private void execute() throws IOException {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            Log.i("webResponse", String.valueOf(response.code()));
            Log.i("response", String.valueOf(response.body()));
        }
    }*/

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

        Request request = new Request.Builder()
                .url("http://172.16.18.120:8888/AndroidLogin/userwebservice/login?mName="+mUserName.getText()+"&mPassword="+mPassword.getText())

                .build();
        Response response = okHttpClient.newCall(request).execute();
        Log.i("login state", String.valueOf(response.code()));
        Log.i("login data", String.valueOf(response.body()));
    }
}
