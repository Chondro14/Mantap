package com.market_tradis.appsmovie.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TVShow implements Parcelable {
    @SerializedName("name")
    private String Title;
    @SerializedName("poster_path")
    private String Poster;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("first_air_date")
    private String releasedate;
    @SerializedName("id")
    private String id;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private String vote_avg;
    @SerializedName("popularity")
    private String popularity;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVote_avg() {
        return vote_avg;
    }

    public void setVote_avg(String vote_avg) {
        this.vote_avg = vote_avg;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @SerializedName("original_language")
    private String language;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Title);
        dest.writeString(this.Poster);
        dest.writeString(this.backdrop);
        dest.writeString(this.releasedate);
        dest.writeString(this.id);
        dest.writeString(this.overview);
        dest.writeString(this.vote_avg);
        dest.writeString(this.popularity);
        dest.writeString(this.language);
    }

    public TVShow() {
    }

    protected TVShow(Parcel in) {
        this.Title = in.readString();
        this.Poster = in.readString();
        this.backdrop = in.readString();
        this.releasedate = in.readString();
        this.id = in.readString();
        this.overview = in.readString();
        this.vote_avg = in.readString();
        this.popularity = in.readString();
        this.language = in.readString();
    }

    public static final Parcelable.Creator<TVShow> CREATOR = new Parcelable.Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel source) {
            return new TVShow(source);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
