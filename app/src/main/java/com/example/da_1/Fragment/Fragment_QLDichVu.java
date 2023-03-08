package com.example.da_1.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.DichVuAdapter;
import com.example.da_1.DAO.DAOLoaiDV;
import com.example.da_1.DAO.DichVuDAO;
import com.example.da_1.Model.DichVu;
import com.example.da_1.Model.LoaiDV;
import com.example.da_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class Fragment_QLDichVu extends Fragment {
    RecyclerView recyclerViewDV;
    FloatingActionButton btn_addDV;
    DichVuDAO daoDichVu;
    ArrayList<DichVu> list;
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qldichvu,container,false);
        recyclerViewDV = view.findViewById(R.id.recyclerViewQLDV);
        btn_addDV = view.findViewById(R.id.btn_addDV);
        loading();

        btn_addDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
        return view;
    }
    private void loading(){
        daoDichVu = new DichVuDAO(getContext());
        list = daoDichVu.getDichVu();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDV.setLayoutManager(linearLayoutManager);
        DichVuAdapter adapter = new DichVuAdapter(list,getContext());
        recyclerViewDV.setAdapter(adapter);
    }
    @SuppressLint("MissingInflatedId")
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_adddichvu,null);
        Spinner spnLDV = view.findViewById(R.id.spn_loaiDV);
        EditText edt_tenDV = view.findViewById(R.id.edt_tenDVAdd);
        EditText edt_giaDV= view.findViewById(R.id.edt_giaDVAdd);
        getDataLoaiSach(spnLDV);
        builder.setView(view);


        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<String,Object> hsTV = (HashMap<String, Object>)spnLDV.getSelectedItem();
                int maloaiDV = (int) hsTV.get("id");
                String tenDV = edt_tenDV.getText().toString();
                String giaDV = edt_giaDV.getText().toString();
                if (tenDV.equals("")|| giaDV.equals("")){
                    Toast.makeText(getActivity(), "Lỗi!!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = InsertLoaiSach(new DichVu(tenDV,maloaiDV,Integer.parseInt(giaDV)));
                    if(check){
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Lỗi!!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                    }
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
    private void getDataLoaiSach(Spinner spnLS){
        DAOLoaiDV daoLoaiDV = new DAOLoaiDV(getContext());
        ArrayList<LoaiDV> list = daoLoaiDV.getAllLoaiDV();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (LoaiDV ls : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("id",ls.getId());
            hs.put("tenloaiDV",ls.getTenloai());
            listHM.add(hs);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloaiDV"},
                new int[]{android.R.id.text1});
        spnLS.setAdapter(adapter);
    }
    private boolean InsertLoaiSach(DichVu dv){
        boolean ktra = daoDichVu.addDichVu(dv);
        if(ktra){
            Toast.makeText(getActivity(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            loading();
        }else{
            Toast.makeText(getActivity(), "Lỗi!!! Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
        }
        return ktra;
    }

    @Override
    public void onResume() {
        super.onResume();
        loading();
    }
}
