package com.market_tradis.appsmovie.Response;

import com.google.gson.annotations.SerializedName;
import com.market_tradis.appsmovie.Model.Movie;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovies() {
        return movies;
    }
}
