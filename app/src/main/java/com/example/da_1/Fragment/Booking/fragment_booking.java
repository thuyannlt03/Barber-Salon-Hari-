package com.example.da_1.Fragment.Booking;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.da_1.Adapter.CheckLichAdapter;
import com.example.da_1.Adapter.viewpage2Adapter;
import com.example.da_1.DAO.DangKyDichVuDAO;
import com.example.da_1.Model.DatLich;
import com.example.da_1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class fragment_booking extends Fragment {
     ArrayList<DatLich> list;
    DangKyDichVuDAO dao;
    SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking,container,false);
        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.viewpage2);

        viewpage2Adapter adapter = new viewpage2Adapter(this);
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Đăng ký dịch vụ");
                        break;
                    case 1:
                        tab.setText(" Dịch vụ đã đăng ký");
                        break;
                }
            }
        }).attach();
        return view;
    }
}
