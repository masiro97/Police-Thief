package com.example.user.masiro;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

/**
 * Created by User on 2017-05-28.
 */

public class NAdapter extends BaseAdapter implements Filterable {

    ArrayList<Person> arr = new ArrayList<Person>();
    ArrayList<Person> filtered = arr;
    Filter listFilter;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Filter getFilter() {
        if (listFilter == null) listFilter = new ListFilter();
        return listFilter;

    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arr;
                results.count = arr.size();
            } else {
                ArrayList<Person> itemList = new ArrayList<Person>();


                for (Person data : arr) {
                    if (data.getID().toUpperCase().contains(constraint.toString()
                            .toUpperCase()))
                        itemList.add(data);
                }
                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered = (ArrayList<Person>) results.values;
            if (results.count >= 0) notifyDataSetChanged();
            else notifyDataSetInvalidated();
        }
    }

}
