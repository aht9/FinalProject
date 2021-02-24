package com.torkashvand.amirhossein.finalproject.RcOfflineMovie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.torkashvand.amirhossein.finalproject.R;
import com.torkashvand.amirhossein.finalproject.RcMovieSearch.RcMovieSearchHolder;
import com.torkashvand.amirhossein.finalproject.SearchMovieActivity;

import java.util.ArrayList;

public class RcOfflineMovieAdapter extends RecyclerView.Adapter<RcOfflineMovieHolder> {

    ArrayList<String> Title_Sh_Movie,Genre_Sh_Movie,Released_Sh_Movie, Actors_Sh_Movie,Poster_Sh_Movie,imdbID;
    SearchMovieActivity searchMovieActivity = new SearchMovieActivity();

    public RcOfflineMovieAdapter(ArrayList<String> Title_Sh_Movie,
                                ArrayList<String> Released_Sh_Movie,
                                ArrayList<String> Genre_Sh_Movie,
                                ArrayList<String> Actors_Sh_Movie,
                                ArrayList<String> imdbID,
                                ArrayList<String> Poster_Sh_Movie){

        this.Title_Sh_Movie=Title_Sh_Movie;
        this.Genre_Sh_Movie=Genre_Sh_Movie;
        this.Released_Sh_Movie=Released_Sh_Movie;
        this.Actors_Sh_Movie =Actors_Sh_Movie;
        this.Poster_Sh_Movie=Poster_Sh_Movie;
        this.imdbID=imdbID;
    }

    @NonNull
    @Override
    public RcOfflineMovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_search,parent,false);

        RcOfflineMovieHolder holder = new RcOfflineMovieHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RcOfflineMovieHolder holder, int position) {
        holder.txt_Title_Search.setText("Title : "+Title_Sh_Movie.get(position));
        holder.txt_Year_Search.setText("Genre : " +Genre_Sh_Movie.get(position));
        holder.txt_imdbID_Search.setText("Released : " +Released_Sh_Movie.get(position));
        holder.txt_Type_Search.setText("Actors : "+ Actors_Sh_Movie.get(position));
        Picasso.get().load(Poster_Sh_Movie.get(position)).fit().into(holder.img_Poster_Search);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;

                String idName = imdbID.get(index);
                searchMovieActivity.ShowDetailsActivity(holder.txt_Title_Search.getContext(),idName);
            }
        });

    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(Title_Sh_Movie != null)
            i = Title_Sh_Movie.size();
        return i;
    }
}
