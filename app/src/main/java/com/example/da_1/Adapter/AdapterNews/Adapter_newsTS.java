package com.example.da_1.Adapter.AdapterNews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.da_1.Model.mdNews.newsTS;
import com.example.da_1.R;

import java.util.ArrayList;

public class Adapter_newsTS extends BaseAdapter {
    private Context c;
    private ArrayList<newsTS> listTC;

    public Adapter_newsTS(Context c, ArrayList<newsTS> list) {
        this.c = c;
        this.listTC = list;
    }


    @Override
    public int getCount() {
        return listTC.size();
    }

    @Override
    public Object getItem(int i) {
        return listTC.get(i);
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
        viewItemNews.title.setText(listTC.get(i).getTitle());
        viewItemNews.time.setText(listTC.get(i).getPubdate());
        return view;
    }
}
