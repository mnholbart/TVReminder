package com.morganh.destroyer.tvreminder;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;

public class Show {

    private JsonObject jsonSource;
    private int id;
    private String title;

    public Show() {}

    public Show(JsonObject show) {
        ParseJson(show);
    }

    protected void ParseJson(JsonObject show) {
        jsonSource = show;
        id = show.get("id").getAsInt();
        title = show.get("name").getAsString();

    }

    @Override
    public String toString() {
        return "Show [showid= " + id + ", title=" + title + "]";
    }

    public int getID () { return id; }
    public void setID (int newID) { id = newID; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String newTitle) { title = newTitle; }
}
