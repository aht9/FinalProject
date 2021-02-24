package com.torkashvand.amirhossein.finalproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RcMovieAdapter extends RecyclerView.Adapter<RcMoviesHolder> {

    ArrayList<String> Title_Sh_Movie,Year_Sh_Movie,imdbID_Sh_Movie,Type_Sh_Movie,Poster_Sh_Movie;
    MainActivity mainActivity =new MainActivity();


    public RcMovieAdapter(ArrayList<String> Title_Sh_Movie,
                          ArrayList<String> Year_Sh_Movie,
                          ArrayList<String> imdbID_Sh_Movie,
                          ArrayList<String> Type_Sh_Movie,
                          ArrayList<String> Poster_Sh_Movie){

        this.Title_Sh_Movie=Title_Sh_Movie;
        this.Year_Sh_Movie=Year_Sh_Movie;
        this.imdbID_Sh_Movie=imdbID_Sh_Movie;
        this.Type_Sh_Movie=Type_Sh_Movie;
        this.Poster_Sh_Movie=Poster_Sh_Movie;
    }




    @NonNull
    @Override
    public RcMoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_layout,parent,false);

        RcMoviesHolder holder = new RcMoviesHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RcMoviesHolder holder, int position) {
        holder.txt_TitleMovie.setText(Title_Sh_Movie.get(position));
        holder.txt_TitleMovie.setTag(imdbID_Sh_Movie.get(position));
        holder.txt_DesMovie.setText(Year_Sh_Movie.get(position));
        Picasso.get().load(Poster_Sh_Movie.get(position)).fit().into(holder.img_PosterMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;
//                notifyDataSetChanged();

                String idName = imdbID_Sh_Movie.get(index);
                mainActivity.ShowDetailsActivity(holder.txt_DesMovie.getContext(),idName);
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
