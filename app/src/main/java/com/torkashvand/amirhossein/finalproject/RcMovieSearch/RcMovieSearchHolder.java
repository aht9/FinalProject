package com.torkashvand.amirhossein.finalproject.RcMovieSearch;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.torkashvand.amirhossein.finalproject.R;

public class RcMovieSearchHolder extends RecyclerView.ViewHolder {

    ImageView img_Poster_Search;
    TextView txt_Title_Search,txt_Year_Search,txt_imdbID_Search,txt_Type_Search;

    public RcMovieSearchHolder(@NonNull View itemView) {
        super(itemView);
        img_Poster_Search = itemView.findViewById(R.id.img_Poster_Search);
        txt_Title_Search = itemView.findViewById(R.id.txt_Title_Search);
        txt_Year_Search = itemView.findViewById(R.id.txt_Year_Search);
        txt_imdbID_Search = itemView.findViewById(R.id.txt_imdbID_Search);
        txt_Type_Search = itemView.findViewById(R.id.txt_Type_Search);
    }
}
