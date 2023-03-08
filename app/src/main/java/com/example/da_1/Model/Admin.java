package com.example.da_1.Model;

public class Admin {
//    String dbAdmin = "CREATE TABLE ADMIN(" +
//            "id integer primary key," +
//            " hoten text," +
//            " username text," +
//            " matkhau text," +
//            " loai text)";

    private int id;
    private String hoten;
    private String username;
    private String matkhau;
    private String loai;

    public Admin(int id, String hoten, String username, String matkhau, String loai) {
        this.id = id;
        this.hoten = hoten;
        this.username = username;
        this.matkhau = matkhau;
        this.loai = loai;
    }

    public Admin(String hoten, String username, String matkhau, String loai) {
        this.hoten = hoten;
        this.username = username;
        this.matkhau = matkhau;
        this.loai = loai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
