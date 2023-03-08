package com.example.da_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.da_1.Adapter.SliderAdapter;
import com.example.da_1.Fragment.Booking.fragment_booking;
import com.example.da_1.Fragment.Fragment_Account;
import com.example.da_1.Fragment.Fragment_DonHang;
import com.example.da_1.Fragment.Fragment_QLDichVu;
import com.example.da_1.Fragment.Fragment_QLThanhVien;
import com.example.da_1.Fragment.Fragment_QLloaiDV;
import com.example.da_1.Fragment.Home.fragment_home;
import com.example.da_1.Fragment.Map.fragmentMaps;
import com.example.da_1.Fragment.News.fragment_giaoduc;
import com.example.da_1.Fragment.News.fragment_news;
import com.example.da_1.Fragment.fragment_doanhthu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
    implements NavigationBarView.OnItemSelectedListener {
  BottomNavigationView bottomNavigationView;
  FragmentManager manager = getSupportFragmentManager();
  DrawerLayout drawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    drawerLayout = this.findViewById(R.id.drawerlayout);
    TextView tv_chao = this.findViewById(R.id.tv_chao);
    NavigationView navigationView = this.findViewById(R.id.navigationview);

    Toolbar toolbar = this.findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

    bottomNavigationView = this.findViewById(R.id.bottomavigationView);

    bottomNavigationView.setOnItemSelectedListener(this);
    bottomNavigationView.setSelectedItemId(R.id.bottomavigationView);

    manager.beginTransaction().replace(R.id.view, new fragment_home()).commit();

    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                    case R.id.m_loaiDV:
                        manager.beginTransaction()
                                .replace(R.id.view,new Fragment_QLloaiDV())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.m_listDV:
                        manager.beginTransaction()
                                .replace(R.id.view,new Fragment_QLDichVu())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.m_donhang:
                        manager.beginTransaction()
                                .replace(R.id.view,new Fragment_DonHang())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    case R.id.m_thanhvien:
                        manager.beginTransaction()
                                .replace(R.id.view,new Fragment_QLThanhVien())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                case R.id.m_thongke:
                    manager.beginTransaction()
                            .replace(R.id.view,new fragment_doanhthu())
                            .commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
            }
            return false;
        }
    });
    SharedPreferences sharedPreferences = getSharedPreferences("tenkh", MODE_PRIVATE);
    String tenchao = sharedPreferences.getString("hoten", "");
    tv_chao.setText("Xin Chào, " + tenchao);
  }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.m_maps:
        manager.beginTransaction().replace(R.id.view, new fragmentMaps()).commit();
        return true;
      case R.id.m_house:
        manager.beginTransaction().replace(R.id.view, new fragment_home()).commit();
        return true;
      case R.id.m_news:
        manager.beginTransaction().replace(R.id.view, new fragment_news()).commit();
        return true;
      case R.id.m_booking:
        manager.beginTransaction().replace(R.id.view, new fragment_booking()).commit();
        return true;
      case R.id.m_account:
        manager.beginTransaction().replace(R.id.view, new Fragment_Account()).commit();
        return true;
    }
    return false;
  }
}
