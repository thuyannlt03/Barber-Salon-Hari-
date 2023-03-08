package com.example.da_1.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.da_1.DAO.UserDAO;
import com.example.da_1.LoginActivity;
import com.example.da_1.R;

import java.util.Calendar;


public class Fragment_Account extends Fragment {
    LinearLayout updatein4, doipass,  logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        updatein4 = view.findViewById(R.id.updatein4);
        doipass = view.findViewById(R.id.doipass);
        logout = view.findViewById(R.id.logout);

        updatein4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate();
            }
        });

        doipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogDoiMatKhau();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        return view;
    }


    private void showDialogDoiMatKhau() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("Đổi Mật Khẩu")
                .setNegativeButton("Đổi mật khẩu", null)
                .setPositiveButton("Hủy", null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        EditText password = view.findViewById(R.id.password);
        EditText Password1 = view.findViewById(R.id.Password1);
        EditText Password2 = view.findViewById(R.id.Password2);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = password.getText().toString();
                String newPass = Password1.getText().toString();
                String reNewPass = Password2.getText().toString();
                if (oldPass.equals("") || newPass.equals("") || reNewPass.equals("")) {
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (newPass.equals(reNewPass)) {
                        SharedPreferences preferences = getContext().getSharedPreferences("thongtin", MODE_PRIVATE);
                        String id = preferences.getString("id", "");
                        UserDAO userDAO = new UserDAO(getContext());
                        int check = userDAO.doimatkhau(String.valueOf(id), oldPass, newPass);
                        if (check == 1) {
                            Toast.makeText(getContext(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else if (check == 0) {
                            Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Nhập mật khẩu không trùng nhau", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void showDialogUpdate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_updateuser, null);
        EditText ten = view.findViewById(R.id.edt_upTen);
        EditText sdt = view.findViewById(R.id.edt_upSdt);
        EditText ngaysinh = view.findViewById(R.id.edt_upNgaySinh);
        Calendar calendar = Calendar.getInstance();

        ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences preferences = getContext().getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    String sdt = preferences.getString("sdt", "");
                    UserDAO userDAO = new UserDAO(getContext());
                    Boolean check = userDAO.updatein4(sdt, tenup, sdtup, ngaysinhup);
                    if (check) {
                        Toast.makeText(getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
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
