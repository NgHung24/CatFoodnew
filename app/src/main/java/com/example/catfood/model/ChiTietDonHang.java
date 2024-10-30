package com.example.catfood.model;

public class ChiTietDonHang {

    private String mdon;
    private String asp;
    private String tsp;
    private double gsp;
    private int slsp;
    private double ttsp;
    private String lhsp;

    public ChiTietDonHang(String mdon, String asp, String tsp, double gsp, int slsp, double ttsp, String lhsp) {
        this.mdon = mdon;
        this.asp = asp;
        this.tsp = tsp;
        this.gsp = gsp;
        this.slsp = slsp;
        this.ttsp = ttsp;
        this.lhsp = lhsp;
    }

    public void setMdon(String mdon) {
        this.mdon = mdon;
    }

    public void setAsp(String asp) {
        this.asp = asp;
    }

    public void setTsp(String tsp) {
        this.tsp = tsp;
    }

    public void setGsp(double gsp) {
        this.gsp = gsp;
    }

    public void setSlsp(int slsp) {
        this.slsp = slsp;
    }

    public void setTtsp(double ttsp) {
        this.ttsp = ttsp;
    }

    public void setLhsp(String lhsp) {
        this.lhsp = lhsp;
    }

    public String getMdon() {
        return mdon;
    }

    public String getAsp() {
        return asp;
    }

    public String getTsp() {
        return tsp;
    }

    public double getGsp() {
        return gsp;
    }

    public int getSlsp() {
        return slsp;
    }

    public double getTtsp() {
        return ttsp;
    }

    public String getLhsp() {
        return lhsp;
    }
}
