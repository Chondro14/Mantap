package com.market_tradis.appsmovie.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.market_tradis.appsmovie.Database.MovieHelper;
import com.market_tradis.appsmovie.Database.TVShowHelper;
import com.market_tradis.appsmovie.Model.Favorite;
import com.market_tradis.appsmovie.Model.TVShow;
import com.market_tradis.appsmovie.R;

import java.util.Objects;

public class DetailTVShowActivity extends AppCompatActivity {
    TextView Title,OriginalLanguage,Overview,ReleaseDate,Rating,Popularity;
    Button AddFavo;
    ImageView Backdrop,ImagePhoto;
   TVShowHelper helper;


    public static String TVShowp="tvshow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_t_v_show);
        Title=findViewById(R.id.title_tv_detail);
        OriginalLanguage=findViewById(R.id.language_tv_detail);
        Overview=findViewById(R.id.overview_tv_detail);
        ReleaseDate=findViewById(R.id.releasedate_tv_detail);
        Rating=findViewById(R.id.rating_tv_detail);
        Popularity=findViewById(R.id.popularity_tv_detail);
        AddFavo=findViewById(R.id.button_favorite);
        Backdrop=findViewById(R.id.backdrop_tv_detail);
        ImagePhoto=findViewById(R.id.poster_tv_Detail);
        final com.market_tradis.appsmovie.Model.TVShow tvShow= Objects.requireNonNull(getIntent().getExtras()).getParcelable(TVShowp);
        assert tvShow != null;
        Title.setText(tvShow.getTitle());
        OriginalLanguage.setText(tvShow.getLanguage());
        Overview.setText(tvShow.getOverview());
        ReleaseDate.setText(tvShow.getReleasedate());
        Rating.setText(tvShow.getVote_avg());
        Popularity.setText(tvShow.getPopularity());
        String imageUrl="https://image.tmdb.org/t/p/original";
        Glide.with(this).load(imageUrl+tvShow.getPoster()).into(ImagePhoto);
        Glide.with(this).load(imageUrl+tvShow.getBackdrop()).into(Backdrop);
        helper=new TVShowHelper(this);
        AddFavo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper.checkObject(tvShow.getId())){
                    helper.delete(tvShow.getId());
                    Toast.makeText(DetailTVShowActivity.this,"Delete"+tvShow.getTitle(),Toast.LENGTH_SHORT).show();

                }
                else {
                    Favorite favorite=new Favorite();
                    favorite.setFavid(tvShow.getId());
                    favorite.setFavPopularity(tvShow.getPopularity());
                    favorite.setFavBacdrop(tvShow.getBackdrop());
                    favorite.setFavImage(tvShow.getPoster());
                    favorite.setFavOriginalLanguage(tvShow.getLanguage());
                    favorite.setFavOverview(tvShow.getOverview());
                    favorite.setFavRating(tvShow.getVote_avg());
                    favorite.setFavReleaseDate(tvShow.getReleasedate());
                    favorite.setFavTitle(tvShow.getTitle());

                    helper.insert(favorite);
                    Toast.makeText(DetailTVShowActivity.this,R.string.favorite+tvShow.getTitle(),Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
