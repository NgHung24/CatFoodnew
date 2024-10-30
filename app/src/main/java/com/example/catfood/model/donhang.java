package com.example.catfood.model;

import java.io.Serializable;

public class donhang implements Serializable {

    private String mdon ;
    private String tkh ;
    private String sodtkh ;
    private String eemailkh ;
    private String dchikh ;
    private String pthucttoan ;
    private double ttien ;
    private String tthai ;

    public donhang(String mdon, String tkh, String sodtkh, String eemailkh, String dchikh, String pthucttoan, double ttien, String tthai) {
        this.mdon = mdon;
        this.tkh = tkh;
        this.sodtkh = sodtkh;
        this.eemailkh = eemailkh;
        this.dchikh = dchikh;
        this.pthucttoan = pthucttoan;
        this.ttien = ttien;
        this.tthai = tthai;
    }

    public void setMdon(String mdon) {
        this.mdon = mdon;
    }

    public void setTkh(String tkh) {
        this.tkh = tkh;
    }

    public void setSodtkh(String sodtkh) {
        this.sodtkh = sodtkh;
    }

    public void setDchikh(String dchikh) {
        this.dchikh = dchikh;
    }

    public void setEemailkh(String eemailkh) {
        this.eemailkh = eemailkh;
    }

    public void setPthucttoan(String pthucttoan) {
        this.pthucttoan = pthucttoan;
    }

    public void setTtien(double ttien) {
        this.ttien = ttien;
    }

    public void setTthai(String tthai) {
        this.tthai = tthai;
    }

    public String getMdon() {
        return mdon;
    }

    public String getTkh() {
        return tkh;
    }

    public String getEemailkh() {
        return eemailkh;
    }

    public String getSodtkh() {
        return sodtkh;
    }

    public String getDchikh() {
        return dchikh;
    }

    public Double getTtien() {
        return ttien;
    }

    public String getPthucttoan() {
        return pthucttoan;
    }

    public String getTthai() {
        return tthai;
    }
}
