package com.example.da_1;

import static com.github.dhaval2404.imagepicker.ImagePicker.*;

import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Model.NguoiDung;
import com.example.da_1.Service.CheckDK;
import com.example.da_1.Service.CheckLogin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Singupuser extends AppCompatActivity {
    IntentFilter intentFilter;
    CircleImageView profile;
    FloatingActionButton changeProfile;
    EditText edt_hoten,edt_sdt,edt_mk,edt_ngaysinh;
    Button btn_sign;
    UserDAO dao;
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ignupuser);

        profile=findViewById(R.id.profile_image);
        changeProfile=findViewById(R.id.changeProfile);
        edt_hoten = findViewById(R.id.sign_hoten);
        edt_ngaysinh = this.findViewById(R.id.sign_ngaysinh);
        edt_sdt = this.findViewById(R.id.sign_sdt);
        edt_mk = findViewById(R.id.sign_password);
        btn_sign = this.findViewById(R.id.btn_signup);
        Calendar calendar= Calendar.getInstance();

        edt_ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Singupuser.this, new DatePickerDialog.OnDateSetListener() {
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

                        edt_ngaysinh.setText(ngay + "/" + thang +"/"+ i);
                    }
                },calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        dao = new UserDAO(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction("KtraSignup");

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edt_hoten.getText().toString();
                String ngaysinh = edt_ngaysinh.getText().toString();
                String sdt = edt_sdt.getText().toString();
                String matkhau = edt_mk.getText().toString();

                String mausdt = "0\\d{9}";
                Boolean checksdt = sdt.matches(mausdt);

                if(ten.equals("")||ngaysinh.equals("") ||sdt.equals("")||matkhau.equals("")){
                    Toast.makeText(Singupuser.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }else {
                    if(checksdt ==  false){
                        Toast.makeText(Singupuser.this, "Số Điện Thoại Không Đúng Định Dạng.", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(Singupuser.this, CheckDK.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("ten",ten);
                        bundle.putString("ngaysinh",ngaysinh);
                        bundle.putString("sdt",sdt);
                        bundle.putString("matkhau",matkhau);
                        intent.putExtras(bundle);
                        startService(intent);
                    }
                }
            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Builder(Singupuser.this)
                        .crop()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start(20);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10){
        }else {
            Uri uri = data.getData();
            profile.setImageURI(uri);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myBroadcastReceiver,intentFilter);
    }
    public BroadcastReceiver myBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "KtraSignup":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    if (check){
                        Toast.makeText(context, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Singupuser.this,LoginActivity.class));
                    }else{
                        Toast.makeText(context, "Đăng Ký Không Thành Công", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
