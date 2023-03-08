package com.example.da_1.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.LoaiDVAdapter;
import com.example.da_1.DAO.DAOLoaiDV;
import com.example.da_1.Model.LoaiDV;
import com.example.da_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_QLloaiDV extends Fragment {
    RecyclerView recyclerViewLS;
    FloatingActionButton btn_addLS;
    DAOLoaiDV daoLoaiDV;
    ArrayList<LoaiDV> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlloaidv,container,false);
        recyclerViewLS = view.findViewById(R.id.recyclerViewQLloaisach);
        btn_addLS = view.findViewById(R.id.btn_addLoaiSach);

        loading();

        btn_addLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });
        return view;
    }
    private void loading(){
        daoLoaiDV = new DAOLoaiDV(getContext());
        list = daoLoaiDV.getAllLoaiDV();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewLS.setLayoutManager(linearLayoutManager);
        LoaiDVAdapter adapter = new LoaiDVAdapter(list,getContext());
        recyclerViewLS.setAdapter(adapter);
    }
    @SuppressLint("MissingInflatedId")
    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addloaidv,null);
        EditText edt_tenloai = view.findViewById(R.id.edt_tenloai);
        builder.setView(view);


        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenloai = edt_tenloai.getText().toString();
                InsertLoaiSach(tenloai);
            }
        });


        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void InsertLoaiSach(String tenloai){
        LoaiDV loaiDV = new LoaiDV(tenloai);
        if (tenloai.equals("")) {
            Toast.makeText(getActivity(), "Lỗi!!! Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        } else {
            boolean check = daoLoaiDV.insertLoaiDV(loaiDV);
            if (check){
                Toast.makeText(getActivity(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                loading();
            }else Toast.makeText(getActivity(), "Lỗi!!! Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        }
    }
}
