package com.example.da_1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.da_1.R;
import com.smarteist.autoimageslider.SliderViewAdapter;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Hoder> {

    int[] image;
    public SliderAdapter(int[] image){
        this.image = image;
    }
    @Override
    public Hoder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);

        return new Hoder(view);
    }

    @Override
    public void onBindViewHolder(Hoder viewHolder, int position) {
        viewHolder.imageView.setImageResource(image[position]);
    }

    @Override
    public int getCount() {
        return image.length;
    }

    public class Hoder extends SliderViewAdapter.ViewHolder{
        ImageView imageView;

        public Hoder(View view){
            super(view);

            imageView = view.findViewById(R.id.image_view);
        }
    }

}

