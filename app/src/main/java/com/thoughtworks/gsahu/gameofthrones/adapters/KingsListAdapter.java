package com.thoughtworks.gsahu.gameofthrones.adapters;

/**
 * Created by gasahu on 08-Jan-17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thoughtworks.gsahu.gameofthrones.R;
import com.thoughtworks.gsahu.gameofthrones.beans.King;

import java.util.ArrayList;

public class KingsListAdapter extends BaseAdapter {
    private ArrayList<King> kingsList;
    private static LayoutInflater inflater = null;

    public KingsListAdapter(Context context, ArrayList<King> kingsList) {
        this.kingsList = kingsList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        View rowView;
        King king = kingsList.get(position);

        if(convertView == null) {
            holder = new Holder();
            rowView = inflater.inflate(R.layout.item_king, null);

            holder.kingName = (TextView) rowView.findViewById(R.id.kingName);
            holder.rating = (TextView) rowView.findViewById(R.id.rating);

            rowView.setTag(holder);
        } else {
            rowView = convertView;
            holder = (Holder) rowView.getTag();
        }

        holder.kingName.setText(king.getKingName());
        holder.rating.setText(String.valueOf(king.getRating()));

        return rowView;
    }

    @Override
    public int getCount() {
        return kingsList.size();
    }

    @Override
    public Object getItem(int position) {
        return kingsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class Holder {
        TextView kingName;
        TextView rating;
    }
}