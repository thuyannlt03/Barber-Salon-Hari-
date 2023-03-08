package com.example.da_1.Fragment.News;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.da_1.Adapter.AdapterNews.Adapter_newsGD;
import com.example.da_1.Model.mdNews.newsGD;
import com.example.da_1.R;
import com.example.da_1.WebNews;
import com.example.da_1.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class fragment_giaoduc extends Fragment {
    private ArrayList<String> arrLink;
    private Adapter_newsGD adapter;
    ListView lv_gd;
    ProgressBar progressBar;
    ArrayList<newsGD> list_gd;
    Activity activity = getActivity();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_giaoduc,container,false);
        arrLink = new ArrayList<>();
        list_gd = new ArrayList<>();
        lv_gd = view.findViewById(R.id.list_GD);
        progressBar = view.findViewById(R.id.progressbarGD);

//        progressBar.setVisibility(View.VISIBLE);
        new ReadRSS().execute("https://vnexpress.net/rss/giao-duc.rss");

        adapter = new Adapter_newsGD(getContext(),list_gd);
        lv_gd.setAdapter(adapter);

        lv_gd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), WebNews.class);
                intent.putExtra("linkNews", arrLink.get(i));
                startActivity(intent);
            }
        });
        return  view;
    }
    private class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            String time = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                time = parser.getValue(element, "pubDate");
                list_gd.add(new newsGD(title,time));
                arrLink.add(parser.getValue(element, "link"));
            }
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        }
    }
}
