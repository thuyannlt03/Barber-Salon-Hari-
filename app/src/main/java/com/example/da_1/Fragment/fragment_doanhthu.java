package com.example.da_1.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.da_1.DAO.DaoDoanhThu;
import com.example.da_1.Model.DichVu;
import com.example.da_1.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

public class    fragment_doanhthu extends Fragment {
    DaoDoanhThu daoDoanhThu;
    PieChart pieChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_doanh_thu,container,false);
        pieChart = view.findViewById(R.id.piechartview);
        setpChartView();
        return view;
    }
    private void setpChartView(){
        daoDoanhThu = new DaoDoanhThu(getContext());
        ArrayList<DichVu> list = daoDoanhThu.getdoanhthu();
        int size = list.size();
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int e = 0; e < size; e++) {
            entries.add(new PieEntry((float) (list.get(e).getSum()),list.get(e).getTenDV()));
        }
        PieDataSet dataSet = new PieDataSet(entries,"");
        pieChart.setData(new PieData(dataSet));
        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.MATERIAL_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setCenterText("DOANH THU");
        pieChart.setData(data);
        pieChart.highlightValues(null);

        pieChart.invalidate();
    }
}
