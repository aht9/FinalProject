package com.torkashvand.amirhossein.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RcSeriesAdapter extends RecyclerView.Adapter<RcSeriesHolder> {

    ArrayList<String> Title_Sh_Series,Year_Sh_Series,imdbID_Sh_Series,Type_Sh_Series,Poster_Sh_Series;
    MainActivity mainActivity =new MainActivity();


    public RcSeriesAdapter(ArrayList<String> Title_Sh_Series,
                          ArrayList<String> Year_Sh_Series,
                          ArrayList<String> imdbID_Sh_Series,
                          ArrayList<String> Type_Sh_Series,
                          ArrayList<String> Poster_Sh_Series){

        this.Title_Sh_Series=Title_Sh_Series;
        this.Year_Sh_Series=Year_Sh_Series;
        this.imdbID_Sh_Series=imdbID_Sh_Series;
        this.Type_Sh_Series=Type_Sh_Series;
        this.Poster_Sh_Series=Poster_Sh_Series;
    }

    @NonNull
    @Override
    public RcSeriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_layout,parent,false);
        RcSeriesHolder holder = new RcSeriesHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RcSeriesHolder holder, int position) {
        holder.txt_TitleMovie.setText(Title_Sh_Series.get(position));
        holder.txt_TitleMovie.setTag(imdbID_Sh_Series.get(position));
        holder.txt_DesMovie.setText(Year_Sh_Series.get(position));
        Picasso.get().load(Poster_Sh_Series.get(position)).fit().into(holder.img_PosterMovie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = position;
//                notifyDataSetChanged();

                String idName = imdbID_Sh_Series.get(index);
                mainActivity.ShowDetailsActivity(holder.txt_DesMovie.getContext(),idName);
            }
        });
    }

    @Override
    public int getItemCount() {
        int i = 0;
        if(Title_Sh_Series != null)
            i = Title_Sh_Series.size();
        return i;
    }
}
