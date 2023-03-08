package com.example.da_1.Model;

public class DatLich {
//    String dbDatLich = "CREATE TABLE DATLICH(" +
//            "id integer primary key autoincrement," +
//            " userid integer references NGUOIDUNG(id)," +
//            " ngay text," +
//            " gio text," +
//            "trangthai integer)";

    private int id;
    private int userid;
    private  String ngay;
    private  String gio;
    private int trangthai;
    private String tenKH;
    private String tenDV;

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public DatLich(String tenKH, String tenDV, String ngay, String gio) {
        this.tenKH = tenKH;
        this.tenDV = tenDV;
        this.ngay = ngay;
        this.gio = gio;
    }

    public DatLich(int id, int userid, String ngay, String gio, int trangthai) {
        this.id = id;
        this.userid = userid;
        this.ngay = ngay;
        this.gio = gio;
        this.trangthai = trangthai;
    }

    public DatLich(int id, String tenKH, String tenDV, String ngay, String gio) {
        this.id = id;
        this.ngay = ngay;
        this.gio = gio;
        this.tenKH = tenKH;
        this.tenDV = tenDV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public DatLich(int userid, String ngay, String gio, int trangthai) {
        this.userid = userid;
        this.ngay = ngay;
        this.gio = gio;
        this.trangthai = trangthai;
    }
}
