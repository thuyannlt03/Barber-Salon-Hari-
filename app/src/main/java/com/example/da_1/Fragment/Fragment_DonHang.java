package com.example.da_1.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.DonHangAdapter;
import com.example.da_1.Adapter.NguoiDungAdapter;
import com.example.da_1.DAO.DAODonHang;
import com.example.da_1.DAO.DangKyDichVuDAO;
import com.example.da_1.DAO.DichVuDAO;
import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.DatLich;
import com.example.da_1.Model.DichVu;
import com.example.da_1.Model.NguoiDung;
import com.example.da_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Fragment_DonHang extends Fragment {
    RecyclerView recyclerViewPM;
    FloatingActionButton btn_add;
    DAODonHang daoDonHang;
    ArrayList<DatLich> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_qldonhang, container, false);

        recyclerViewPM = view.findViewById(R.id.recyclerViewQLdonhang);
        btn_add = view.findViewById(R.id.btn_addDH);

        loading();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        return view;
    }
    @SuppressLint("MissingInflatedId")
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_insertphieumuon,null);
        Spinner spnTV = view.findViewById(R.id.spn_tv);
        Spinner spnSach = view.findViewById(R.id.spn_dv);
        getDataTV(spnTV);
        getDataDV(spnSach);
        TextView ngaydl = view.findViewById(R.id.ngaydatlich);
        TextView gio = view.findViewById(R.id.giodatlich);
        Calendar calendar= Calendar.getInstance();
        ngaydl.setOnClickListener(new View.OnClickListener() {
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

                        ngaydl.setText(ngay + "/" + thang +"/"+ i);
                        ngaydl.setTextColor(Color.parseColor("#FF9800"));
                    }
                },calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        gio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute <10 && minute > -1){
                            gio.setText(hourOfDay + " : " +minute);
                        }
                        gio.setText(hourOfDay + " : " + minute);
                        gio.setTextColor(Color.parseColor("#FF9800"));
                    }
                },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                );
                timePickerDialog.show();
            }
        });
        builder.setView(view);


        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<String,Object> hsTV = (HashMap<String, Object>)spnTV.getSelectedItem();
                int matv = (int) hsTV.get("id");

                HashMap<String,Object> hsSach = (HashMap<String, Object>)spnSach.getSelectedItem();
                int maDV = (int) hsSach.get("id");

                int gia = (int) hsSach.get("giaDV");
                String ngaydlll = ngaydl.getText().toString();
                String giodlll = gio.getText().toString();
                InsertLich(matv,maDV,ngaydlll,giodlll,gia);
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
    private void loading(){
        daoDonHang = new DAODonHang(getContext());
        list = daoDonHang.getDatlich();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewPM.setLayoutManager(linearLayoutManager);
        DonHangAdapter adapter = new DonHangAdapter(list,getContext());
        recyclerViewPM.setAdapter(adapter);
    }
    private void getDataTV(Spinner spnTV){
        UserDAO dao = new UserDAO(getContext());
        ArrayList<NguoiDung> list = dao.getAllUser();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (NguoiDung tv : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("id",tv.getId());
            hs.put("hoten",tv.getHoten());
            listHM.add(hs);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1});
        spnTV.setAdapter(adapter);
    }
    private void getDataDV(Spinner spnSach){
        DichVuDAO daoSach = new DichVuDAO(getContext());
        ArrayList<DichVu> list = daoSach.getDichVu();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (DichVu s : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("id",s.getId());
            hs.put("tenDV",s.getTenDV());
            hs.put("giaDV",s.getGiaDV());
            listHM.add(hs);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenDV"},
                new int[]{android.R.id.text1});
        spnSach.setAdapter(adapter);
    }
    private void InsertLich(int id, int madv, String ngay, String gio, int giadv){
        UserDAO dao =  new UserDAO(getContext());
        if (ngay.equals("") || gio.equals("")) {
            Toast.makeText(getActivity(), "Lỗi!!! Vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
        } else {
            dao.dangkyDichVu(id,madv,ngay,gio,giadv,0);
            Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
            loading();
        }
    }
}
