package com.example.catfood.model;

public class SanPham {
    private int maSP;      // Mã sản phẩm
    private String tenSP;  // Tên sản phẩm
    private double gia;    // Giá sản phẩm
    private int soLuong;   // Số lượng sản phẩm
    private String theLoai; // Thể loại sản phẩm
    private String linkAnh; // Liên kết ảnh sản phẩm
    private String moTa;

    // Constructor
    public SanPham(int maSP, String tenSP, String theLoai, double gia, int soLuong,  String moTa, String linkAnh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.theLoai = theLoai;
        this.gia = gia;
        this.soLuong = soLuong;
        this.moTa = moTa;
        this.linkAnh = linkAnh;
    }

    // Các phương thức getter
    public int getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public double getGia() {
        return gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public String getMota() {
        return moTa;
    }

    public String getLinkAnh() {
        return linkAnh;
    }
}
