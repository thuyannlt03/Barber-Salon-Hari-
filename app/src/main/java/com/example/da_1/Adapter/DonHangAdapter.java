package com.example.da_1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.DAO.DAODonHang;
import com.example.da_1.Model.DatLich;
import com.example.da_1.R;

import java.util.ArrayList;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private ArrayList<DatLich> list;
    private Context c;

    public DonHangAdapter(ArrayList<DatLich> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
    View view = inflater.inflate(R.layout.item_qllichdat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.tv_maDH.setText(""+list.get(position).getId());
                holder.tv_tenKH.setText(list.get(position).getTenKH());
                holder.tv_tenDV.setText(list.get(position).getTenDV());
                holder.tv_ngayhenDH.setText(list.get(position).getNgay());
                holder.tv_giohenDH.setText(list.get(position).getGio());

                holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DAODonHang daoDonHang = new DAODonHang(c);
                        boolean ktra = daoDonHang.XoaDonHang(list.get(holder.getAdapterPosition()).getId());
                        if (ktra){
                            list.clear();
                            list = daoDonHang.getDatlich();
                            notifyDataSetChanged();
                        }else {
                            Toast.makeText(c, "Lỗi!!! Xin hãy thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void dialogUpdate() {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
    TextView tv_maDH,  tv_tenKH, tv_tenDV, tv_ngayhenDH, tv_giohenDH;
    AppCompatButton btn_xoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maDH = itemView.findViewById(R.id.tv_maKHDat);
            tv_tenKH= itemView.findViewById(R.id.tv_tenKHDat);
            tv_tenDV = itemView.findViewById(R.id.tv_tenDVDat);
            tv_ngayhenDH = itemView.findViewById(R.id.tv_ngayDat);
            tv_giohenDH= itemView.findViewById(R.id.tv_gioDat);
            btn_xoa = itemView.findViewById(R.id.iv_xoaDH);
        }
    }
}
