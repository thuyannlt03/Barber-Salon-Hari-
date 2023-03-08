package com.example.da_1.Adapter.AdapterNews;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.da_1.Fragment.News.fragment_SoHoa;
import com.example.da_1.Fragment.News.fragment_SucKhoe;
import com.example.da_1.Fragment.News.fragment_TheThao;
import com.example.da_1.Fragment.News.fragment_ThoiSu;
import com.example.da_1.Fragment.News.fragment_TrangChu;
import com.example.da_1.Fragment.News.fragment_giaoduc;

public class viewpageNewsAdapter extends FragmentStateAdapter {

    public viewpageNewsAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new fragment_TrangChu();
            case 1:
                return new fragment_ThoiSu();
            case 2:
                return new fragment_giaoduc();
            case 3:
                return new fragment_SucKhoe();
            case 4:
                return new fragment_SoHoa();
            case 5:
                return new fragment_TheThao();
            default:
                return new fragment_TrangChu();
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
