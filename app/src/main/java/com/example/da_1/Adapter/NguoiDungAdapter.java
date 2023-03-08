package com.example.da_1.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.NguoiDung;
import com.example.da_1.R;

import java.util.ArrayList;
import java.util.Calendar;

public class NguoiDungAdapter extends RecyclerView.Adapter<NguoiDungAdapter.ViewHolder> {
    private ArrayList<NguoiDung> list;
    private Context c;
    UserDAO userDAO;
    int index,i;

    public NguoiDungAdapter(ArrayList<NguoiDung> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
    View view = inflater.inflate(R.layout.item_thanhvien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.tv_matv.setText(String.valueOf(list.get(position).getId()));
                holder.tv_tentv.setText(String.valueOf(list.get(position).getHoten()));
                holder.tv_namsinh.setText(String.valueOf(list.get(position).getNgaysinh()));
                holder.tv_sdt.setText(String.valueOf(list.get(position).getSdt()));
                holder.iv_xoatv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userDAO = new UserDAO(c);
                        boolean check = userDAO.XoaNguoiDung(list.get(holder.getAdapterPosition()).getId());
                        if (check){
                            list.clear();
                            list = userDAO.getAllUser();
                            notifyDataSetChanged();
                            Toast.makeText(c, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(c, "Lỗi!! Hãy thử lại sau", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                holder.iv_suatv.setOnClickListener(new View.OnClickListener() {
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
    TextView tv_matv,tv_tentv,tv_namsinh,tv_sdt;
    AppCompatButton iv_suatv,iv_xoatv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_matv = itemView.findViewById(R.id.tv_maTVItem);
            tv_tentv= itemView.findViewById(R.id.tv_tenTVitem);
            tv_namsinh = itemView.findViewById(R.id.tv_namsinhTVitem);
            tv_sdt = itemView.findViewById(R.id.tv_sdtTVitem);
            iv_suatv = itemView.findViewById(R.id.iv_suaTV);
            iv_xoatv = itemView.findViewById(R.id.iv_xoaTV);
        }
    }
    @SuppressLint("MissingInflatedId")
    private void showDialogUpdate() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(c);
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_updateuser, null);
        EditText ten = view.findViewById(R.id.edt_upTen);
        EditText sdt = view.findViewById(R.id.edt_upSdt);
        EditText ngaysinh = view.findViewById(R.id.edt_upNgaySinh);
        Calendar calendar = Calendar.getInstance();
        ten.setText(""+list.get(i).getHoten());
        sdt.setText(""+list.get(i).getSdt());
        ngaysinh.setText(""+list.get(i).getNgaysinh());

        ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(c, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String ngay = "";
                        String thang = "";
                        if (i2 < 10) {
                            ngay = "0" + i2;
                        } else {
                            ngay = String.valueOf(i2);
                        }

                        if ((i1 + 1) < 10) {
                            thang = "0" + (i1 + 1);
                        } else {
                            thang = String.valueOf((i1 + 1));
                        }

                        ngaysinh.setText(ngay + "/" + thang + "/" + i);
                    }
                }, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        builder.setView(view);

        builder.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenup = ten.getText().toString();
                String sdtup = sdt.getText().toString();
                String ngaysinhup = ngaysinh.getText().toString();
                if (tenup.equals("") || sdtup.equals("") || ngaysinhup.equals("")) {
                    Toast.makeText(c, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences preferences = c.getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    String sdt = preferences.getString("sdt", "");
                    UserDAO userDAO = new UserDAO(c);
                    Boolean check = userDAO.updatein4(sdt, tenup, sdtup, ngaysinhup);
                    if (check) {
                        Toast.makeText(c, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(c, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
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
