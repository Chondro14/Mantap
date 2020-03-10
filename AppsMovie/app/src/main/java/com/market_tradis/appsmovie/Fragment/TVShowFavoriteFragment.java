package com.market_tradis.appsmovie.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.market_tradis.appsmovie.Adapter.FavoriteAdapter;
import com.market_tradis.appsmovie.Database.TVShowHelper;
import com.market_tradis.appsmovie.Model.Favorite;
import com.market_tradis.appsmovie.R;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowFavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    LinkedList<Favorite> linkedList;
    TVShowHelper helper;
    FavoriteAdapter adapter;


    public TVShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_t_v_show_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.rv_fav_tv);
    }
    private class LoadData extends AsyncTask<Void,Void, ArrayList<Favorite>> {

        protected void onPreExecute(){
            super.onPreExecute();
            if(linkedList.size()>0){
                linkedList.clear();
            }
        }
        protected void onPostExecute(ArrayList<Favorite> favorites){
            super.onPostExecute(favorites);
            linkedList.addAll(favorites);
            adapter.setFavoriteList(linkedList);
            adapter.notifyDataSetChanged();

        }

        @Override
        protected ArrayList<Favorite> doInBackground(Void... voids) {
            return helper.query();
        }
    }

    @Override
    public void onResume() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        helper=new TVShowHelper(getActivity());
        helper.open();
        linkedList=new LinkedList<>();
        adapter=new FavoriteAdapter(getActivity());
        adapter.setFavoriteList(linkedList);
        recyclerView.setAdapter(adapter);
        new TVShowFavoriteFragment.LoadData().execute();

        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        super.onDestroy();
        if(helper!=null){
            helper.close();
        }
    }
}
