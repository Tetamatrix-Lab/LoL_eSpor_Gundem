package com.erdem.kurumi.volley.adater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.erdem.kurumi.volley.R;
import com.erdem.kurumi.volley.model.TakımPuan;

import java.util.List;

/**
 * Created by Kurumi on 9.3.2015.
 */
public class LigAdapter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<TakımPuan> movieItems;

    public LigAdapter(Activity activity, List<TakımPuan> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.ligsatir_row, null);
        TextView title = (TextView) convertView.findViewById(R.id.takimadi);//TakimAdi
        TextView galibiyet = (TextView) convertView.findViewById(R.id.galibiyet);//Galibiyet
        TextView malubiyet = (TextView) convertView.findViewById(R.id.malubiyet);//Malubiyet
        TextView sira=(TextView)convertView.findViewById(R.id.sira);//Sira
        // getting movie data for the row
        TakımPuan m = movieItems.get(position);


       // title
        title.setText(String.valueOf(m.getTitle()));
        // galibiyet maglubiyet
        galibiyet.setText(String.valueOf(m.getGalibiyet()));
        malubiyet.setText(String.valueOf(m.getMalubiyet()));
        sira.setText(String.valueOf(m.getSira())+".");

        // genre
       /* String genreStr = "";
        for (String str : m.getPuan()) {
            genreStr += str + ", ";
        }
        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
                genreStr.length() - 2) : genreStr;*/


        return convertView;
    }

}