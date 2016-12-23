package com.ss.barter.presenler;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.ss.barter.model.UserModel;
import com.ss.barter.util.MyException;
import com.ss.barter.view.ILoginView;
import com.ss.barter.view.LoginActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by woshi on 2016/12/22.
 */

public class LoginPresenterCompl implements ILoginPresenter {
    private   OkHttpClient okHttpClient ;
    private final Gson gson = new Gson();
    ILoginView iLoginView = new LoginActivity();
    UserModel user = new UserModel();
    Handler handler =new Handler();
    public LoginPresenterCompl(ILoginView iLoginView) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void doLogin(final String name, final String passwd) {


               new Thread(new Runnable() {
                   @Override
                   public void run() {

                       Boolean isLoginSuccess = true;
                       int code = user.checkUserValidity(name,passwd);
                       if (code!=0) isLoginSuccess = false;
                       Boolean result = isLoginSuccess;


                       RequestBody rb = new FormBody.Builder()
                               .add("mName",name)
                               .add("mPassword", passwd)
                               .build();
                       okHttpClient = new OkHttpClient.Builder()
                               .connectTimeout(10, TimeUnit.SECONDS)
                               .writeTimeout(10, TimeUnit.SECONDS)
                               .readTimeout(30, TimeUnit.SECONDS)
                               .build();
                       Request request = new Request.Builder()
                               .url("http://192.168.2.2:8888/BarterServer/userwebservice/login?mName="+name+"&mPassword="+passwd)
                               .build();

                       try {
                           Response  response = okHttpClient.newCall(request).execute();
                           code = response.code();
                           Log.i("login state", String.valueOf(response.code()));
                           Log.i("login data", String.valueOf(response.body().charStream()));
                           // TODO: 2016/12/22 对返回的状态码进行处理 放在APP crash
                           if( response.code()  ==200) {
                               UserModel u = gson.fromJson(response.body().charStream(), UserModel.class);
                               if (u != null) {
                                   Log.i("json data:", u.toString());
                                   // TODO: 2016/12/22 登陆成功处理： 用户信息存入 LoginActivity
                                   result =true;

                               } else {
                                   Log.i("json data:", "null");
                                   result=false;

                               }
                           }else {
                               try {

                                   throw new MyException("返回状态码为："+response.code()+",出现异常");
                               } catch (MyException e) {
                                   iLoginView.onLoginResult(result,code);
                                   e.getMessage();
                                   e.printStackTrace();
                               }
                           }



                       }  catch (IOException e) {
                           e.printStackTrace();
                       }

                       iLoginView.onLoginResult(result,code);
                   }
               }).start();

    }

    @Override
    public void setProgressBarVisiblity(int visiblity) {

    }
}
