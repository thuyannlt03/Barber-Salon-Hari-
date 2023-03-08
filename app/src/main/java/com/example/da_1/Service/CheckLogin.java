package com.example.da_1.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.da_1.DAO.LoginDAO;

public class CheckLogin extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String user = bundle.getString("user");
        String pass = bundle.getString("pass");

        String mausdt = "0\\d{9}";
        Boolean checkloai = user.matches(mausdt);
        LoginDAO dao = new LoginDAO(this);

        if(checkloai == false){
            Boolean check = dao.kiemtraLogin(user,pass);
            xuat(check);
        }else{
            Boolean check = dao.kiemtraLoginND(user,pass);
            xuat(check);
        }
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
        intentBR.setAction("KtraDangNhap");
        sendBroadcast(intentBR);
    }
}
