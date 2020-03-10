package com.market_tradis.appsmovie;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.market_tradis.appsmovie.Fragment.MovieFavoriteFragment;
import com.market_tradis.appsmovie.Fragment.TVShowFavoriteFragment;
import com.market_tradis.appsmovie.Fragment.TVShowFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.movie, R.string.tv_show};
    private final Context mContext;

    public SectionPagerAdapter(@NonNull FragmentManager fm,  Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new MovieFavoriteFragment();
                break;
            case 1:
                fragment=new TVShowFavoriteFragment();
                break;

        }

        return fragment;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
