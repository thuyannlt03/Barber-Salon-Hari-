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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.DAO.DAOLoaiDV;
import com.example.da_1.Model.DichVu;
import com.example.da_1.Model.LoaiDV;
import com.example.da_1.R;

import java.util.ArrayList;

public class LoaiDVAdapter extends RecyclerView.Adapter<LoaiDVAdapter.ViewHolder> {
    private ArrayList<LoaiDV> list;
    private Context c;
    DAOLoaiDV daoLoaiDV;
    String tenloai;
    int index,i;

    public LoaiDVAdapter(ArrayList<LoaiDV> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaidv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.tv_maloai.setText(String.valueOf(list.get(position).getId()));
                holder.tv_tenloai.setText(String.valueOf(list.get(position).getTenloai()));
                holder.iv_xoaloai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        daoLoaiDV = new DAOLoaiDV(c);
                        int check = daoLoaiDV.DeleteLoaiSach(list.get(holder.getAdapterPosition()).getId());
                        switch (check){
                            case 1:
                                list.clear();
                                list = daoLoaiDV.getAllLoaiDV();
                                notifyDataSetChanged();
                                break;
                            case -1:
                                Toast.makeText(c, "Lỗi!!! Có sách thuộc loại sách này", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(c, "Lỗi!!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                holder.iv_sualoai.setOnClickListener(new View.OnClickListener() {
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
    TextView tv_maloai,tv_tenloai;
    ImageView iv_sualoai,iv_xoaloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maloai = itemView.findViewById(R.id.tv_maloaiitem);
            tv_tenloai= itemView.findViewById(R.id.tv_tenloaiitem);
            iv_sualoai = itemView.findViewById(R.id.iv_sualoai);
            iv_xoaloai = itemView.findViewById(R.id.iv_xoaloai);
        }
    }
    @SuppressLint("MissingInflatedId")
    private void showDialogUpdate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_updateloaidv,null);
        EditText edt_uptenloai = view.findViewById(R.id.edt_tenloaiUpdate);
        edt_uptenloai.setText(list.get(i).getTenloai());
        builder.setView(view);


        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tenloai = edt_uptenloai.getText().toString();
                daoLoaiDV = new DAOLoaiDV(c);
                if (tenloai.equals("")){
                    Toast.makeText(c, "Lỗi!!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                }else {
                    boolean check = daoLoaiDV.UpdateLoaiDV(index,tenloai);
                    if(check){
                        list.clear();
                        list = daoLoaiDV.getAllLoaiDV();
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
