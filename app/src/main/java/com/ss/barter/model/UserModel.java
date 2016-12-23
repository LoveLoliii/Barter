package com.ss.barter.model;

/**
 * Created by Administrator on 2016/12/22.
 */

public class UserModel implements IUser {
    private int id;
    private String name;
    private String password;
    private String phone;
    private String address;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "UserModel [id=" + id + ", name=" + name + ", password=" + password
                + ", phone=" + phone + ", address=" + address + "]";
    }


    public int checkUserValidity(String name, String password) {
        if (name==null||password==null||!name.equals(getName())||!password.equals(getPassword())){
            return -1;
        }
        return 0;
    }
}
