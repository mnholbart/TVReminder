package com.morganh.destroyer.tvreminder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.MorganHolbart.TVReminderAPI.API;
import com.google.gson.JsonObject;

import java.io.IOException;


public class TVFavorites extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvfavorites);
    }

    protected void searchShow() {

        EditText editText = (EditText) findViewById(R.id.searchMessage);
        try { //Show exists
            JsonObject show = API.GetShow(editText.toString());
            Show newShow = new Show(show);

        } catch (IOException e) { //Show doesn't exist

        }

        editText.setText(getString(R.string.SearchButton_message)); //Reset search text
    }
}
