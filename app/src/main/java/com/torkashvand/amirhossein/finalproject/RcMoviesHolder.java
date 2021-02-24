package com.torkashvand.amirhossein.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RcMoviesHolder extends RecyclerView.ViewHolder {

    CardView card_rcMovie;
    ImageView img_PosterMovie;
    TextView txt_TitleMovie,txt_DesMovie;

    public RcMoviesHolder(@NonNull View itemView) {
        super(itemView);
        card_rcMovie=itemView.findViewById(R.id.card_rcMovie);
        img_PosterMovie = itemView.findViewById(R.id.img_PosterMovie);
        txt_TitleMovie=itemView.findViewById(R.id.txt_TitleMovie);
        txt_DesMovie=itemView.findViewById(R.id.txt_DesMovie);
    }

}
