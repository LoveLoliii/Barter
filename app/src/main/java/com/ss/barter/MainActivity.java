package com.ss.barter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ss.util.HttpUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mpusu;
    private final  HttpUtils httpUntils = new HttpUtils();
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
        httpUntils.getJson("http://publicobject.com/helloworld.txt", new HttpUtils.HttpCallBack() {
            @Override
            public void onSusscess(String data) {
                 Log.i("data",data);
            }
        });

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
        mpusu = (Button) this.findViewById(R.id.push_toast);
        mpusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"test",Toast.LENGTH_LONG).show();
            }
        });
    }
}
