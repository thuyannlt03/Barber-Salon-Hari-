package com.example.da_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.DichVu;

import java.util.ArrayList;

public class DichVuDAO {

    DbHelper dbhelper;
    SQLiteDatabase db;
    public DichVuDAO(Context c){
        dbhelper = new DbHelper(c);
    }

    public ArrayList<DichVu> getDichVu() {
        ArrayList<DichVu> list = new ArrayList<>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT dv.id, dv.tenDV,ldv.tenloaiDV, dv.giaDV,dv.anhDV   FROM DICHVU dv,  LOAIDV ldv  WHERE dv.maldv = ldv.id",null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
        list.add(
            new DichVu(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3),cursor.getString(4)));

            } while (cursor.moveToNext());
        }
        return list;
    }


    public boolean addDichVu(DichVu dichVu){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDV",dichVu.getTenDV());
        values.put("giaDV",dichVu.getGiaDV());
        values.put("maldv",dichVu.getMaloai());
        values.put("anhDV"," https://firebasestorage.googleapis.com/v0/b/da1android.appspot.com/o/cattoc.jpg?alt=media&token=e1c07fa4-9be9-4f6e-ba3f-ef87b8738089");
        long check =db.insert("DICHVU",null,values);
        if (check == -1)
            return false;
        return true;
    }
    public boolean updateDichVu(DichVu dichVu){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDV",dichVu.getTenDV());
        values.put("maldv",dichVu.getMaloai());
        values.put("giaDV",dichVu.getGiaDV());
        long check =db.update("DICHVU",values,"id = ?",
                new String[]{String.valueOf(dichVu.getId())});
        if (check == -1)
            return false;

        return true;
    }

    public boolean deleteDichVu(int id) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long check = db.delete("DICHVU", "id=?", new String[]{String.valueOf(id)});
        if (check == -1)
            return false;

        return true;
    }
}
