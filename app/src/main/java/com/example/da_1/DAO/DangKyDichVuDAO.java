package com.example.da_1.DAO;

import static android.content.Context.MODE_PRIVATE;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.DatLich;
import com.example.da_1.Model.DichVu;

import java.util.ArrayList;

public class DangKyDichVuDAO {
    DbHelper dbhelper;
    SQLiteDatabase db;
    DangKyDichVuDAO dao;
    ArrayList<DatLich> list;
    SharedPreferences sharedPreferences;
    Context c;
    int id;
    public  DangKyDichVuDAO(Context c){
        dbhelper = new DbHelper(c);
    }

    public ArrayList<DatLich> getDatlich(int id) {
        ArrayList<DatLich> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nd.hoten, dv.tenDV, dl.ngay, dl.gio FROM DATLICH dl, NGUOIDUNG nd, DICHVU dv WHERE dl.userid = nd.id AND dl.iddichvu = dv.id  AND dl.userid = ? ORDER BY dl.id DESC", new String[]{String.valueOf(id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new DatLich(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            } while (cursor.moveToNext());
        }

        return list;
    }
}
