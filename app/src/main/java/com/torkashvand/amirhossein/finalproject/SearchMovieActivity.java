package com.torkashvand.amirhossein.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.torkashvand.amirhossein.finalproject.RcMovieSearch.RcMovieSearchAdapter;
import com.torkashvand.amirhossein.finalproject.ShowDetailMovie.DetailsMovie;
import com.torkashvand.amirhossein.finalproject.TypeApiMovie.OMDbApi;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchMovieActivity extends AppCompatActivity {

    ArrayList<String> Title_Sh_Movie,Year_Sh_Movie,imdbID_Sh_Movie,Type_Sh_Movie,Poster_Sh_Movie;
    String nameMovie;
    RecyclerView rc_SearchMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        Intent intent = getIntent();
        nameMovie = intent.getStringExtra("Title");

        intiArrayList();
        ShowMainMovie();
        init();
    }

    private void init() {
        rc_SearchMovie = findViewById(R.id.rc_SearchMovie);
    }

    private void intiArrayList() {
        //List Movie Show
        Title_Sh_Movie = new ArrayList<>();
        Year_Sh_Movie = new ArrayList<>();
        imdbID_Sh_Movie = new ArrayList<>();
        Type_Sh_Movie = new ArrayList<>();
        Poster_Sh_Movie = new ArrayList<>();
    }


    private void ShowMainMovie() {
        String URLName = "http://omdbapi.com/?s="+nameMovie+"&apikey=137d5bf1&r=json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URLName,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                OMDbApi omDbApi = gson.fromJson(response.toString(),OMDbApi.class);
                try {
                    for(int i =0;i < omDbApi.getSearch().size();i++) {
                        Title_Sh_Movie.add(omDbApi.getSearch().get(i).getTitle());
                        Year_Sh_Movie.add(omDbApi.getSearch().get(i).getYear());
                        imdbID_Sh_Movie.add(omDbApi.getSearch().get(i).getImdbID());
                        Type_Sh_Movie.add(omDbApi.getSearch().get(i).getType());
                        Poster_Sh_Movie.add(omDbApi.getSearch().get(i).getPoster());
                    }
                }catch (Exception ex){}
                initRecyclerShowMovie();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(SearchMovieActivity.this, "The Connection is Failed "+errorResponse+" "+ statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRecyclerShowMovie() {
        LinearLayoutManager manager = new LinearLayoutManager(SearchMovieActivity.this, RecyclerView.VERTICAL,false);
        rc_SearchMovie.setLayoutManager(manager);
        RcMovieSearchAdapter adapter = new RcMovieSearchAdapter(Title_Sh_Movie,
                Year_Sh_Movie,
                imdbID_Sh_Movie,
                Type_Sh_Movie,
                Poster_Sh_Movie);
        rc_SearchMovie.setAdapter(adapter);

    }

    public void ShowDetailsActivity(Context context, String idOMDb)
    {
        Intent intent = new Intent(context, ShowAllDetailsMovies.class);
        intent.putExtra("idOMDb", idOMDb);
        context.startActivity(intent);
    }
}