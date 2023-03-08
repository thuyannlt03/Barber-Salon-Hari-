package com.example.da_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.DatLich;

import java.util.ArrayList;

public class DAODonHang {
    DbHelper dbhelper;
    SQLiteDatabase db;
    public DAODonHang(Context c){
        dbhelper = new DbHelper(c);
    }
    public ArrayList<DatLich> getDatlich() {
        ArrayList<DatLich> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT dl.id, nd.hoten, dv.tenDV, dl.ngay, dl.gio FROM DATLICH dl, NGUOIDUNG nd, DICHVU dv WHERE dl.userid = nd.id AND dl.iddichvu = dv.id  ORDER BY dl.id DESC", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DatLich(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
            } while (cursor.moveToNext());
        }

        return list;
    }
    public boolean XoaDonHang(int id) {
        db = dbhelper.getWritableDatabase();
        Long check = Long.valueOf(db.delete("DATLICH", "id = ? ", new String[] {String.valueOf(id)}));
        if (check == -1) return false;
        return true;
    }

    public boolean CapNhatDichVu(int id, int madv, String ngay, String gio, int giadv, int trangthai) {
        db = dbhelper.getWritableDatabase();
        gio = gio.replace(" ", "");
        ngay = ngay.replace(" ", "");
        Cursor cursor =
                db.rawQuery(
                        "SELECT * FROM DATLICH  WHERE gio = ? AND ngay = ?",
                        new String[] {String.valueOf(gio), String.valueOf(ngay)});
        if (cursor.getCount() != 0) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid", id);
        contentValues.put("iddichvu", madv);
        contentValues.put("giadv", giadv);
        contentValues.put("ngay", ngay);
        contentValues.put("gio", gio);
        contentValues.put("trangthai", trangthai);
        Long check = db.insert("DATLICH", null, contentValues);
        if (check == -1) return false;
        return true;
    }
}
