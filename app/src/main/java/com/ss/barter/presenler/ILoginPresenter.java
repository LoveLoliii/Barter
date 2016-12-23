package com.ss.barter.presenler;

/**
 * Created by woshi on 2016/12/22.
 */
public interface ILoginPresenter {

    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);

}
