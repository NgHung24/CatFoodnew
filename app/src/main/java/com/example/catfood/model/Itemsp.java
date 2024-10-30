package com.example.catfood.model;

public class Itemsp {
    private String image;
    private String tensp;
    private double giasp;
    private int sl;
    private String loaihang;

    public Itemsp(String image, String tensp, double giasp, int sl , String loaihang) {
        this.image = image;
        this.tensp = tensp;
        this.giasp = giasp;
        this.sl = sl;
        this.loaihang = loaihang;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setGiasp(double giasp) {
        this.giasp = giasp;
    }


    public void setSl(int sl) {
        this.sl = sl;
    }

    public void setLoaihang(String loaihang) {
        this.loaihang = loaihang;
    }

    public String getImage() {
        return image;
    }

    public String getTensp() {
        return tensp;
    }

    public double getGiasp() {
        return giasp;
    }

    public int getSl() {
        return sl;
    }

    public String getLoaihang() {
        return loaihang;
    }
}
