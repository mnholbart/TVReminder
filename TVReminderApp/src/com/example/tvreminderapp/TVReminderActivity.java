package com.example.tvreminderapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TVReminderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tvreminder);
	}
	

	
	public void loadFavorites(View v) {
		launchFavoriteTitleView();
	}
	
	public void launchFavoriteTitleView() {
		Intent i = new Intent(TVReminderActivity.this, FavoriteTVActivity.class);
		startActivity(i);
	}
}
