package com.example.da_1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.DichVu;

import java.util.ArrayList;

public class DAOCatToc {


    DbHelper dbhelper;
    SQLiteDatabase db;
    public DAOCatToc(Context c){
        dbhelper = new DbHelper(c);
    }

    public ArrayList<DichVu> getCatToc(int i) {
        ArrayList<DichVu> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT dv.id, dv.tenDV,ldv.tenloaiDV, dv.giaDV,dv.anhDV  FROM DICHVU dv,  LOAIDV ldv  WHERE dv.maldv = ? AND   dv.maldv = ldv.id  ",new String[]{String.valueOf(i)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Integer id = cursor.getInt(1);
                if(id.equals(id)) {
                    list.add(
                            new DichVu(
                                    cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4)));
                }
            } while (cursor.moveToNext());
        }
        return list;
    }
}
