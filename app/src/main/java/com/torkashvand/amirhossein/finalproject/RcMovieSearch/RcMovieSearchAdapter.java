package com.torkashvand.amirhossein.finalproject.RcMovieSearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.torkashvand.amirhossein.finalproject.MainActivity;
import com.torkashvand.amirhossein.finalproject.R;
import com.torkashvand.amirhossein.finalproject.RcMoviesHolder;
import com.torkashvand.amirhossein.finalproject.SearchMovieActivity;

import java.util.ArrayList;

public class RcMovieSearchAdapter extends RecyclerView.Adapter<RcMovieSearchHolder> {

    ArrayList<String> Title_Sh_Movie,Year_Sh_Movie,imdbID_Sh_Movie,Type_Sh_Movie,Poster_Sh_Movie;
    SearchMovieActivity searchMovieActivity = new SearchMovieActivity();

    public RcMovieSearchAdapter(ArrayList<String> Title_Sh_Movie,
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
    public RcMovieSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_search,parent,false);

        RcMovieSearchHolder holder = new RcMovieSearchHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RcMovieSearchHolder holder, int position) {
        holder.txt_Title_Search.setText("Title : "+Title_Sh_Movie.get(position));
        holder.txt_Year_Search.setText("Year : " +Year_Sh_Movie.get(position));
        holder.txt_imdbID_Search.setText("imdbID : " +imdbID_Sh_Movie.get(position));
        holder.txt_Type_Search.setText("Type : "+Type_Sh_Movie.get(position));
        Picasso.get().load(Poster_Sh_Movie.get(position)).fit().into(holder.img_Poster_Search);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;

                String idName = imdbID_Sh_Movie.get(index);
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
