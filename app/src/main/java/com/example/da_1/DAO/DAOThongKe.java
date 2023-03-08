package com.example.da_1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.DichVu;


import java.util.ArrayList;

public class DAOThongKe {
    DbHelper dbhelper;
    SQLiteDatabase db;
    public DAOThongKe(Context c){
        dbhelper =new DbHelper(c);
    }

    public ArrayList<DichVu> getTop(){
        ArrayList<DichVu> list = new ArrayList<>();
        db = dbhelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT dl.iddichvu, dv.tenDV, dv.giaDV, COUNT (dl.iddichvu) FROM DATLICH dl, DICHVU dv WHERE  dl.iddichvu = dv.id GROUP BY dl.iddichvu, dv.id ORDER BY COUNT (dl.iddichvu) DESC LIMIT 10",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                    list.add(new DichVu(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }



}
