package com.example.da_1.Adapter.AdapterNews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.da_1.Model.mdNews.newsTT;
import com.example.da_1.R;

import java.util.ArrayList;

public class Adapter_newsTT extends BaseAdapter {
    private Context c;
    private ArrayList<newsTT> list;

    public Adapter_newsTT(Context c, ArrayList<newsTT> list) {
        this.c = c;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class viewItemNews{
        TextView title;
        TextView time;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewItemNews viewItemNews;
        LayoutInflater inflater = ((Activity)c).getLayoutInflater();
        if(view == null){
            viewItemNews = new viewItemNews();
            view = inflater.inflate(R.layout.item_news,viewGroup,false);
            viewItemNews.title = view.findViewById(R.id.titleNews);
            viewItemNews.time = view.findViewById(R.id.pubdateNews);
            view.setTag(viewItemNews);
        }else{
            viewItemNews = (viewItemNews) view.getTag();
        }
        viewItemNews.title.setText(list.get(i).getTitle());
        viewItemNews.time.setText(list.get(i).getPubdate());
        return view;
    }
}
