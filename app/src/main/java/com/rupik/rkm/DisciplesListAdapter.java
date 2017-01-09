package com.rupik.rkm;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by macmin5 on 09/01/17.
 */

public class DisciplesListAdapter extends BaseAdapter {

    static final String MonkName = "MonkName";
    static final String MonkImageName = "MonkImageName";

    Context context;
    private static LayoutInflater inflater=null;
    ArrayList<HashMap> dataSet;

    public DisciplesListAdapter(Context context, ArrayList<HashMap> dataSet)
    {
        this.context = context;
        this.dataSet = dataSet;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView discipleName;
        ImageView discipleImageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.disciples_cell, null);

        holder.discipleName = (TextView) rowView.findViewById(R.id.discipleNameTV);
        holder.discipleImageView = (ImageView) rowView.findViewById(R.id.discipleImageView);

        HashMap hashmap = dataSet.get(position);

        holder.discipleName.setText((String)hashmap.get(MonkName));
        String PACKAGE_NAME = context.getPackageName();
        final int imgId = context.getResources().getIdentifier(PACKAGE_NAME+":drawable/"+(String)hashmap.get(MonkImageName) , null, null);
        holder.discipleImageView.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),imgId));

        return rowView;
    }
}
