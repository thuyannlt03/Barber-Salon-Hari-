package com.example.da_1.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.da_1.DAO.DangKyDichVuDAO;
import com.example.da_1.DAO.DichVuDAO;
import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.DatLich;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CheckLichAdapter extends RecyclerView.Adapter<CheckLichAdapter.ViewHolder>{
    private Context c;
    private ArrayList<DatLich> list;
    DangKyDichVuDAO dao;
    int index,i,id;


    public CheckLichAdapter(Context c, ArrayList<DatLich> list, int id) {
        this.c = c;
        this.list = list;
        this.id = id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_lichdadat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_tenDV.setText(list.get(position).getTenDV());
        holder.tv_tenKH.setText(list.get(position).getTenKH());
        holder.tv_ngayDat.setText(list.get(position).getNgay());
        holder.tv_giodat.setText(list.get(position).getGio());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_tenDV, tv_tenKH, tv_ngayDat,tv_giodat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tenKH = itemView.findViewById(R.id.tv_tenKHDat);
            tv_tenDV = itemView.findViewById(R.id.tv_tenDVDat);
            tv_ngayDat = itemView.findViewById(R.id.tv_ngayDat);
            tv_giodat = itemView.findViewById(R.id.tv_gioDat);
        }
    }
}
