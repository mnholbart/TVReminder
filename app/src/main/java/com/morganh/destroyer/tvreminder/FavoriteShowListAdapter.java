package com.morganh.destroyer.tvreminder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FavoriteShowListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] imgURL;

    public FavoriteShowListAdapter(Activity context, String[] itemname, String[] imgURL) {
        super(context, R.layout.list_item, itemname);

        this.context=context;
        this.itemname=itemname;
        this.imgURL=imgURL;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.favorite_list_item, null,true);

        //TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        //txtTitle.setText(itemname[position]);
        new AsyncLoadImage(imgURL[position], imageView).execute();

        imageView.setTag(R.string.FavoriteButton_Position, position);
        //Button button = (Button) rowView.findViewById(R.id.addToFavorites);
        //button.setTag(R.string.FavoriteButton_Position, position);

        return rowView;

    };
}