package com.example.da_1.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.da_1.DAO.DangKyDichVuDAO;
import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.DatLich;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;


import java.io.DataInput;
import java.util.ArrayList;
import java.util.Calendar;

public class AdapterTop extends RecyclerView.Adapter<AdapterTop.ViewHolder>{
    private Context c;
    private ArrayList<DichVu> list;
    private int id;
    int index,i,giadv;
    SharedPreferences sharedPreferences;
    boolean checktt;

    public AdapterTop(Context c, ArrayList<DichVu> list,int id) {
        this.c = c;
        this.list = list;
        this.id = id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.lists_home_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.Tendv.setText(String.valueOf("Dịch vụ: "+list.get(position).getTenDV()));
    holder.madv.setText(String.valueOf("Mã: "+list.get(position).getId()));
    holder.giadv.setText(String.valueOf("Giá: "+list.get(position).getGiaDV()+" $"));
    Glide.with(c).load(String.valueOf(list.get(position).getAnhDV())).into(holder.hinh);
    holder.btn_datlich.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index =(int) list.get(holder.getAdapterPosition()).getId();
            i = (int) holder.getAdapterPosition();
            giadv = list.get(holder.getAdapterPosition()).getGiaDV();
            sharedPreferences = c.getSharedPreferences("tenkh",MODE_PRIVATE);
            String tenKH = sharedPreferences.getString("hoten","");
            showDialog(index,i,tenKH);
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
    TextView  Tendv,madv, giadv;
    ImageView hinh;
    LinearLayout btn_datlich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Tendv = itemView.findViewById(R.id.TenDV);
            madv = itemView.findViewById(R.id.Madv);
            giadv = itemView.findViewById(R.id.giadv);
            hinh = itemView.findViewById(R.id.image_view);
            btn_datlich = itemView.findViewById(R.id.btn_dkHome);
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
                        if (madv == 0 || ngay.equals("") || gio.equals("") || id == 0) {
                            Toast.makeText(c, "Thất bại. Kiểm tra ngày giờ đăng ký", Toast.LENGTH_SHORT).show();
                        } else {
                            checktt = dao.dangkyDichVu(id, madv, ngay, gio, giadv, 0);
                            Toast.makeText(c, "Đặt lịch thành công", Toast.LENGTH_SHORT).show();
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
