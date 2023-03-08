package com.example.da_1.Model;

public class DichVu {

    private int id;
    private String tenDV;
    private int maloai;
    private int giaDV;
    private String tenloai;
    private String anhDV;
    private int trangthai;
    private int check;
    private int sum;




    public DichVu(int id, String tenDV, int maloai, int giaDV, String anhDV, int trangthai, int check) {
        this.id = id;
        this.tenDV = tenDV;
        this.maloai = maloai;
        this.giaDV = giaDV;
        this.anhDV = anhDV;
        this.trangthai = trangthai;
        this.check = check;
    }


    public DichVu(int id, String tenDV, int maloai, int giaDV) {
        this.id = id;
        this.tenDV = tenDV;
        this.maloai = maloai;
        this.giaDV = giaDV;
    }

    public DichVu(String tenDV, int maloai, int giaDV) {
        this.tenDV = tenDV;
        this.maloai = maloai;
        this.giaDV = giaDV;
    }


    public DichVu(int id, String tenDV, int giaDV, String anhDV, int check) {
        this.id = id;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.anhDV = anhDV;
        this.check = check;
    }

    public DichVu(int id, String tenDV, String tenloai, int giaDV,  String anhDV) {
        this.id = id;
        this.tenDV = tenDV;
        this.tenloai = tenloai;
        this.giaDV = giaDV;
        this.anhDV = anhDV;
    }



    public DichVu(int id, String tenDV, int giaDV, String anhDV) {
        this.id = id;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.anhDV = anhDV;
    }

    public DichVu(String tenDV, int giaDV, String anhDV) {
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.anhDV = anhDV;
    }

    public DichVu(int id, String tenDV, int giaDV) {
        this.id = id;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
    }

    public DichVu( String tenDV, int sum) {
        this.tenDV = tenDV;
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public int getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(int giaDV) {
        this.giaDV = giaDV;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getAnhDV() {
        return anhDV;
    }

    public void setAnhDV(String anhDV) {
        this.anhDV = anhDV;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
