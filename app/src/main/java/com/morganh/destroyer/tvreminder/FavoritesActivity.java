package com.morganh.destroyer.tvreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private MySQLiteHelper db;
    private TwoWayView list;
    private List<Show> displayedShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        db = MySQLiteHelper.getInstance(this);

        TwoWayView listView = (TwoWayView) findViewById(R.id.horizontalScroll); //Couldn't find XML for item margin so do it here
        listView.setItemMargin(20);

        populateFavorites();
    }

    void populateFavorites() {

        List<Show> shows = db.getAllShows();
        displayedShows = shows;

        String[] name = new String[shows.size()];
        String[] imgURL = new String [shows.size()];

        for (int i=0; i<shows.size(); i++) {
            name[i] = shows.get(i).getTitle();
            imgURL[i] = shows.get(i).getImgURL();
        }

        FavoriteShowListAdapter adapter=new FavoriteShowListAdapter(this, name, imgURL);
        list=(TwoWayView)findViewById(R.id.horizontalScroll);
        list.setAdapter(adapter);
    }

    public void viewShow(View v) {

        int i = (int)v.getTag(R.string.FavoriteButton_Position);
        JSONObject show = displayedShows.get(i).getJsonSource();

        Bundle b = new Bundle();
        b.putString("JSON", show.toString());
        Intent intent = new Intent(this, ViewShowActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}
