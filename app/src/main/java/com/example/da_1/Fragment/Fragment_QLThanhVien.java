package com.example.da_1.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.NguoiDungAdapter;
import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.NguoiDung;
import com.example.da_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_QLThanhVien extends Fragment {
    RecyclerView recyclerViewTV;
    FloatingActionButton btn_addTV;
    UserDAO userDAOo;
    ArrayList<NguoiDung> list;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_qlnguoidung, container, false);
        recyclerViewTV = view.findViewById(R.id.recyclerViewQLTNguoiDung);
        btn_addTV = view.findViewById(R.id.btn_addNguoiDung);


        btn_addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });
        return view;
    }
    private void loading(){
        userDAOo = new UserDAO(getContext());
        list = userDAOo.getAllUser();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTV.setLayoutManager(linearLayoutManager);
        NguoiDungAdapter adapter = new NguoiDungAdapter(list,getContext());
        recyclerViewTV.setAdapter(adapter);
    }
    @SuppressLint("MissingInflatedId")
    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addthanhvien,null);
        EditText edt_tentv = view.findViewById(R.id.edt_tentvAdd);
        EditText edt_sdt = view.findViewById(R.id.edt_sdttvAdd);
        EditText edt_matkhau = view.findViewById(R.id.edt_matkhautvAdd);
        TextView edt_namsinhtv = view.findViewById(R.id.edt_namsinhtvAdd);
        Calendar calendar= Calendar.getInstance();

        edt_namsinhtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = "";
                        String thang = "";
                        if(i2<10){
                            ngay = "0"+ i2 ;
                        }else {
                            ngay = String.valueOf(i2);
                        }

                        if((i1 + 1)<10){
                            thang = "0"+ (i1+1) ;
                        }else {
                            thang = String.valueOf((i1+1));
                        }

                        edt_namsinhtv.setText(ngay + "/" + thang +"/"+ i);
                    }
                },calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        builder.setView(view);


        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tentv = edt_tentv.getText().toString();
                String sdt = edt_sdt.getText().toString();
                String matkhau = edt_matkhau.getText().toString();
                String namsinh = edt_namsinhtv.getText().toString();
                if (tentv.equals("")|| sdt.equals("") || matkhau.equals("") || namsinh.equals("")) {
                    Toast.makeText(getActivity(), "Lỗi!!! Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                }else{
                    InsertThanhVien(new NguoiDung(tentv, namsinh, sdt, matkhau));
                }
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

    private void InsertThanhVien(NguoiDung nd) {
        boolean ktr = userDAOo.insertUser(nd);
        if (ktr) {
            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            loading();
        } else {
            Toast.makeText(getActivity(), "Lỗi!!! Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        loading();
    }
}
