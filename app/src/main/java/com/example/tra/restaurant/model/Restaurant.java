package com.example.tra.restaurant.model;

public class Restaurant {
    private int maNhaHang;
    private String tenNhaHang;
    private String diaChi;
    private String diemDanhGia;

    public Restaurant() {
    }

    public Restaurant(int maNhaHang, String tenNhaHang, String diaChi, String diemDanhGia) {
        this.maNhaHang = maNhaHang;
        this.tenNhaHang = tenNhaHang;
        this.diaChi = diaChi;
        this.diemDanhGia = diemDanhGia;
    }

    public Restaurant(String tenNhaHang, String diaChi, String diemDanhGia) {
        this.tenNhaHang = tenNhaHang;
        this.diaChi = diaChi;
        this.diemDanhGia = diemDanhGia;
    }

    public int getMaNhaHang() {
        return maNhaHang;
    }

    public void setMaNhaHang(int maNhaHang) {
        this.maNhaHang = maNhaHang;
    }

    public String getTenNhaHang() {
        return tenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        this.tenNhaHang = tenNhaHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(String diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }
}
