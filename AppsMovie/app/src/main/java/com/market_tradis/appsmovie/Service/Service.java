package com.market_tradis.appsmovie.Service;

import com.market_tradis.appsmovie.Response.MovieResponse;
import com.market_tradis.appsmovie.Response.SearchResponse;
import com.market_tradis.appsmovie.Response.TVResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovie(@Query("api_key")String api_key);

    @GET("tv/airing_today")
    Call<TVResponse> getNowPlayingTV(@Query("api_key")String api_key);

    @GET("search/multi")
    Call<SearchResponse> getNowSearch(@Query("query")String key,@Query("api_key")String api_key);

    @GET("movie/upcoming")
    Call<MovieResponse> getMovieUpComing(@Query("api_key")String api_key);
    @GET("search/movie")
    Call<MovieResponse> getNowSearchMovie(@Query("query")String key,@Query("api_key")String api_key);
    @GET("search/tv")
    Call<TVResponse> getNowSearchTV(@Query("query")String key,@Query("api_key")String api_key);

}
