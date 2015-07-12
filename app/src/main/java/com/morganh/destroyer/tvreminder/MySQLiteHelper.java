package com.morganh.destroyer.tvreminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static MySQLiteHelper sInstance;

    //Database and version names
    public static final String DATABASE_NAME = "ShowDB";
    public static final String DATABASE_TABLE = "Shows";
    public static final int DATABASE_VERSION = 1;

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_SHOWID = "showid";

    private static final String[] COLUMNS = {KEY_ID,KEY_TITLE,KEY_SHOWID};

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
        String CREATE_SHOW_TABLE = "CREATE TABLE " + DatabaseHelper.DATABASE_TABLE + " ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT, "+
                "showid INTEGER )";

        // create books table
        db.execSQL(CREATE_SHOW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseHelper.DATABASE_TABLE);

        // create fresh books table
        this.onCreate(db);
    }

    public void addShow(Show show){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, show.getTitle());
        values.put(KEY_SHOWID, show.getShowID());

        db.insert(DATABASE_TABLE,
                null,
                values);

        db.close();
    }


}
