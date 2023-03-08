package com.example.da_1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.da_1.DAO.DAOLoaiDV;
import com.example.da_1.DAO.DichVuDAO;
import com.example.da_1.Model.DichVu;
import com.example.da_1.Model.LoaiDV;
import com.example.da_1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.ViewHolder> {
    private ArrayList<DichVu> list;
    private Context c;
    DichVuDAO dichVuDAO;
    int index,i;

    public DichVuAdapter(ArrayList<DichVu> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
    View view = inflater.inflate(R.layout.item_dichvu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Glide.with(c).load(String.valueOf(list.get(position).getAnhDV())).into(holder.iv_dichvu);
                holder.tv_maDV.setText(String.valueOf(list.get(position).getId()));
                holder.tv_tenDV.setText(String.valueOf(list.get(position).getTenDV()));
                holder.tv_tenloaiDV.setText(String.valueOf(list.get(position).getTenloai()));
                holder.tv_giaDV.setText(String.valueOf(list.get(position).getGiaDV()));
                holder.btn_xoaDV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dichVuDAO = new DichVuDAO(c);
                        boolean check = dichVuDAO.deleteDichVu(list.get(holder.getAdapterPosition()).getId());
                        if (check){
                            list.clear();
                            list = dichVuDAO.getDichVu();
                            notifyDataSetChanged();
                            Toast.makeText(c, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(c, "Lỗi!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                holder.btn_suaDV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        index =(int) list.get(holder.getAdapterPosition()).getId();
                        i = (int) holder.getAdapterPosition();
                        showDialogUpdate();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_maDV,tv_tenDV,tv_tenloaiDV,tv_giaDV;
    ImageView iv_dichvu;
    AppCompatButton btn_suaDV,btn_xoaDV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_dichvu = itemView.findViewById(R.id.iv_imgDV);
            tv_maDV = itemView.findViewById(R.id.tv_maDVItem);
            tv_tenDV= itemView.findViewById(R.id.tv_tenDVitem);
            tv_tenloaiDV = itemView.findViewById(R.id.tv_tenLoaiDVItem);
            tv_giaDV = itemView.findViewById(R.id.tv_giaDVItem);
            btn_suaDV = itemView.findViewById(R.id.iv_suaDV);
            btn_xoaDV = itemView.findViewById(R.id.iv_xoaDV);
        }
    }
    private void getDataLoaiDV(Spinner spnLS){
        DAOLoaiDV daoLoaiSach = new DAOLoaiDV(c);
        ArrayList<LoaiDV> list = daoLoaiSach.getAllLoaiDV();

        ArrayList<HashMap<String,Object>> listHM = new ArrayList<>();
        for (LoaiDV ls : list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("id",ls.getId());
            hs.put("tenloaiDV",ls.getTenloai());
            listHM.add(hs);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                c,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenloaiDV"},
                new int[]{android.R.id.text1});
        spnLS.setAdapter(adapter);
    }
    @SuppressLint("MissingInflatedId")
    private void showDialogUpdate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_updatedichvu,null);
        EditText edt_uptenDV = view.findViewById(R.id.edt_tenDVUpdate);
        EditText edt_upgiatDV = view.findViewById(R.id.edt_giaDVUpdate);
        Spinner spinnerLoai = view.findViewById(R.id.spn_loaiDvUpdate);
        getDataLoaiDV(spinnerLoai);
        builder.setView(view);


        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                HashMap<String,Object> hsTV = (HashMap<String, Object>)spinnerLoai.getSelectedItem();
                int maloai = (int) hsTV.get("id");
                String tensach = edt_uptenDV.getText().toString();
                String giathue = edt_upgiatDV.getText().toString();
                dichVuDAO = new DichVuDAO(c);
                if (tensach.equals("")|| giathue.equals("")){
                    Toast.makeText(c, "Lỗi!!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                }else {
                boolean check = dichVuDAO.updateDichVu(new DichVu(index,tensach,maloai,Integer.parseInt(giathue)));
                    if(check){
                        list.clear();
                        list = dichVuDAO.getDichVu();
                        notifyDataSetChanged();
                        Toast.makeText(c, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(c, "Lỗi!!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
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
}
