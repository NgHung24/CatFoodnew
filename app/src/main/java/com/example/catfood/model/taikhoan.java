package com.example.catfood.model;

import android.widget.ImageView;

public class taikhoan {
    private String name;
    private int num;
    private String mail;
    private int check;
    private String picture;
    private String pass;

    public taikhoan(String name, int num, String mail, int check, String picture, String pass) {
        this.name = name;
        this.num = num;
        this.mail = mail;
        this.check = check;
        this.picture = picture;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}