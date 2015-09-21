package com.morganh.destroyer.tvreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = MySQLiteHelper.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadSearchShows(View v) {
        launchSearchShowsActivity();
    }

    private void launchSearchShowsActivity() {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    public void loadFavorites(View v) {
        launchFavoritesActivity();
    }

    private void launchFavoritesActivity() {
        Intent i = new Intent(this, FavoritesActivity.class);
        startActivity(i);
    }

    public void resetDB(View v) {
        db.clearDatabase();
    }
}

