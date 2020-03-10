package com.market_tradis.appsmovie.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.market_tradis.appsmovie.Fragment.MovieFragment;
import com.market_tradis.appsmovie.Fragment.ProfileFragment;
import com.market_tradis.appsmovie.Fragment.SearchFragment;
import com.market_tradis.appsmovie.Fragment.TVShowFragment;
import com.market_tradis.appsmovie.R;
import com.market_tradis.appsmovie.SettingsActivity;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    MaterialSearchView searchView;
    public static final String SEARCH = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.nav_bot);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        searchView=findViewById(R.id.search_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.movie:
                      fragment= new MovieFragment();
                      getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName()).commit();
                        if (getSupportActionBar() != null){
                            getSupportActionBar().setTitle(getString(R.string.movie));
                        }
                      return true;

                    case R.id.tvshow:
                        fragment=new TVShowFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName()).commit();
                        if (getSupportActionBar() != null){
                            getSupportActionBar().setTitle(getString(R.string.tv_show));
                        }
                        return true;
                    case R.id.profile:
                        fragment=new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName()).commit();
                        if (getSupportActionBar() != null){
                            getSupportActionBar().setTitle(getString(R.string.profile));
                        }
                        return true;
                }
                return false;
            }
        });
        searchView.setHint(getString(R.string.search_hint));
        searchView.setHintTextColor(getResources().getColor(R.color.colorAccent));
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle=new Bundle();
                bundle.putString(SEARCH, query);
                Fragment fragment;
                fragment=new SearchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName()).commit();
                if(getSupportActionBar()!=null){
                    getSupportActionBar().setTitle(getString(R.string.search)+" '"+query+"'");
                }
                fragment.setArguments(bundle);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sidebar,menu);
        MenuItem item=menu.findItem(R.id.search_bara);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.favorite){
            Intent intent=new Intent(MainActivity.this,FavoriteActivity.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.settings){
            Intent intent=new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
