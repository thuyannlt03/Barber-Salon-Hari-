package com.example.da_1.Fragment.Home;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da_1.Adapter.AdapterTop;
import com.example.da_1.Adapter.SliderAdapter;
import com.example.da_1.DAO.DAOThongKe;
import com.example.da_1.DAO.DangKyDichVuDAO;
import com.example.da_1.DAO.UserDAO;
import com.example.da_1.Fragment.Fragment_QLThanhVien;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Calendar;

public class fragment_home extends Fragment {
    DAOThongKe daoThongKe;
    ArrayList<DichVu> list;
    RecyclerView recyclerView;
    LinearLayout bt1,bt2,bt3,bt4;
    SliderView sliderView;
    SharedPreferences sharedPreferences;
    int[] image ={R.mipmap.slide4,R.mipmap.slide1,R.mipmap.slide3,R.mipmap.slide2};
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        sliderView = view.findViewById(R.id.image_slider);

        bt1 = view.findViewById(R.id.btn_haircut_home);
        bt2 = view.findViewById(R.id.btn_makeup_home);
        bt3 = view.findViewById(R.id.btn_manicure_home);
        bt4 = view.findViewById(R.id.btn_massage_home);

        recyclerView=view.findViewById(R.id.recyclerViewQTop);

        SliderAdapter sliderAdapter = new SliderAdapter(image);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CattocActivity.class);
                intent.getAction();
                startActivity(intent);
            }
        });
        loadingTop();

        return view;

    }
    private void loadingTop(){
        daoThongKe = new DAOThongKe(getContext());
        list = daoThongKe.getTop();
        sharedPreferences = getContext().getSharedPreferences("thongtin",MODE_PRIVATE);
        int id = sharedPreferences.getInt("id",-1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        AdapterTop adapterTop = new AdapterTop(getContext(),list,id);
        recyclerView.setAdapter(adapterTop);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
