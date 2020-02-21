package com.example.bbowl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.bataille.R;

public class AdapterGridAdDino extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;

    int [] ligne ;

    public AdapterGridAdDino(Context context, int[] ligne) {
        this.context = context;
        this.ligne= ligne;
    }


    @Override
    public int getCount() {
        return ligne.length;
    }

    @Override
    public Object getItem(int position) {
        return ligne[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View gridViewAdDino = convertView;

        if (convertView==null){

            gridViewAdDino= layoutInflater.inflate(R.layout.layout_jeton, null);

        }
        ImageView icon = (ImageView)gridViewAdDino.findViewById(R.id.imageView);
        icon.setImageResource(ligne[position]);

        return gridViewAdDino;
    }


}
