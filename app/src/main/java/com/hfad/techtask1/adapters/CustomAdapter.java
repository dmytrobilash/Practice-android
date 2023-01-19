package com.hfad.techtask1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hfad.techtask1.R;
import com.hfad.techtask1.model.Data;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    public List<Data> dataList;
    public Context context;

    public CustomAdapter(List<Data> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view  = LayoutInflater.from(context).inflate(R.layout.grid_item, null);

        TextView header = view.findViewById(R.id.grid_header_text);
        TextView publishedAt = view.findViewById(R.id.grid_date);
        TextView subText = view.findViewById(R.id.grid_lower_text);
        ImageView imageView = view.findViewById(R.id.grid_image);

        Glide.with(context)
                .load(dataList.get(i)
                        .getImageUrl())
                .into(imageView);
        publishedAt.setText(dataList.get(i).getPublishedAt());
        header.setText(dataList.get(i).getTitle());
        subText.setText(dataList.get(i).getSummary());

        return view;
    }
}
