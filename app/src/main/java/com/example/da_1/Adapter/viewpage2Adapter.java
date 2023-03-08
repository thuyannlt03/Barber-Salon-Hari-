package com.example.da_1.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.da_1.DAO.DangKyDichVuDAO;
import com.example.da_1.Fragment.Booking.fragment_booklich;
import com.example.da_1.Fragment.Booking.fragment_checkLich;
import com.example.da_1.Model.DatLich;

import java.util.ArrayList;

public class viewpage2Adapter extends FragmentStateAdapter {

    public viewpage2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new fragment_booklich();
            case 1:
                return new fragment_checkLich();
            default:
                return new fragment_booklich();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
