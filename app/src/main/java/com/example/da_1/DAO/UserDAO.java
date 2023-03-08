package com.example.da_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.NguoiDung;

import java.util.ArrayList;

public class UserDAO {
  DbHelper dbhelper;
  SQLiteDatabase db;

  public UserDAO(Context c) {
    dbhelper = new DbHelper(c);
  }

  public ArrayList<NguoiDung> getAllUser() {
    ArrayList<NguoiDung> list = new ArrayList<>();
    db = dbhelper.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG", null);
    if (cursor.getCount() != 0) {
      cursor.moveToFirst();
      do {
        list.add(
            new NguoiDung(
                cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
      } while (cursor.moveToNext());
    }
    return list;
  }

  public boolean insertUser(NguoiDung us) {
    db = dbhelper.getReadableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("hoten", us.getHoten());
    contentValues.put("ngaysinh", us.getNgaysinh());
    contentValues.put("sdt", us.getSdt());
    contentValues.put("matkhau", us.getMatkhau());
    long check = db.insert("NGUOIDUNG", null, contentValues);
    if (check == -1) {
      return false;
    } else {
      return true;
    }
  }

  public int doimatkhau(String sdt, String oldPass, String newPass) {
    SQLiteDatabase db = dbhelper.getWritableDatabase();
    Cursor cursor =
        db.rawQuery(
            "SELECT * FROM NGUOIDUNG WHERE sdt=? AND matkhau=?", new String[] {sdt, oldPass});
    if (cursor.getCount() > 0) {
      ContentValues values = new ContentValues();
      values.put("matkhau", newPass);
      long check = db.update("NGUOIDUNG", values, "sdt=?", new String[] {sdt});
      if (check == -1) return -1;
      return 1;
    }
    return 0;
  }

  //    public Boolean updatein4(String ten, String sdt, String ngaysinh) {
  //        SQLiteDatabase db = dbhelper.getWritableDatabase();
  //        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG WHERE sdt=?", new String[]{sdt});
  //        if (cursor.getCount() > 0) {
  //            ContentValues values = new ContentValues();
  //            values.put("hoten", ten);
  //            values.put("ngaysinh", ngaysinh);
  //            values.put("sdt", sdt);
  //            long check = db.update("NGUOIDUNG", values, "sdt=?", new String[]{sdt});
  //            if(check == -1){
  //                return false;
  //            }else{
  //                return true;
  //            }

  public boolean updatein4(String sdtup, String ten, String sdt, String ngaysinh) {
    db = dbhelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("hoten", ten);
    values.put("ngaysinh", ngaysinh);
    values.put("sdt", sdt);
    int check = db.update("NGUOIDUNG", values, "sdt = ? ", new String[] {String.valueOf(sdtup)});
    if (check == -1) {
      return false;
    } else {
      return true;
    }
  }

  public boolean updateDVND(String sdtup, String ten, String sdt, String ngaysinh, String matkhau) {
    db = dbhelper.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("hoten", ten);
    values.put("ngaysinh", ngaysinh);
    values.put("sdt", sdt);
    values.put("matkhau", matkhau);
    int check = db.update("NGUOIDUNG", values, "sdt = ? ", new String[] {String.valueOf(sdtup)});
    if (check == -1) {
      return false;
    } else {
      return true;
    }
  }

  public boolean dangkyDichVu(int id, int madv, String ngay, String gio, int giadv, int trangthai) {
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

  public boolean XoaNguoiDung(int id) {
    db = dbhelper.getWritableDatabase();
    Cursor cursor =
        db.rawQuery("SELECT * FROM DATLICH WHERE userid = ? ", new String[] {String.valueOf(id)});
    if (cursor.getCount() != 0) {
      return false;
    }
    Long check = Long.valueOf(db.delete("NGUOIDUNG", "id = ? ", new String[] {String.valueOf(id)}));
    if (check == -1) return false;
    return true;
  }
}
//    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
//            .setTitle("Cập Nhật Thông Tin")
//            .setNegativeButton("Hủy",null)
//            .setPositiveButton("Cập nhật",null);
//    LayoutInflater inflater = getLayoutInflater();
//    View view = inflater.inflate(R.layout.dialog_updateuser, null);
//    EditText  ten =view.findViewById(R.id.edt_upTen);
//    EditText  sdt =view.findViewById(R.id.edt_upSdt);
//    EditText  ngaysinh =view.findViewById(R.id.edt_upNgaySinh);
//
//    Calendar calendar= Calendar.getInstance();
//
//        ngaysinh.setOnClickListener(new View.OnClickListener() {
// @Override
// public void onClick(View v) {
//        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new
// DatePickerDialog.OnDateSetListener() {
// @Override
// public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//        String ngay = "";
//        String thang = "";
//        if(i2<10){
//        ngay = "0"+ i2 ;
//        }else {
//        ngay = String.valueOf(i2);
//        }
//
//        if((i1 + 1)<10){
//        thang = "0"+ (i1+1) ;
//        }else {
//        thang = String.valueOf((i1+1));
//        }
//
//        ngaysinh.setText(ngay + "/" + thang +"/"+ i);
//        }
//        },calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH));
//        datePickerDialog.show();
//        }
//        });
//        builder.setView(view);
//
//        AlertDialog dialog = builder.create();
//        dialog.setCancelable(false);
//        dialog.show();
//
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new
// View.OnClickListener() {
// @Override
// public void onClick(View v) {
//        String tenup = ten.getText().toString();
//        String sdtup = sdt.getText().toString();
//        String ngaysinhup = ngaysinh.getText().toString();
//        if (tenup.equals("") || sdtup.equals("") || ngaysinhup.equals("")) {
//        Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
//        }else {
//        SharedPreferences preferences = getContext().getSharedPreferences("THONGTIN",
// MODE_PRIVATE);
//        String sdt = preferences.getString("sdt","");
//        UserDAO userDAO = new UserDAO(getContext());
//        Boolean check = userDAO.updatein4(sdt, tenup, sdtup, ngaysinhup);
//        if (check) {
//        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//        }else {
//        Toast.makeText(getContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
//        }
//        }
//        }
//        });
//        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new
// View.OnClickListener() {
// @Override
// public void onClick(View v) {
//
//        }
//        });
