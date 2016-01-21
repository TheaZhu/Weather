package com.thea.weather.model;

import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.thea.weather.R;
import com.thea.weather.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thea on 2016/1/21 0021.
 */
public class TitleSpinnerAdapter implements SpinnerAdapter {
    private List<City> mItems = new ArrayList<>();

    public TitleSpinnerAdapter() {
        mItems.add(new City("CN00000", "宁波", "", ""));
        mItems.add(new City("CN00000", "杭州", "", ""));
        mItems.add(new City("CN00000", "上海", "", ""));
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("DROPDOWN")) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.title_spinner_item, parent, false);
            convertView.setTag("DROPDOWN");
        }

        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));

        return convertView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null || !convertView.getTag().toString().equals("NON_DROPDOWN")) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.title_spinner_item_actionbar, parent, false);
            convertView.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return mItems.size() == 0;
    }

    private String getTitle(int position) {
        return mItems.get(position).getTown();
    }
}
