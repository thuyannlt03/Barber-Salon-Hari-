package com.example.da_1.Fragment.News;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.da_1.Adapter.AdapterNews.viewpageNewsAdapter;
import com.example.da_1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class fragment_news extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,container,false);
        TabLayout tabLayout = view.findViewById(R.id.tablayoutNews);
        ViewPager2 viewPagerNews = view.findViewById(R.id.viewpageNews);

        viewpageNewsAdapter adapter = new viewpageNewsAdapter(this);
        viewPagerNews.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPagerNews, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Trang Chủ");
                        break;
                    case 1:
                        tab.setText("Thời Sự");
                        break;
                    case 2:
                        tab.setText("Giáo Dục");
                        break;
                    case 3:
                        tab.setText("Sức Khỏe");
                        break;
                    case 4:
                        tab.setText("Số Hóa");
                        break;
                    case 5:
                        tab.setText("Thể Thao");
                        break;
                }
            }
        }).attach();
        return view;
    }
}
