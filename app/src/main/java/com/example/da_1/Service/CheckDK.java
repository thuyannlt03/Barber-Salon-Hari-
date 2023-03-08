package com.example.da_1.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.da_1.DAO.LoginDAO;
import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.NguoiDung;

public class CheckDK extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String ten = bundle.getString("ten");
        String ngaysinh = bundle.getString("ngaysinh");
        String sdt = bundle.getString("sdt");
        String pass = bundle.getString("matkhau");

        UserDAO dao = new UserDAO(this);
        Boolean check = dao.insertUser(new NguoiDung(ten,ngaysinh,sdt,pass));
        xuat(check);

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private void xuat(Boolean check){
        Intent intentBR = new Intent();
        Bundle bundleBR = new Bundle();
        bundleBR.putBoolean("check",check);
        intentBR.putExtras(bundleBR);
        intentBR.setAction("KtraSignup");
        sendBroadcast(intentBR);
    }
}
