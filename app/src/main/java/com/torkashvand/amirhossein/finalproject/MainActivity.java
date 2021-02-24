package com.torkashvand.amirhossein.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.torkashvand.amirhossein.finalproject.TypeApiMovie.OMDbApi;
import com.torkashvand.amirhossein.finalproject.TypeApiSeries.SeriesApi;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    RecyclerView rc_ShowMovie,rc_ShowSeries;
    Button btn_Search;
    EditText txt_Search;

    ArrayList<String> Title_Sh_Movie,Year_Sh_Movie,imdbID_Sh_Movie,Type_Sh_Movie,Poster_Sh_Movie;
    ArrayList<String> Title_Sh_Series,Year_Sh_Series,imdbID_Sh_Series,Type_Sh_Series,Poster_Sh_Series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiArrayList();
        ShowMainMovie();
        ShowMainSeries();
        init();


    }

    private void intiArrayList() {

        //List Movie Show
        Title_Sh_Movie = new ArrayList<>();
        Year_Sh_Movie = new ArrayList<>();
        imdbID_Sh_Movie = new ArrayList<>();
        Type_Sh_Movie = new ArrayList<>();
        Poster_Sh_Movie = new ArrayList<>();
        //List Series Show
        Title_Sh_Series = new ArrayList<>();
        Year_Sh_Series = new ArrayList<>();
        imdbID_Sh_Series = new ArrayList<>();
        Type_Sh_Series = new ArrayList<>();
        Poster_Sh_Series = new ArrayList<>();
    }

    private void ShowMainMovie() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://omdbapi.com/?s=2020&type=movie&apikey=137d5bf1&r=json",new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                OMDbApi omDbApi = gson.fromJson(response.toString(),OMDbApi.class);
                for(int i =0;i < omDbApi.getSearch().size();i++){
                    Title_Sh_Movie.add(omDbApi.getSearch().get(i).getTitle());
                    Year_Sh_Movie.add(omDbApi.getSearch().get(i).getYear());
                    imdbID_Sh_Movie.add(omDbApi.getSearch().get(i).getImdbID());
                    Type_Sh_Movie.add(omDbApi.getSearch().get(i).getType());
                    Poster_Sh_Movie.add(omDbApi.getSearch().get(i).getPoster());
                }
                initRecyclerShowMovie();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(MainActivity.this, "The Connection is Failed "+errorResponse+" "+ statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void ShowMainSeries() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://omdbapi.com/?s=2020&type=series&apikey=137d5bf1&r=json",new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                SeriesApi seriesApi = gson.fromJson(response.toString(),SeriesApi.class);
                for(int i =0;i < seriesApi.getSearch().size();i++){
                    Title_Sh_Series.add(seriesApi.getSearch().get(i).getTitle());
                    Year_Sh_Series.add(seriesApi.getSearch().get(i).getYear());
                    imdbID_Sh_Series.add(seriesApi.getSearch().get(i).getImdbID());
                    Type_Sh_Series.add(seriesApi.getSearch().get(i).getType());
                    Poster_Sh_Series.add(seriesApi.getSearch().get(i).getPoster());
                }
                initRecyclerShowSeries();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(MainActivity.this, "The Connection is Failed "+errorResponse+" "+ statusCode, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.offlinemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.btn_OfflineMode:
                startActivity(new Intent(MainActivity.this,ShowOfflineActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void init() {
        rc_ShowMovie = findViewById(R.id.rc_ShowMovie);
        rc_ShowSeries = findViewById(R.id.rc_ShowSeries);

        txt_Search = findViewById(R.id.txt_Search);

        btn_Search = findViewById(R.id.btn_Search);
        btn_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameMovie = txt_Search.getText().toString();
                Intent intent = new Intent(v.getContext(),SearchMovieActivity.class);
                intent.putExtra("Title", nameMovie);
                v.getContext().startActivity(intent);
            }
        });
    }

    private void initRecyclerShowMovie() {
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL,false);
        rc_ShowMovie.setLayoutManager(manager);
        RcMovieAdapter adapter = new RcMovieAdapter(Title_Sh_Movie,
                                                    Year_Sh_Movie,
                                                    imdbID_Sh_Movie,
                                                    Type_Sh_Movie,
                                                    Poster_Sh_Movie);
        rc_ShowMovie.setAdapter(adapter);

    }

    private void initRecyclerShowSeries() {
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL,false);
        rc_ShowSeries.setLayoutManager(manager);
        RcSeriesAdapter adapter = new RcSeriesAdapter(Title_Sh_Series,
                Year_Sh_Series,
                imdbID_Sh_Series,
                Type_Sh_Series,
                Poster_Sh_Series);
        rc_ShowSeries.setAdapter(adapter);
    }

    public void ShowDetailsActivity(Context context,String idOMDb)
    {
        Intent intent = new Intent(context, ShowAllDetailsMovies.class);
        intent.putExtra("idOMDb", idOMDb);
        context.startActivity(intent);
    }

}