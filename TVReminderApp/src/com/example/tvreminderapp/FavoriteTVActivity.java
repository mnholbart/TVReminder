package com.example.tvreminderapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.MorganHolbart.TVReminderAPI.API;
import com.google.gson.JsonObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FavoriteTVActivity extends Activity {

	private ArrayList<Integer> FavoriteShows = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_tv);
		
		loadFavoriteShows();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		saveItems();
	}
	
	private void loadFavoriteShows() {
		readItems();
	}
	
	@SuppressWarnings("unused")
	private void addShow() throws IOException {
		FavoriteShows.add(API.GetShow("How I Met Your Mother").get("id").getAsInt());
		FavoriteShows.add(API.GetShow("Suits").get("id").getAsInt());
	}
	
	private void readItems() {
		File fileDir = getFilesDir();
		File file = new File(fileDir, "FavoriteShowsID.txt");
		try {
			List<String> ids = FileUtils.readLines(file);
			for (int i = 0; i<ids.size(); i++) {
				FavoriteShows.add(Integer.parseInt(ids.get(i)));
			}
		} catch(IOException e) {
			//default case
			e.printStackTrace();
		}
	}
	
	private void saveItems() {
		File fileDir = getFilesDir();
		File file = new File(fileDir, "FavoriteShowsID.txt");		
		try {
			FileUtils.writeLines(file, FavoriteShows);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
