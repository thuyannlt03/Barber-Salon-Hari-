package com.example.da_1.Model;

public class ChiTietDH {

    /* String dbChiTietDH = "CREATE TABLE CHITIETDH(" +
            "id integer primary key autoincrement," +
            " orderid integer references DATLICH(id)," +
            "madv integer  references DICHVU(id)," +
            " tendv text," +
            "tonggia integer)";*/


        private int id;
        private int orderid;
        private int madv;
        private String tendv;
        private int tonggia;

    public ChiTietDH(int id, int orderid, int madv, String tendv, int tonggia) {
        this.id = id;
        this.orderid = orderid;
        this.madv = madv;
        this.tendv = tendv;
        this.tonggia = tonggia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getMadv() {
        return madv;
    }

    public void setMadv(int madv) {
        this.madv = madv;
    }

    public String getTendv() {
        return tendv;
    }

    public void setTendv(String tendv) {
        this.tendv = tendv;
    }

    public int getTonggia() {
        return tonggia;
    }

    public void setTonggia(int tonggia) {
        this.tonggia = tonggia;
    }
}
