package com.example.user.masiro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2017-05-28.
 */

public class NAdapter extends BaseAdapter {

    ArrayList<Item> arr = new ArrayList<Item>();
    Context context;

    public NAdapter(ArrayList<Item> arr, Context context) {
        this.arr = arr;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);

        TextView geo = (TextView)convertView.findViewById(R.id.textView);
        TextView point = (TextView)convertView.findViewById(R.id.textView2);

        Item item = arr.get(position);
        geo.setText(item.getGeopoint());
        point.setText(item.getPoint());

        return convertView;
    }

}
