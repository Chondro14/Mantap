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
import android.widget.Toast;

import com.market_tradis.appsmovie.Adapter.TVShowAdapter;
import com.market_tradis.appsmovie.BuildConfig;
import com.market_tradis.appsmovie.Model.TVShow;
import com.market_tradis.appsmovie.R;
import com.market_tradis.appsmovie.Response.TVResponse;
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
public class TVShowFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TVShowAdapter adapter;
    private ProgressDialog progressDialog;
    private static String TVShowO="tvshow";

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_v_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rv_tv);
        swipeRefreshLayout=view.findViewById(R.id.swiper_tv);
        showTV();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showTV();
            }
        });
    }
    private void showTV(){
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Get Data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        List<com.market_tradis.appsmovie.Model.TVShow> tvShows=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new TVShowAdapter(getActivity(),tvShows);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        JSoN();



    }
    private void JSoN(){
        try {
            Client client=new Client();
            Service service=client.getClient().create(Service.class);
            Call<TVResponse> tvResponseCall=service.getNowPlayingTV(BuildConfig.API_KEY);
            tvResponseCall.enqueue(new Callback<TVResponse>() {
                @Override
                public void onResponse(Call<TVResponse> call, Response<TVResponse> response) {
                    List<TVShow> tvShowList=response.body().getTvShowList();
                    recyclerView.setAdapter(adapter);
                    adapter.setTvShowList(tvShowList);
                    if(swipeRefreshLayout.isRefreshing()){
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    progressDialog.dismiss();

                }

                @Override
                public void onFailure(Call<TVResponse> call, Throwable t) {
                    Toast.makeText(getActivity(),"Failed to get data",Toast.LENGTH_SHORT).show();

                }
            });

        } catch (Exception e) {
            Toast.makeText(getActivity(),"Failed to get data",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TVShowO,new ArrayList<Parcelable>(adapter.getTvShowList()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState!=null){
            ArrayList<TVShow> tvShowArrayList;
            tvShowArrayList=savedInstanceState.getParcelableArrayList(TVShowO);
            adapter.setTvShowList(tvShowArrayList);
            progressDialog.dismiss();

        }
    }
}
