package com.rupik.rkm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by macmin5 on 12/01/17.
 */

public class QuotesListAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater=null;
    ArrayList<Quote> dataSet;

    public QuotesListAdapter(Context context, ArrayList<Quote> dataSet)
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
        TextView authorName;
        TextView quote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.quote_cell_layout, null);

        holder.quote = (TextView) rowView.findViewById(R.id.quotes_QuoteTV);
        holder.authorName = (TextView) rowView.findViewById(R.id.quotes_AuthorTV);

        Quote quote = dataSet.get(position);

        holder.quote.setText(quote.getQuote());
        holder.authorName.setText(quote.getAuthor());

        return rowView;
    }
}
