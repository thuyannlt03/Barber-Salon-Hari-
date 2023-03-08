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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;

import java.util.ArrayList;
import java.util.Calendar;

public class CatTocAdapter extends RecyclerView.Adapter<CatTocAdapter.ViewHolder>{
    private Context c;
    private ArrayList<DichVu> list;
    private int id;
    int index,i,giadv;
    SharedPreferences sharedPreferences;
    boolean checktt;

    public CatTocAdapter(Context c, ArrayList<DichVu> list,int id) {
        this.c = c;
        this.list = list;
        this.id = id;
    }

    @NonNull
    @Override
    public CatTocAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_dv,parent,false);
        return new CatTocAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatTocAdapter.ViewHolder holder, int position) {
        Glide.with(c).load(String.valueOf(list.get(position).getAnhDV())).into(holder.iv_anhDV);
        holder.tv_maDV.setText(String.valueOf(list.get(position).getId()));
        holder.tv_tenDV.setText(list.get(position).getTenDV());
        holder.tv_tenLoaiDV.setText(list.get(position).getTenloai());
        holder.tv_giaDV.setText(String.valueOf(list.get(position).getGiaDV())+"$");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_anhDV;
        TextView tv_tenDV, tv_giaDV,tv_tenLoaiDV,tv_maDV;
        LinearLayout btn_dkDV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_anhDV = itemView.findViewById(R.id.img_anhDV);
            tv_tenDV = itemView.findViewById(R.id.tv_tenDV);
            tv_tenLoaiDV = itemView.findViewById(R.id.tv_tenLDV);
            tv_maDV = itemView.findViewById(R.id.tv_maDV);
            tv_giaDV = itemView.findViewById(R.id.tv_giaDV);
            btn_dkDV = itemView.findViewById(R.id.btn_dk);
        }
    }
    private void showDialog(int index, int i,String tenkhach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_datlich,null);
        TextView tenKH = view.findViewById(R.id.tv_tenKHDK);
        TextView tenDV = view.findViewById(R.id.tv_tenDVDK);
        TextView ngayDat = view.findViewById(R.id.tv_ngayDK);
        TextView gioDat = view.findViewById(R.id.tv_gioDK);

        tenKH.setText(tenkhach);
        tenDV.setText(list.get(i).getTenDV());

        Calendar calendar= Calendar.getInstance();
        ngayDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(c, new DatePickerDialog.OnDateSetListener() {
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

                        ngayDat.setText(ngay + "/" + thang +"/"+ i);
                        ngayDat.setTextColor(Color.parseColor("#FF9800"));
                    }
                },calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        gioDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(c, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute <10 && minute > -1){
                            gioDat.setText(hourOfDay + " : " +minute);
                        }
                        gioDat.setText(hourOfDay + " : " + minute);
                        gioDat.setTextColor(Color.parseColor("#FF9800"));
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

        builder.setPositiveButton(
                "Đặt",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int madv = list.get(i).getId();
                        String ngay = ngayDat.getText().toString();
                        String gio = gioDat.getText().toString();
                        UserDAO dao = new UserDAO(c);
                        checktt = dao.dangkyDichVu(id, madv, ngay, gio, giadv,0);
                        if (checktt) {
                            Toast.makeText(c, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(c, "Đặt lịch thất bại", Toast.LENGTH_SHORT).show();
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
