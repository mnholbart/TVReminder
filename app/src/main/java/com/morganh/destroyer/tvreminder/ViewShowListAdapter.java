package com.morganh.destroyer.tvreminder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewShowListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] dataTag;
    private final String[] data;

    public ViewShowListAdapter(Activity context, String[] dataTag, String[] data) {
        super(context, R.layout.view_show_list_item, dataTag);

        this.context=context;
        this.dataTag=dataTag;
        this.data=data;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.view_show_list_item, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.tag);
        TextView txtInfo = (TextView) rowView.findViewById(R.id.data);
        txtTitle.setText(dataTag[position]);
        txtInfo.setText(data[position]);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        return rowView;

    };
}