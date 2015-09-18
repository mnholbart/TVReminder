package com.morganh.destroyer.tvreminder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.JsonObject;
import com.loopj.android.http.*;

import org.apache.http.Header;
import org.json.*;

import java.io.IOException;


public class TVFavorites extends Activity {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private MySQLiteHelper db;
    private Button addToFavoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvfavorites);

        Log.d("onCreate", "Start database");
        db = MySQLiteHelper.getInstance(this);

        addToFavoritesButton = (Button) findViewById(R.id.addToFavorites);
    }

    //Called by pressing the search button, if the show is valid it will display the show
    public void searchShow(View v) throws JSONException {

        EditText editText = (EditText) findViewById(R.id.searchMessage);

        client.get("http://api.tvmaze.com/singlesearch/shows?q=" + editText.getText().toString(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //response is the JsonObject
                setDisplayedShow(response);
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
    void setDisplayedShow(JSONObject showInfo) {

        addToFavoritesButton.setEnabled(true);
        ImageView imageView = (ImageView) findViewById(R.id.showThumbnail);

        try {
            new AsyncLoadImage(showInfo.getJSONObject("image").get("medium").toString(), imageView).execute();

        } catch(JSONException e) {
            Log.d("FAIL", "Image load fail");
            e.printStackTrace();
        }

    }
}

