package com.market_tradis.appsmovie.Response;

import com.google.gson.annotations.SerializedName;
import com.market_tradis.appsmovie.Model.Search;

import java.util.List;

public class SearchResponse {
    @SerializedName("results")
    private List<Search> searchList;

    public List<Search> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Search> searchList) {
        this.searchList = searchList;
    }
}
