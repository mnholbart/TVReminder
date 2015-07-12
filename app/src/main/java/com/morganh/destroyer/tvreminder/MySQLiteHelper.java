package com.morganh.destroyer.tvreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static MySQLiteHelper sInstance;

    //Database and version names
    public static final String DATABASE_NAME = "ShowDB";
    public static final String TABLE_SHOWS_NAME = "Shows";
    public static final int DATABASE_VERSION = 1;

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE};

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized MySQLiteHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new MySQLiteHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_SHOW_TABLE = "CREATE TABLE " + TABLE_SHOWS_NAME + " ( " +
                "id INTEGER PRIMARY KEY, " +
                "title TEXT )";

        // create books table
        db.execSQL(CREATE_SHOW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOWS_NAME);

        // create fresh books table
        this.onCreate(db);
    }

    public void addShow(Show show){
        int n = updateShow(show); //Attempt to update existing row

        if (n > 0) //If we updated a row, don't add a new one
            return;

        Log.d("addShow", show.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, show.getTitle());
        values.put(KEY_ID, show.getID());

        db.insert(TABLE_SHOWS_NAME,
                null,
                values);

        db.close();
    }

    public Show getShow(int showID){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_SHOWS_NAME, // a. table
                        COLUMNS, // b. column names
                        " showid = ?", // c. selections
                        new String[] { String.valueOf(showID) }, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        //if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
        else return null;

        Show show = new Show();
        show.setTitle(cursor.getString(1));
        show.setID(cursor.getInt(2));

        Log.d("getShow(" + showID + ")", show.toString());

        return show;
    }

    public List<Show> getAllShows() {
        List<Show> shows = new LinkedList<Show>();

        String query = "SELECT * FROM " + TABLE_SHOWS_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Show show = null;
        if (cursor.moveToFirst()) {
            do {
                show = new Show();
                show.setTitle((cursor.getString(1)));
                show.setID(cursor.getInt(2));

                shows.add(show);
            } while (cursor.moveToNext());
        }

        Log.d("getAllShows()", shows.toString());

        return shows;
    }

    private int updateShow(Show show) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", show.getID());
        values.put("title", show.getTitle());

        int nRows = db.update(TABLE_SHOWS_NAME,
                values,
                KEY_ID+ " = ?",
                new String[] { String.valueOf(show.getID()) });
        db.close();

        if (nRows > 0) {
            Log.d("updateShow", show.toString());
        }
        return nRows;
    }

    public void deleteShow(Show show) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SHOWS_NAME,
                KEY_ID+ " = ?",
                new String[] { String.valueOf(show.getID()) });
        db.close();

        Log.d("deleteShow", show.toString());
    }

}
