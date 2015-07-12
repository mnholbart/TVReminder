package com.morganh.destroyer.tvreminder;

import com.MorganHolbart.TVReminderAPI.API;
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

    public int getShowID () {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
