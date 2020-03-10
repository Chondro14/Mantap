package com.market_tradis.appsmovie.Response;

import com.google.gson.annotations.SerializedName;
import com.market_tradis.appsmovie.Model.TVShow;

import java.util.List;

public class TVResponse {
    @SerializedName("results")
    private List<TVShow> tvShowList;

    public List<TVShow> getTvShowList() {
        return tvShowList;
    }
}
