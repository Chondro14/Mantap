package com.market_tradis.appsmovie.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.market_tradis.appsmovie.Activity.MainActivity;
import com.market_tradis.appsmovie.Adapter.SearchAdapter;
import com.market_tradis.appsmovie.BuildConfig;
import com.market_tradis.appsmovie.Model.Search;
import com.market_tradis.appsmovie.R;
import com.market_tradis.appsmovie.Response.SearchResponse;
import com.market_tradis.appsmovie.Service.Client;
import com.market_tradis.appsmovie.Service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.market_tradis.appsmovie.Activity.MainActivity.SEARCH;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    private String key ;
    private static String STATE = "state";

    public SearchFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        key = getArguments().getString(SEARCH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rv_search);
        ShowSearch();
        JSoNSearch(key);
        if(savedInstanceState!=null){
            ArrayList<Search> searcheslist=savedInstanceState.getParcelableArrayList(STATE);
            searchAdapter.setSearchList(searcheslist);
            recyclerView.setAdapter(searchAdapter);

        }
        else {
            JSoNSearch(key);
        }

    }
    private void ShowSearch(){
        List<Search> searches=new ArrayList<>();
        searchAdapter=new SearchAdapter(getActivity(),searches);
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.setSearchList(searches);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        JSoNSearch(key);


    }
    private void JSoNSearch(String key){
        try {

            Client Client = new Client();
            Service service = Client.getClient().create(Service.class);
            Call<SearchResponse> call = service.getNowSearch(key, BuildConfig.API_KEY);
            call.enqueue(new Callback<SearchResponse>() {


                @Override
                public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                    assert response.body() != null;
                    List<Search> searches = response.body().getSearchList();
                    recyclerView.setAdapter(new SearchAdapter(getActivity().getApplicationContext(), searches));
                    recyclerView.smoothScrollToPosition(0);
                    searchAdapter.setSearchList(searches);

                }

                @Override
                public void onFailure(Call<SearchResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE, new ArrayList<Parcelable>(searchAdapter.getSearchList()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<Search> list= savedInstanceState.getParcelableArrayList(STATE);
            searchAdapter.setSearchList(list);
            recyclerView.setAdapter(searchAdapter);
        }

    }
}
