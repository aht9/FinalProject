package com.torkashvand.amirhossein.finalproject.AccessDataLayer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SqlLiteDB extends SQLiteOpenHelper {

    String TABLE_NAME = "FinalProject";

    public SqlLiteDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "(" +
                "_id integer primary key autoincrement," +
                "Title text," +
                "Released text," +
                "Genre text," +
                "Actors text," +
                "imdbID text," +
                "Poster text," +
                "Plot text" +
                ")";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIntoFinalProject(String Title,String Released,String Genre,String Actors,String Plot,String imdbID,String Poster) {
        String INSERT_INTO_FinalProject_QUERY = "INSERT INTO " + TABLE_NAME
                + "(Title,Released,Genre,Actors,imdbID,Poster,Plot) VALUES(" + "'" + Title + "','"
                + Released + "','"
                + Genre + "','"
                + Actors + "','"
                + imdbID + "','"
                + Poster + "','"
                + Plot
                + "')";
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL(INSERT_INTO_FinalProject_QUERY);
        } catch (Exception ex) {}
            db.close();
        }


    public List<MovieOfflineDetails> getFinalProjectNames() {
        ArrayList<MovieOfflineDetails> list = new ArrayList<MovieOfflineDetails>();

        String SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_NAME;
        ArrayList<String> names = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL_QUERY, null);
        while (cursor.moveToNext()) {

            //String title,String released,String genre,String actors,String imdbID,String plot
            String title = cursor.getString(1);
            String released = cursor.getString(2);
            String genre = cursor.getString(3);
            String actors = cursor.getString(4);
            String imdbID = cursor.getString(5);
            String poster = cursor.getString(6);
            String plot = cursor.getString(7);
            list.add(new MovieOfflineDetails(title,released,genre,actors,imdbID,poster,plot));
        }
        db.close();
        return list;
    }
}
