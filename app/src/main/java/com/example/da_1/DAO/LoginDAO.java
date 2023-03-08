package com.example.da_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.Admin;

import java.util.ArrayList;

public class LoginDAO {
    DbHelper dbhelper;
    SQLiteDatabase db;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPreferences2;
    public  LoginDAO(Context c){
        dbhelper = new DbHelper(c);
        sharedPreferences = c.getSharedPreferences("thongtin",Context.MODE_PRIVATE);
        sharedPreferences2 = c.getSharedPreferences("tenkh",Context.MODE_PRIVATE);
    }

    public boolean kiemtraLogin(String user, String pass){
        SQLiteDatabase dp = dbhelper.getReadableDatabase();
        Cursor cursor = dp.rawQuery("SELECT * FROM ADMIN where username = ? AND matkhau = ?",new String[]{user,pass});
        if (cursor.getCount() !=0){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.apply();
            return true;
        }
        return false;
    }
    public boolean kiemtraLoginND(String user, String pass){
        SQLiteDatabase dp = dbhelper.getReadableDatabase();
        Cursor cursor = dp.rawQuery("SELECT * FROM NGUOIDUNG where sdt = ? AND matkhau = ?",new String[]{user,pass});
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id",cursor.getInt(0));
            editor.apply();
            SharedPreferences.Editor editor2 = sharedPreferences2.edit();
            editor2.putString("hoten",cursor.getString(1));
            editor2.apply();
            return true;
        }
        return false;
    }

    public ArrayList<Admin> getlistAdmin(){
        ArrayList<Admin> list = new ArrayList<>();
        db = dbhelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ADMIN",null);
        if (cursor.getCount() != 0 ){
            cursor.moveToFirst();
            do {
                list.add(new Admin(cursor.getInt(0),cursor.getString(1),
                        cursor.getString(2),cursor.getString(3),
                        cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }

}
