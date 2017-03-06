package com.alexbagg.sampleapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alexbagg.sampleapplication.R;
import com.alexbagg.sampleapplication.models.LegendModel;

import java.util.ArrayList;

/**
 * Created by Alex Bagg on 3/5/17.
 */

public class LegendListAdapter extends BaseAdapter {

    /*//////////////////////////////////////////////////////////
    // MEMBERS
    *///////////////////////////////////////////////////////////
    private ArrayList<LegendModel> mLegendModelList;
    private LayoutInflater mInflater;


    /*//////////////////////////////////////////////////////////
    // CONSTRUCTOR
    *///////////////////////////////////////////////////////////
    public LegendListAdapter(Context context, ArrayList<LegendModel> legendModelList) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLegendModelList = legendModelList;
    }


    /*//////////////////////////////////////////////////////////
    // BASE CLASS OVERRIDES
    *///////////////////////////////////////////////////////////
    @Override
    public int getCount(){
        return mLegendModelList.size();
    }
    @Override
    public Object getItem(int location) {
        return mLegendModelList.get(location);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get model for position
        final ViewHolder viewHolder;
        LegendModel legend = mLegendModelList.get(position);

        // If view is first time drawn, create new viewholder for memory reference
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.row_legend, null, false);

            viewHolder.txtLegendName = (TextView)convertView.findViewById(R.id.txtLegendName);
            viewHolder.txtLegendGame = (TextView)convertView.findViewById(R.id.txtLegendGame);

            // Store viewholder in view's Tag for quick memory recycling
            convertView.setTag(viewHolder);

        } else {
            // Else get existing viewholder from tag
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.txtLegendName.setText(legend.getName());
        viewHolder.txtLegendGame.setText(legend.getGame());

        return convertView;
    }


    /*//////////////////////////////////////////////////////////
    // SCOPED CLASSES
    *///////////////////////////////////////////////////////////
    private class ViewHolder {
        private TextView txtLegendName;
        private TextView txtLegendGame;
    }

}
