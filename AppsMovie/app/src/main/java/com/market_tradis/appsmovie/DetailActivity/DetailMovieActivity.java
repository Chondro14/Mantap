package com.market_tradis.appsmovie.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.market_tradis.appsmovie.Database.MovieHelper;
import com.market_tradis.appsmovie.Model.Favorite;
import com.market_tradis.appsmovie.Model.Movie;
import com.market_tradis.appsmovie.R;

import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {
    TextView Title,OriginalLanguage,Overview,ReleaseDate,Rating,Popularity;
    Button AddFav;
    ImageView Backdrop,ImagePhoto;
    MovieHelper helper;

    public static String Movie="movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Title=findViewById(R.id.title_mv_detail);
        OriginalLanguage=findViewById(R.id.language_mv_detail);
        Overview=findViewById(R.id.overview_mv_detail);
        ReleaseDate=findViewById(R.id.releasedate_mv_detail);
        Rating=findViewById(R.id.rating_mv_detail);
        Popularity=findViewById(R.id.popularity_mv_detail);
        AddFav=findViewById(R.id.button_fav);
        Backdrop=findViewById(R.id.backdrop_mv_detail);
        ImagePhoto=findViewById(R.id.poster_mv_Detail);

        final com.market_tradis.appsmovie.Model.Movie movie= Objects.requireNonNull(getIntent().getExtras()).getParcelable(Movie);
        assert movie != null;
        Title.setText(movie.getTitle());
        OriginalLanguage.setText(movie.getLanguage());
        Overview.setText(movie.getOverview());
        ReleaseDate.setText(movie.getReleasedate());
        Rating.setText(movie.getVote_avg());
        Popularity.setText(movie.getPopularity());
        String imageUrl="https://image.tmdb.org/t/p/original";
        Glide.with(this).load(imageUrl+movie.getPoster()).into(ImagePhoto);
        Glide.with(this).load(imageUrl+movie.getBackdrop()).into(Backdrop);
        helper=new MovieHelper(this);
        helper.open();
        AddFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!helper.checkObject(movie.getId())){
                    helper.delete(movie.getId());
                    Toast.makeText(DetailMovieActivity.this,"Delete"+movie.getTitle(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Favorite favorite=new Favorite();
                    favorite.setFavid(movie.getId());
                    favorite.setFavPopularity(movie.getPopularity());
                    favorite.setFavBacdrop(movie.getBackdrop());
                    favorite.setFavImage(movie.getPoster());
                    favorite.setFavOriginalLanguage(movie.getLanguage());
                    favorite.setFavOverview(movie.getOverview());
                    favorite.setFavRating(movie.getVote_avg());
                    favorite.setFavReleaseDate(movie.getReleasedate());
                    favorite.setFavTitle(movie.getTitle());

                    helper.insert(favorite);
                    Toast.makeText(DetailMovieActivity.this,"Favorite"+movie.getTitle(),Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
