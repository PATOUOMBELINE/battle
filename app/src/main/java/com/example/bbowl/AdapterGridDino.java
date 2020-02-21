package com.example.bbowl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.bataille.R;

public class AdapterGridDino extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;

    int [] colonne ;

    public AdapterGridDino(Context context, int[] colonne) {
        this.context = context;
        this.colonne= colonne;
    }


    @Override
    public int getCount() {
        return colonne.length;
    }

    @Override
    public Object getItem(int position) {
        return colonne[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View gridViewDino = convertView;

        if (convertView==null){

            gridViewDino= layoutInflater.inflate(R.layout.layout_jeton, null);

        }
        ImageView icon = (ImageView)gridViewDino.findViewById(R.id.imageView);
        icon.setImageResource(colonne[position]);

        return gridViewDino;
    }


}
