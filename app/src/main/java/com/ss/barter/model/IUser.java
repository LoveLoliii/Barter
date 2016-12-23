package com.ss.barter.model;

/**
 * Created by woshi on 2016/12/22.
 */

public interface IUser {
     int getId();
     void setId(int id);
     String getName();
     void setName(String name);
     String getPassword();
     void setPassword(String password);
     String getPhone();
     void setPhone(String phone);
     String getAddress();
     void setAddress(String address);
     int checkUserValidity(String name, String passwd);
}
