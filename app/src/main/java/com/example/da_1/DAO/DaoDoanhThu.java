package com.example.da_1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.DichVu;

import java.util.ArrayList;

public class DaoDoanhThu {
    DbHelper dbhelper;
    SQLiteDatabase db;
    public DaoDoanhThu(Context c){
        dbhelper =new DbHelper(c);
    }
    public ArrayList<DichVu> getdoanhthu(){
        ArrayList<DichVu> list = new ArrayList<>();
        db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT dv.tenDV, SUM(dl.giaDV) FROM DATLICH dl, DICHVU dv WHERE dl.iddichvu = dv.id GROUP BY iddichvu",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new DichVu(cursor.getString(0), cursor.getInt(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
