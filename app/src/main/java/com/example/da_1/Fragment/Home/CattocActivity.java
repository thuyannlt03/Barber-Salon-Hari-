package com.example.da_1.Fragment.Home;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.CatTocAdapter;
import com.example.da_1.Adapter.DichVuAdapter;
import com.example.da_1.DAO.DAOCatToc;
import com.example.da_1.DAO.DichVuDAO;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CattocActivity extends AppCompatActivity {
    RecyclerView recyclerViewDV;
    DAOCatToc daoCatToc;
    ArrayList<DichVu> list;
    SharedPreferences sharedPreferences;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_toc);

        recyclerViewDV = findViewById(R.id.cattoc);
        loading();

    }

    private void loading(){
        sharedPreferences =getSharedPreferences("thongtin",MODE_PRIVATE);
        id = sharedPreferences.getInt("id",-1);
        daoCatToc = new DAOCatToc(this);
        list = daoCatToc.getCatToc(1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewDV.setLayoutManager(linearLayoutManager);
        CatTocAdapter adapter = new CatTocAdapter(this,list,id);
        recyclerViewDV.setAdapter(adapter);
    }
}
