package com.market_tradis.appsmovie.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Search implements Parcelable {
    @SerializedName("id")
    private String search_id;

    @SerializedName("original_title")
    private String search_title;

    @SerializedName("overview")
    private String search_overview;

    @SerializedName("vote_average")
    private Double search_rating;

    @SerializedName("poster_path")
    private String search_poster;

    @SerializedName("backdrop_path")
    private String search_backdrop;

    @SerializedName("original_language")
    private String search_language;

    @SerializedName("first_air_date")
    private String releasedate2;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName("release_date")
    private String releasedate;

    public Search(String search_id, String search_title, String search_overview, Double search_rating, String search_poster, String search_backdrop, String search_language, String releasedate2, String popularity, String releasedate) {
        this.search_id = search_id;
        this.search_title = search_title;
        this.search_overview = search_overview;
        this.search_rating = search_rating;
        this.search_poster = search_poster;
        this.search_backdrop = search_backdrop;
        this.search_language = search_language;
        this.releasedate2 = releasedate2;
        this.popularity = popularity;
        this.releasedate = releasedate;
    }

    public String getSearch_id() {
        return search_id;
    }

    public void setSearch_id(String search_id) {
        this.search_id = search_id;
    }

    public String getSearch_title() {
        return search_title;
    }

    public void setSearch_title(String search_title) {
        this.search_title = search_title;
    }

    public String getSearch_overview() {
        return search_overview;
    }

    public void setSearch_overview(String search_overview) {
        this.search_overview = search_overview;
    }

    public Double getSearch_rating() {
        return search_rating;
    }

    public void setSearch_rating(Double search_rating) {
        this.search_rating = search_rating;
    }

    public String getSearch_poster() {
        return search_poster;
    }

    public void setSearch_poster(String search_poster) {
        this.search_poster = search_poster;
    }

    public String getSearch_backdrop() {
        return search_backdrop;
    }

    public void setSearch_backdrop(String search_backdrop) {
        this.search_backdrop = search_backdrop;
    }

    public String getSearch_language() {
        return search_language;
    }

    public void setSearch_language(String search_language) {
        this.search_language = search_language;
    }

    public String getReleasedate2() {
        return releasedate2;
    }

    public void setReleasedate2(String releasedate2) {
        this.releasedate2 = releasedate2;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.search_id);
        dest.writeString(this.search_title);
        dest.writeString(this.search_overview);
        dest.writeValue(this.search_rating);
        dest.writeString(this.search_poster);
        dest.writeString(this.search_backdrop);
        dest.writeString(this.search_language);
        dest.writeString(this.releasedate2);
        dest.writeString(this.popularity);
        dest.writeString(this.releasedate);
    }

    public Search() {
    }

    protected Search(Parcel in) {
        this.search_id = in.readString();
        this.search_title = in.readString();
        this.search_overview = in.readString();
        this.search_rating = (Double) in.readValue(Double.class.getClassLoader());
        this.search_poster = in.readString();
        this.search_backdrop = in.readString();
        this.search_language = in.readString();
        this.releasedate2 = in.readString();
        this.popularity = in.readString();
        this.releasedate = in.readString();
    }

    public static final Parcelable.Creator<Search> CREATOR = new Parcelable.Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel source) {
            return new Search(source);
        }

        @Override
        public Search[] newArray(int size) {
            return new Search[size];
        }
    };
}
