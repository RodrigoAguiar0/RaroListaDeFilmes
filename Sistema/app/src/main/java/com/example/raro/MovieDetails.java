package com.example.raro;



import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Esta classe tem função de popular a tela activity_details fornecendo todos os detalhes que o
 * filme que foi selecionado na activity_main possua.
 *
 * @author Rodrigo Aguiar
 * @since  01/06/2021
 * */
public class MovieDetails extends AppCompatActivity {

    private TextView movieTitle,movieGenre, movieDetailsDetails, movieDetailsReview;
    private ImageView movieImage;
    private Toolbar detailsToolbar;
    private Movie movie;

    /**
     * Contém comandos que devem ser realizados assim que essa interface gráfica é criada,
     * capturando diversos itens da tela que serão modificados no momento da  insersão das \
     * informações do filme selecionado pelo usuário.
     *
     * @param savedInstanceState: Busca o estado da aplicação caso ela já esteja em memória.
     * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        detailsToolbar = findViewById(R.id.details_toolbar);
        movieTitle = findViewById(R.id.movie_details_title);
        movieImage = findViewById(R.id.movie_detais_poster);
        movieGenre = findViewById(R.id.movie_details_genre);
        movieDetailsDetails = findViewById(R.id.movie_details_details);
        movieDetailsReview = findViewById(R.id.movie_review);
        movie = (Movie) getIntent().getSerializableExtra("Movie");

        populateMovieDetails();
    }

    /**
     * Popula a tela de detalhes de filme com as informação do item selecionado.
     * */
    public void populateMovieDetails(){
        String genres = "";
        movieTitle.setText(movie.getCurrentTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2"
                .concat(movie.getPosterPath())).into(movieImage);

        detailsToolbar.setTitle("Detalhes de ".concat(movie.getCurrentTitle()));

        ArrayList <String> movieGenresList = movie.getMovieGenres();
        for (int i = 0; i < movieGenresList.size(); i++){
            if(movieGenresList.size() - 1 == i){
                genres = genres.concat(movieGenresList.get(i)).concat(".");
            }else{
                genres = genres.concat(movieGenresList.get(i)).concat(", ");
            }
        }
        movieGenre.setText("Gênero(s) - ".concat(genres));

        movieDetailsDetails.setText(movie.getOverview());

        movieDetailsReview.setText("Nota média dos usuários: ".concat(movie.getVoteAverage()
                .toString()));
    }

}
