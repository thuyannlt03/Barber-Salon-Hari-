package com.example.da_1.Fragment.Booking;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.CheckLichAdapter;
import com.example.da_1.Adapter.DatLichAdapter;
import com.example.da_1.DAO.DangKyDichVuDAO;
import com.example.da_1.DAO.DichVuDAO;
import com.example.da_1.Model.DatLich;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;

import java.util.ArrayList;

public class fragment_checkLich extends Fragment  {
    RecyclerView recyclerViewDichVuDL;
    Button btn_huylich;
    ArrayList<DatLich> list;
    SharedPreferences sharedPreferences;
    DangKyDichVuDAO dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khdadk,container,false);
        recyclerViewDichVuDL =  view.findViewById(R.id.list_lichdadat);

        dao = new DangKyDichVuDAO(getContext());

        return view;
    }
    private void loadData(){
        sharedPreferences = getContext().getSharedPreferences("thongtin",MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",-1);
        list = dao.getDatlich(id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDichVuDL.setLayoutManager(linearLayoutManager);
        CheckLichAdapter adapter = new CheckLichAdapter(getContext(),list,id);
        recyclerViewDichVuDL.setAdapter(adapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
//        Toast.makeText(requireActivity(), "resume", Toast.LENGTH_SHORT).show();
    }

}
