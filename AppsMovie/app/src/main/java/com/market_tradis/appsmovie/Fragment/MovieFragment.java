package com.market_tradis.appsmovie.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.market_tradis.appsmovie.Adapter.MovieAdapter;
import com.market_tradis.appsmovie.BuildConfig;
import com.market_tradis.appsmovie.Model.Movie;
import com.market_tradis.appsmovie.R;
import com.market_tradis.appsmovie.Response.MovieResponse;
import com.market_tradis.appsmovie.Service.Client;
import com.market_tradis.appsmovie.Service.Service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MovieAdapter adapter;
    public static String Moviee="move";

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rv_mv);
        swipeRefreshLayout=view.findViewById(R.id.swiper_mv);
        show();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                show();

            }
        });
    }
    private void show(){
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Get Data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        List<Movie> movieList=new ArrayList<>();
        adapter=new MovieAdapter(getActivity(),movieList);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter.notifyDataSetChanged();
        JsonMV();

    }
    private void JsonMV(){
        try {
            Client client=new Client();
            final Service service= client.getClient().create(Service.class);

            Call<MovieResponse> movieCall=service.getNowPlayingMovie(BuildConfig.API_KEY);
            movieCall.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(@NonNull Call<MovieResponse> call,@NonNull Response<MovieResponse> response) {
                    assert response.body() != null;
                    List<Movie> movies=response.body().getMovies();
                    recyclerView.setAdapter(new MovieAdapter(getActivity(),movies));
                    adapter.setMovies(movies);
                    if(swipeRefreshLayout.isRefreshing()){
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(@NonNull Call<MovieResponse> call, Throwable t) {
                    Toast.makeText(getActivity(),"Fail to load",Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            ArrayList<Movie> moove;
            moove=savedInstanceState.getParcelableArrayList(Moviee);
            adapter.setMovies(moove);
            recyclerView.setAdapter(adapter);
            progressDialog.dismiss();
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Moviee,new ArrayList<Parcelable>(adapter.getMovies()));
    }
}
