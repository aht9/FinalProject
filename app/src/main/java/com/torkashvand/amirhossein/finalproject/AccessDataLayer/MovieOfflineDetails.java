package com.torkashvand.amirhossein.finalproject.AccessDataLayer;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class MovieOfflineDetails {
    String Title,Released,Genre,Actors,imdbID,Poster,Plot;

    public MovieOfflineDetails(String title,String released,String genre,String actors,String imdbID,String poster,String plot)
    {
        this.Title = title;
        this.Released = released;
        this.Genre = genre;
        this.Actors = actors;
        this.imdbID = imdbID;
        this.Poster = poster;
        this.Plot = plot;
    }
}

