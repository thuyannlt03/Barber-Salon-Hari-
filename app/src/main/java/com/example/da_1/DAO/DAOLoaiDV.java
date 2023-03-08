package com.example.da_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.LoaiDV;

import java.util.ArrayList;

public class DAOLoaiDV {
    DbHelper dbhelper;
    SQLiteDatabase db;

    public DAOLoaiDV(Context c){
        dbhelper = new DbHelper(c);
    }

    public ArrayList<LoaiDV> getAllLoaiDV(){
        ArrayList<LoaiDV> list = new ArrayList<>();
        db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LOAIDV",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiDV(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public  boolean insertLoaiDV(LoaiDV loaiDV){
        db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT tenloaiDV FROM LOAIDV where tenloaiDV = ? ",new String[]{loaiDV.getTenloai()});
        if (cursor.getCount() != 0) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloaiDV",loaiDV.getTenloai());
        long check = db.insert("LOAIDV",null,contentValues);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
    public  boolean UpdateLoaiDV(int maloai,String tenloai){
        db = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenloaiDV",tenloai);
        int check = db.update("LOAIDV",contentValues,"id = ? ",new String[]{String.valueOf(maloai)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }
    public  int DeleteLoaiSach(int maloai){
        db = dbhelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM DICHVU WHERE maldv = ?",new String[]{String.valueOf(maloai)});
        if (cursor.getCount() != 0){
            return -1;
        }
        int check = db.delete("LOAIDV","id = ? ",new String[]{String.valueOf(maloai)});
        if(check == -1){
            return 0;
        }else{
            return 1;
        }
    }
}
