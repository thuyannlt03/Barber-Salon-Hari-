package com.example.da_1.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public  DbHelper(Context c){
        super(c,"DBPRO1121",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbAdmin = "CREATE TABLE ADMIN(" +
                "id integer primary key," +
                " username text," +
                " matkhau text," +
                " loai text)";
        db.execSQL(dbAdmin);
        db.execSQL("INSERT INTO ADMIN VALUES (1,'admin','123321','Boss'), (2,'staff','123321','Staff') ");

        String dbNguoiDung = "CREATE TABLE NGUOIDUNG(" +
                "id integer primary key autoincrement," +
                " hoten text," +
                " ngaysinh text," +
                " sdt text," +
                "matkhau text)";
        db.execSQL(dbNguoiDung);
        db.execSQL("INSERT INTO NGUOIDUNG VALUES (1,'Trương Tấn Thành','07/03/2003','0337227649','123321'), (2,'Thuy Ân','08/03/2003','0337227648','123321'), (3,'Công Phát','24/10/2003','0924451336','phatga22') ");

        String dbLoaiDV = "CREATE TABLE LOAIDV(" +
                "id integer primary key autoincrement," +
                "tenloaiDV text)";
        db.execSQL(dbLoaiDV);
        db.execSQL("INSERT INTO LOAIDV VALUES (1,'Cắt Tóc'), (2,'Nhuộm tóc'),(3,'Uốn tóc'),(4,'Trang điểm') ");

        String dbDichVu = "CREATE TABLE DICHVU(" +
                "id integer primary key autoincrement," +
                "tenDV text," +
                "maldv integer references LOAIDV(id)," +
                "giaDV integer," +
                "anhDV text)";
        db.execSQL(dbDichVu);
        db.execSQL("INSERT INTO DICHVU VALUES (1,'Layer',1,10,'https://firebasestorage.googleapis.com/v0/b/da1android.appspot.com/o/cattoc.jpg?alt=media&token=e1c07fa4-9be9-4f6e-ba3f-ef87b8738089'), (2,'Nhuộm Hàn Quốc',2,50,'https://firebasestorage.googleapis.com/v0/b/da1android.appspot.com/o/cattoc.jpg?alt=media&token=e1c07fa4-9be9-4f6e-ba3f-ef87b8738089') ");

        String dbDatLich = "CREATE TABLE DATLICH(" +
                "id integer primary key autoincrement," +
                " userid integer references NGUOIDUNG(id)," +
                " iddichvu integer references DICHVU(id)," +
                " giadv integer references DICHVU(giaDV)," +
                " ngay text," +
                " gio text," +
                "trangthai integer)";
        db.execSQL(dbDatLich);
        db.execSQL("INSERT INTO DATLICH VALUES (1,1,1,10,'22/11/2022','11:00',0), (2,2,2,50,'22/11/2022','11:00',0) ");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS ADMIN");
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS DICHVU");
            db.execSQL("DROP TABLE IF EXISTS THONGTIN");
            db.execSQL("DROP TABLE IF EXISTS DATLICH");
            onCreate(db);
        }
    }
}
