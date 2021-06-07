package com.example.raro;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Esta é a classe modelo de filme, sendo cada objeto, um filme. Contém apenas o construtor vazio
 * já que é o único necessário, e todos os getters e setters das informações de cada filme.
 *
 * @author Rodrigo Aguiar
 * @since  01/06/2021
 * */
public class Movie extends AppCompatActivity implements Serializable {

    private Integer popularity, voteCount, voteAverage;
    private int movieId;
    private ArrayList<String> movieGenres;
    private String posterPath, backdropPath, originalLanguage, originalTitle, currentTitle, overview,
            releaseDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public Movie(){
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public ArrayList<String> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(JSONArray listOfGenres) throws JSONException {
        int genre;
        movieGenres = new ArrayList();
        for(int i = 0; i < listOfGenres.length(); i++){
            genre = listOfGenres.getInt(i);
            switch(genre) {
                case 28:
                    this.movieGenres.add("Ação");
                    break;
                case 12:
                    this.movieGenres.add("Aventura");
                    break;
                case 16:
                    this.movieGenres.add("Animação");
                    break;
                case 35:
                    this.movieGenres.add("Comédia");
                    break;
                case 80:
                    this.movieGenres.add("Criminal");
                    break;
                case 99:
                    this.movieGenres.add("Documentário");
                    break;
                case 18:
                    this.movieGenres.add("Drama");
                    break;
                case 10751:
                    this.movieGenres.add("Para Toda Família");
                    break;
                case 14:
                    this.movieGenres.add("Fantasia");
                    break;
                case 36:
                    this.movieGenres.add("Histórico");
                    break;
                case 27:
                    this.movieGenres.add("Terror");
                    break;
                case 10402:
                    this.movieGenres.add("Musical");
                    break;
                case 9648:
                    this.movieGenres.add("Mistério");
                    break;
                case 10749:
                    this.movieGenres.add("Romance");
                    break;
                case 878:
                    this.movieGenres.add("Ficção Científica");
                    break;
                case 10770:
                    this.movieGenres.add("Filme de TV");
                    break;
                case 53:
                    this.movieGenres.add("Suspense");
                    break;
                case 10752:
                    this.movieGenres.add("Guerra");
                    break;
                case 17:
                    this.movieGenres.add("Faroeste");
                    break;
            }
        }
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Integer getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Integer voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getCurrentTitle() {
        return currentTitle;
    }

    public void setCurrentTitle(String currentTitle) {
        this.currentTitle = currentTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
