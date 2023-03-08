package com.example.da_1.Fragment.News;

import android.annotation.SuppressLint;
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

import com.example.da_1.Adapter.AdapterNews.Adapter_newsTT;
import com.example.da_1.Model.mdNews.newsTT;
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

public class fragment_TheThao extends Fragment {
    private ArrayList<String>  arrLink;
    private Adapter_newsTT adapter;
    ArrayList<newsTT> list_tt;
    ProgressBar progressBar;
    ListView lv_tt;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thethao,container,false);
        arrLink = new ArrayList<>();
        list_tt = new ArrayList<>();
        lv_tt = view.findViewById(R.id.list_TT);
        progressBar = view.findViewById(R.id.progressbarTT);
        progressBar.setVisibility(View.VISIBLE);
        new ReadRSS().execute("https://vnexpress.net/rss/the-thao.rss");
        adapter = new Adapter_newsTT(getContext(),list_tt);
        lv_tt.setAdapter(adapter);


        lv_tt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                list_tt.add(new newsTT(title,time));
                arrLink.add(parser.getValue(element, "link"));
            }
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);
        }
    }
}
