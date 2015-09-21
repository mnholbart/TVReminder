package com.morganh.destroyer.tvreminder;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Show {

    private JSONObject jsonSource;
    private int id;
    private String title;

    public Show() {}

    public Show(JSONObject show) {
        ParseJson(show);
    }

    protected void ParseJson(JSONObject show) {
        jsonSource = show;

        try {
            id = show.getInt("id");
            title = show.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Log.d("test", jsonSource.getJSONObject("image").getString("medium"));
        } catch (JSONException e) {
            Log.e("test", "failedwtf");
            e.printStackTrace(); }
    }

    @Override
    public String toString() {
        return "Show [showid= " + id + ", title=" + title + "]";
    }

    public void setJsonSource(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            jsonSource = obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public JSONObject getJsonSource() {
        return jsonSource;
    }

    public int getID () { return id; }
    public void setID (int newID) { id = newID; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) { title = newTitle; }

    public String getImgURL() {
        try {
            return jsonSource.getJSONObject("image").getString("medium");
        } catch (JSONException e) {
            Log.e("JSONException", "Failed to get image URL");
            e.printStackTrace();
            return "";
        }
    }
}
