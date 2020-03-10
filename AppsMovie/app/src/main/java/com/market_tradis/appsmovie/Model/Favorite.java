package com.market_tradis.appsmovie.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
    private String Favid;
    private String FavTitle;
    private String FavOverview;
    private String FavRating;
    private String FavImage;
    private String FavReleaseDate;
    private String FavOriginalLanguage;
    private String FavBacdrop;
    private String FavPopularity;

    public Favorite(String favid, String favTitle, String favOverview, String favRating, String favImage, String favReleaseDate, String favOriginalLanguage, String favBacdrop, String favPopularity) {
        Favid = favid;
        FavTitle = favTitle;
        FavOverview = favOverview;
        FavRating = favRating;
        FavImage = favImage;
        FavReleaseDate = favReleaseDate;
        FavOriginalLanguage = favOriginalLanguage;
        FavBacdrop = favBacdrop;
        FavPopularity = favPopularity;
    }

    public String getFavid() {
        return Favid;
    }

    public void setFavid(String favid) {
        Favid = favid;
    }

    public String getFavTitle() {
        return FavTitle;
    }

    public void setFavTitle(String favTitle) {
        FavTitle = favTitle;
    }

    public String getFavOverview() {
        return FavOverview;
    }

    public void setFavOverview(String favOverview) {
        FavOverview = favOverview;
    }

    public String getFavRating() {
        return FavRating;
    }

    public void setFavRating(String favRating) {
        FavRating = favRating;
    }

    public String getFavImage() {
        return FavImage;
    }

    public void setFavImage(String favImage) {
        FavImage = favImage;
    }

    public String getFavReleaseDate() {
        return FavReleaseDate;
    }

    public void setFavReleaseDate(String favReleaseDate) {
        FavReleaseDate = favReleaseDate;
    }

    public String getFavOriginalLanguage() {
        return FavOriginalLanguage;
    }

    public void setFavOriginalLanguage(String favOriginalLanguage) {
        FavOriginalLanguage = favOriginalLanguage;
    }

    public String getFavBacdrop() {
        return FavBacdrop;
    }

    public void setFavBacdrop(String favBacdrop) {
        FavBacdrop = favBacdrop;
    }

    public String getFavPopularity() {
        return FavPopularity;
    }

    public void setFavPopularity(String favPopularity) {
        FavPopularity = favPopularity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Favid);
        dest.writeString(this.FavTitle);
        dest.writeString(this.FavOverview);
        dest.writeString(this.FavRating);
        dest.writeString(this.FavImage);
        dest.writeString(this.FavReleaseDate);
        dest.writeString(this.FavOriginalLanguage);
        dest.writeString(this.FavBacdrop);
        dest.writeString(this.FavPopularity);
    }

    public Favorite() {
    }

    protected Favorite(Parcel in) {
        this.Favid = in.readString();
        this.FavTitle = in.readString();
        this.FavOverview = in.readString();
        this.FavRating = in.readString();
        this.FavImage = in.readString();
        this.FavReleaseDate = in.readString();
        this.FavOriginalLanguage = in.readString();
        this.FavBacdrop = in.readString();
        this.FavPopularity = in.readString();
    }

    public static final Parcelable.Creator<Favorite> CREATOR = new Parcelable.Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel source) {
            return new Favorite(source);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };
}
