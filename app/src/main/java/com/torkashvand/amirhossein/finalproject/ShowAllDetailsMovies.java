package com.torkashvand.amirhossein.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;
import com.torkashvand.amirhossein.finalproject.AccessDataLayer.SqlLiteDB;
import com.torkashvand.amirhossein.finalproject.ShowDetailMovie.DetailsMovie;
import com.torkashvand.amirhossein.finalproject.TypeApiMovie.OMDbApi;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ShowAllDetailsMovies extends AppCompatActivity {

    String IdOMDb;
    ImageView img_Detail_PosterMovie;
    TextView txt_Detail_Title,txt_Detail_Genre,txt_Detail_Released,txt_Detail_Actors,txt_Detail_Plot;
    Button btn_Save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_details_movies);

        Intent intent = getIntent();
        IdOMDb = intent.getStringExtra("idOMDb");

        init();
        GetDetailsMovie();
    }

    private void init() {
        img_Detail_PosterMovie = findViewById(R.id.img_Detail_PosterMovie);
        txt_Detail_Title = findViewById(R.id.txt_Detail_Title);
        txt_Detail_Genre = findViewById(R.id.txt_Detail_Genre);
        txt_Detail_Released = findViewById(R.id.txt_Detail_Released);
        txt_Detail_Actors = findViewById(R.id.txt_Detail_Actors);
        txt_Detail_Plot = findViewById(R.id.txt_Detail_Plot);

        btn_Save = findViewById(R.id.btn_Save);

        SqlLiteDB helper = new SqlLiteDB(ShowAllDetailsMovies.this, "MyMovieTest", null, 1);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = txt_Detail_Title.getText().toString();
                String Genre = txt_Detail_Genre.getText().toString();
                String Released = txt_Detail_Released.getText().toString();
                String Actors = txt_Detail_Actors.getText().toString();
                String Plot = txt_Detail_Plot.getText().toString();
                String Poster = img_Detail_PosterMovie.getTag().toString();
                helper.insertIntoFinalProject(Title,Released,Genre,Actors,Plot,IdOMDb,Poster);
                Toast.makeText(ShowAllDetailsMovies.this, "The Movie Save successfully!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void GetDetailsMovie() {
        String URLName = "http://omdbapi.com/?i="+IdOMDb+"&apikey=137d5bf1&r=json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(URLName,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                DetailsMovie detailsMovie = gson.fromJson(response.toString(),DetailsMovie.class);

                Picasso.get().load(detailsMovie.getPoster()).fit().into(img_Detail_PosterMovie);
                txt_Detail_Title.setText(detailsMovie.getTitle());
                txt_Detail_Genre.setText(detailsMovie.getGenre());
                txt_Detail_Released.setText(detailsMovie.getReleased());
                txt_Detail_Actors.setText("Actors :"+detailsMovie.getActors());
                txt_Detail_Plot.setText(detailsMovie.getPlot());
                img_Detail_PosterMovie.setTag(detailsMovie.getPoster());

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(ShowAllDetailsMovies.this, "The Connection is Failed "+errorResponse+" "+ statusCode, Toast.LENGTH_LONG).show();
            }
        });

    }
}