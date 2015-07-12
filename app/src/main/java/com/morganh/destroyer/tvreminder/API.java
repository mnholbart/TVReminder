package com.morganh.destroyer.tvreminder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class API {

    //Get Json data for a show searched by name
    public static JsonObject GetShow(String name) throws IOException {
        String sURL = "http://api.tvmaze.com/singlesearch/shows?q=" + name; //just a string
        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //may be an array, may be an object.

        request.disconnect();
        return rootobj;
    }

    //Get Json data for a show searched by ID
    public static JsonObject GetShow(int ID) throws IOException {
        String sURL = "http://api.tvmaze.com/shows/" + ID;
        // Connect to the URL using java's native library
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //convert the input stream to a json element
        JsonObject rootobj = root.getAsJsonObject(); //may be an array, may be an object.

        request.disconnect();
        return rootobj;
    }

    //Get Json data for next episode to be released for a showID
    public static JsonObject GetNextEpisode(int showID) throws IOException {
        String sURL = "http://api.tvmaze.com/shows/" + showID + "?embed=nextepisode";
        URL url = new URL(sURL);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
        JsonObject rootobj = root.getAsJsonObject();
        rootobj = rootobj.getAsJsonObject("_embedded").getAsJsonObject("nextepisode"); //Get the data only for next episode

        request.disconnect();
        return rootobj;
    }
}
