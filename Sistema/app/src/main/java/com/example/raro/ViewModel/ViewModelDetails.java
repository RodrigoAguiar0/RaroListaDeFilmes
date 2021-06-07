package com.example.raro.ViewModel;

import androidx.appcompat.app.AppCompatActivity;

import com.example.raro.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Essa classe é um view-model que tem a funçao de controlar as informações de cada filme através da
 * busca em uma lista contendo JSONs, criando o objeto e adicionando ele a uma lista de filmes, que
 * será retornada ao método que o chamou
 *
 * @author Rodrigo Aguiar
 * @since  01/06/2021
 * @return movieList: Lista de filmes que foram adquiridos da base de dados através do Request.
 * */
public class ViewModelDetails extends AppCompatActivity {

    private ArrayList <Movie> movieList = new ArrayList();

    public ArrayList<Movie> jsonParse(JSONArray jsonArray) throws JSONException {
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject movieJson = jsonArray.getJSONObject(i);
            Movie movie = new Movie();
            movie.setPopularity(movieJson.getInt("popularity"));
            movie.setVoteCount(movieJson.getInt("vote_count"));
            movie.setPosterPath(movieJson.getString("poster_path"));
            movie.setMovieId(movieJson.getInt("id"));
            movie.setBackdropPath(movieJson.getString("backdrop_path"));
            movie.setOriginalLanguage(movieJson.getString("original_language"));
            movie.setOriginalTitle(movieJson.getString("original_title"));
            movie.setCurrentTitle(movieJson.getString("title"));
            movie.setVoteAverage(movieJson.getInt("vote_average"));
            movie.setOverview(movieJson.getString("overview"));
            movie.setReleaseDate(movieJson.getString("release_date"));
            movie.setMovieGenres(movieJson.getJSONArray("genre_ids"));
            movieList.add(movie);
            }
        return movieList;
        }
    }