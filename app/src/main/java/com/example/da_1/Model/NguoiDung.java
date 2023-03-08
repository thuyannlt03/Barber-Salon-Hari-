package com.example.da_1.Model;

public class NguoiDung {
//    String dbNguoiDung = "CREATE TABLE NGUOIDUNG(" +
//            "id integer primary key autoincrement," +
//            " hoten text," +
//            " sdt text," +
//            "matkhau text)";
//        db.execSQL(dbNguoiDung);
    private int id;
    private String hoten;
    private String ngaysinh;
    private String sdt;
    private String matkhau;

    public String getNgaysinh() {
        return ngaysinh;
    }

    public String setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
        return ngaysinh;
    }

    public NguoiDung(int id, String hoten, String ngaysinh, String sdt, String matkhau) {
        this.id = id;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.matkhau = matkhau;
    }

    public NguoiDung(String hoten, String ngaysinh, String sdt, String matkhau) {
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.matkhau = matkhau;
    }
    public NguoiDung(int id, String hoten, String ngaysinh, String sdt) {
        this.id = id;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
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

    public String setHoten(String hoten) {
        this.hoten = hoten;
        return hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public String setSdt(String sdt) {
        this.sdt = sdt;
        return sdt;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String setMatkhau(String matkhau) {
        this.matkhau = matkhau;
        return matkhau;
    }
}
