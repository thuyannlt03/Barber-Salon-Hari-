package com.example.da_1.Model;

public class LoaiDV {
    private int id;
    private String tenloai;

    public LoaiDV(int id, String tenloai) {
        this.id = id;
        this.tenloai = tenloai;
    }

    public LoaiDV(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }
}
