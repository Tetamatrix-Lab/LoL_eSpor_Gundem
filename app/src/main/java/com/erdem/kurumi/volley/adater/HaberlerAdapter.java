package com.erdem.kurumi.volley.adater;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.erdem.kurumi.volley.R;
import com.erdem.kurumi.volley.app.AppController;
import com.erdem.kurumi.volley.model.Movie2;

import java.util.List;

public class HaberlerAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie2> movieItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public HaberlerAdapter(Activity activity, List<Movie2> movieItems) {
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
            convertView = inflater.inflate(R.layout.haberler_list, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);//Haber Basligi
        //TextView year = (TextView) convertView.findViewById(R.id.releaseYear);//icerik

        // getting movie data for the row
        Movie2 m = movieItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        title.setText(m.getTitle());

        // release year
       // year.setText(String.valueOf(m.getYear()));

        return convertView;
    }

}