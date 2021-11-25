package com.edu.neu.homework1;

import java.io.Serializable;

/**
 * 用户的实体类，有用户名，密码，邮箱，电话，地址，生日
 */
public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String birthday;

    public User(String username, String password, String email, String phone, String address, String birthday) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Serializable
     */
    private static final long serialVersionUID = 1L;
}
