package com.morganh.destroyer.tvreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.loopj.android.http.*;

import org.apache.http.Header;
import org.json.*;
import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;


public class SearchActivity extends Activity {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private MySQLiteHelper db;
    private Button addToFavoritesButton;
    private TwoWayView list;
    private ArrayList<JSONObject> DisplayedShows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Log.d("onCreate", "Start database");
        db = MySQLiteHelper.getInstance(this);

        TwoWayView listView = (TwoWayView) findViewById(R.id.horizontalScroll); //Couldn't find XML for item margin so do it here
        listView.setItemMargin(20);
    }

    //Called by pressing the search button, if the show is valid it will display the show
    public void searchShow(View v) throws JSONException {

        EditText editText = (EditText) findViewById(R.id.searchMessage);

        client.get("http://api.tvmaze.com/search/shows?q=" + editText.getText().toString(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //response is the JsonObject
                Log.d("Success", "OBJECT");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray shows) {
                Log.d("Success", "ARRAY - COUNT: " + shows.length());
                try {
                    setDisplayedShows(shows);
                } catch (JSONException e) {
                    Log.e("JSONException", "Failed to display shows");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("Fail", Integer.toString(statusCode));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String str, Throwable throwable) {
                Log.d("Fail", Integer.toString(statusCode));
            }

        });
    }

    //Initialize for show to be displayed
    void setDisplayedShows(JSONArray showInfo) throws JSONException {

        String[] name = new String[showInfo.length()];
        String[] imgURL = new String [showInfo.length()];
        DisplayedShows.clear();

        for (int i=0; i<showInfo.length(); i++) {
            JSONObject obj = showInfo.getJSONObject(i).getJSONObject("show");
            Log.d("test", obj.toString());
            DisplayedShows.add(obj);
            //Log.d("Show", obj.getJSONObject("show").get("name").toString());
            name[i] = obj.getString("name");
            imgURL[i] = obj.getJSONObject("image").getString("medium");
        }

        SearchShowListAdapter adapter=new SearchShowListAdapter(this, name, imgURL);
        list=(TwoWayView)findViewById(R.id.horizontalScroll);
        list.setAdapter(adapter);
    }

    public void favoriteShow(View v) throws JSONException {
        int i = (int)v.getTag(R.string.SearchButton_Position); //Get index of button pressed
        JSONObject showSelected = DisplayedShows.get(i);


        //if (!db.contains(showSelected.getInt("id")))
            db.addShow(new Show(showSelected));
    }

    public void viewShow(View v) {

        int i = (int)v.getTag(R.string.SearchButton_Position);
        JSONObject show = DisplayedShows.get(i);

        Bundle b = new Bundle();
        b.putString("JSON", show.toString());
        Intent intent = new Intent(this, ViewShowActivity.class);
        intent.putExtras(b);
        startActivity(intent);
    }
}

