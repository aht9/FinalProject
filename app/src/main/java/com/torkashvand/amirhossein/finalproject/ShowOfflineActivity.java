package com.torkashvand.amirhossein.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.torkashvand.amirhossein.finalproject.AccessDataLayer.MovieOfflineDetails;
import com.torkashvand.amirhossein.finalproject.AccessDataLayer.SqlLiteDB;
import com.torkashvand.amirhossein.finalproject.RcMovieSearch.RcMovieSearchAdapter;
import com.torkashvand.amirhossein.finalproject.RcOfflineMovie.RcOfflineMovieAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class ShowOfflineActivity extends AppCompatActivity {

    ArrayList<MovieOfflineDetails> list = new ArrayList<MovieOfflineDetails>();
    ArrayList<String> Title_Sh_Movie,Genre_Sh_Movie,Released_Sh_Movie, Actors_Sh_Movie,Poster_Sh_Movie,imdbID;
    RecyclerView rc_OfflineMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_offline);

        initArray();
        init();

        SqlLiteDB helper = new SqlLiteDB(ShowOfflineActivity.this, "MyMovieTest", null, 1);
        List<MovieOfflineDetails> list = helper.getFinalProjectNames();
        for (int i =0;i<list.size();i++)
        {
            Title_Sh_Movie.add(list.get(i).getTitle());
            Released_Sh_Movie.add(list.get(i).getReleased());
            Genre_Sh_Movie.add(list.get(i).getGenre());
            Actors_Sh_Movie.add(list.get(i).getActors());
            Poster_Sh_Movie.add(list.get(i).getPoster());
            imdbID.add(list.get(i).getImdbID());
        }

        initRecyclerShowMovie();

    }

    private void init() {
        rc_OfflineMovie = findViewById(R.id.rc_OfflineMovie);
    }

    private void initArray() {
        //Title_Sh_Movie,Genre_Sh_Movie,Released_Sh_Movie, Actors_Sh_Movie,Poster_Sh_Movie,imdbID;
        Title_Sh_Movie = new ArrayList<String>();
        Genre_Sh_Movie = new ArrayList<String>();
        Released_Sh_Movie = new ArrayList<String>();
        Actors_Sh_Movie = new ArrayList<String>();
        Poster_Sh_Movie = new ArrayList<String>();
        imdbID = new ArrayList<String>();
    }

    private void initRecyclerShowMovie() {
        LinearLayoutManager manager = new LinearLayoutManager(ShowOfflineActivity.this, RecyclerView.VERTICAL,false);
        rc_OfflineMovie.setLayoutManager(manager);
        RcOfflineMovieAdapter adapter = new RcOfflineMovieAdapter(Title_Sh_Movie,
                Released_Sh_Movie,
                Genre_Sh_Movie,
                Actors_Sh_Movie,
                imdbID,
                Poster_Sh_Movie);
        rc_OfflineMovie.setAdapter(adapter);

    }

    public void ShowDetailsActivity(Context context, String idOMDb)
    {
        Intent intent = new Intent(context, ShowAllDetailsMovies.class);
        intent.putExtra("idOMDb", idOMDb);
        context.startActivity(intent);
    }

}