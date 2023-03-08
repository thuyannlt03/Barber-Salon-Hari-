package com.example.da_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.da_1.DataBase.DbHelper;
import com.example.da_1.Service.CheckLogin;

public class LoginActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    EditText username;
    EditText edt_pass;
    ToggleButton btn_showhidden;
    Button login;
    CheckBox checkAcc;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    Boolean saveLogin;
    TextView forgotpass;
    DbHelper dbhelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgotpass =this.findViewById(R.id.forgotpass);
        username = findViewById(R.id.edt_username);
        btn_showhidden = findViewById(R.id.btn_ShowHiddenPass);
        edt_pass = findViewById(R.id.edt_pass);
        login = findViewById(R.id.btn_login);
        checkAcc = this.findViewById(R.id.cb_renameber);

        btn_showhidden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    edt_pass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    edt_pass.setInputType(129);
                }
            }
        });


        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            username.setText(loginPreferences.getString("username", ""));
            edt_pass.setText(loginPreferences.getString("password", ""));
            checkAcc.setChecked(true);
        }

        checkAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(username.getWindowToken(), 0);

                String saveUser = username.getText().toString();
                String savePass = edt_pass.getText().toString();

                if (checkAcc.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", saveUser);
                    loginPrefsEditor.putString("password", savePass);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }
            }
        });

        intentFilter = new IntentFilter();
        intentFilter.addAction("KtraDangNhap");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = edt_pass.getText().toString();

                if(checkAcc.isChecked() == true){
                    username.setText(user);
                    edt_pass.setText(pass);
                }else{
                    username.setText("");
                    edt_pass.setText("");
                }

                if(user.equals("")||pass.equals("") ){
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sharedPreferences = getSharedPreferences("thongtin",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("sdt",user);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, CheckLogin.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("user",user);
                    bundle.putString("pass",pass);
                    intent.putExtras(bundle);
                    startService(intent);
                }
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,Singupuser.class);
                startActivity(intent);
            }
        });

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
                case "KtraDangNhap":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    if (check){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        Toast.makeText(context, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Tài Khoản Hoặc Mật Khẩu Không Chính Xác", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}