package com.morganh.destroyer.tvreminder;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ViewShowActivity extends AppCompatActivity  {

    private TwoWayView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_show
        );

        Bundle b = getIntent().getExtras();
        String jsonString = b.getString("JSON");
        JSONObject jsonObject;
        try {
            JSONObject obj = new JSONObject(jsonString);
            jsonObject = obj;
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        List<String> InfoType = new ArrayList<String>();
        List<String> Data = new ArrayList<String>();

        try {
            InfoType.add("ID:");
            Data.add(jsonObject.getString("id"));

            InfoType.add("Name:");
            Data.add(jsonObject.getString("name"));

            InfoType.add("Summary:");
            Data.add(jsonObject.getString("summary"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] infoTypeArray = new String[InfoType.size()];
        String[] dataArray = new String[Data.size()];
        infoTypeArray = InfoType.toArray(infoTypeArray);
        dataArray = Data.toArray(dataArray);

        ViewShowListAdapter adapter=new ViewShowListAdapter(this, infoTypeArray, dataArray);
        list = (TwoWayView) findViewById(R.id.verticalScroll);
        list.setAdapter(adapter);
    }
}
