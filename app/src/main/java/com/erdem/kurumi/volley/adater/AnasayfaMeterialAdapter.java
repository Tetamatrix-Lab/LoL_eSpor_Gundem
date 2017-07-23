package com.erdem.kurumi.volley.adater;

/**
 * Created by Kurumi on 18.5.2015.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.erdem.kurumi.volley.AnasayfaMeterial;
import com.erdem.kurumi.volley.Ligler_Grid;
import com.erdem.kurumi.volley.R;

/**
 * Created by Kurumi on 18.5.2015.
 */
public class AnasayfaMeterialAdapter extends BaseAdapter {
    String[] result;
    Context context;
    int[] imageId;
    private static LayoutInflater inflater = null;

    public AnasayfaMeterialAdapter(AnasayfaMeterial mainActivity, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        imageId = prgmImages;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    public class Holder {
        TextView tv;
        ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.anasayfa_item, null);
        holder.tv = (TextView) rowView.findViewById(R.id.textViewanasayfa);
        holder.img = (ImageView) rowView.findViewById(R.id.imageViewanasayfa);

        holder.tv.setText(result[position]);
        holder.img.setImageResource(imageId[position]);
    /*
        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });*/

        return rowView;
    }

}