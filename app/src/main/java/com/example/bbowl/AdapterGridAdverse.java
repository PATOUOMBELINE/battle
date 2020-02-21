package com.example.bbowl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.bataille.R;

public class AdapterGridAdverse extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    int plateauImage [];

    public AdapterGridAdverse(Context context, int[] plateauImage) {
        this.context = context;
        this.plateauImage = plateauImage;
    }


    @Override
    public int getCount() {
        return plateauImage.length;
    }

    @Override
    public Object getItem(int position) {
        return plateauImage[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View gridViewAd = convertView;

        if (convertView==null){
            layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            gridViewAd= layoutInflater.inflate(R.layout.layout_jeton_gris, null);

        }
        ImageView icon = (ImageView)gridViewAd.findViewById(R.id.imageViewGris);
        icon.setImageResource(plateauImage[position]);

        return gridViewAd;
    }


}
