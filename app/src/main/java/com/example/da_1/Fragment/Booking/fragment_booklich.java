package com.example.da_1.Fragment.Booking;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.DatLichAdapter;
import com.example.da_1.DAO.DichVuDAO;
import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;

import java.util.ArrayList;


public class fragment_booklich extends Fragment {
    RecyclerView recyclerViewDichVu;
    ArrayList<DichVu> list;
    int id;
    SharedPreferences sharedPreferences;
    int load = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dkkhoahoc, container, false);
        recyclerViewDichVu =  view.findViewById(R.id.DKdichvu);
        DichVuDAO dao = new DichVuDAO(getContext());
        sharedPreferences = getContext().getSharedPreferences("thongtin",MODE_PRIVATE);
        id = sharedPreferences.getInt("id",-1);
        list = dao.getDichVu();
        loadData();
        return view;
    }
    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDichVu.setLayoutManager(linearLayoutManager);
        DatLichAdapter adapter = new DatLichAdapter(getContext(),list,id);
        recyclerViewDichVu.setAdapter(adapter);
    }
}
