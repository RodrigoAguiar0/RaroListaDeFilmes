package com.example.raro;

/**
 * MainActivity.java
 * Versão: 1.0
 * Data de criação: 09/09/2020
 *
 * Este sistema tem o propósito de apresentar uma lista em ordem dos filmes mais recentes aos
 * mais antigos, e permitir uma visualização detalhada de cada filme.
 * */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.raro.ViewModel.ViewModelDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Esta classe tem a função de controlar requisições à API que contém a base de dados, bem como
 * controlar quando o Fragment contendo a lista de filmes deve ser mostrada, e controlar o
 * acesso à diversas páginas da base de dados pelas classes Controller.
 *
 * @author Rodrigo Aguiar
 * @since  01/06/2021
 * */
public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {



    final private Context context = this;
    private ViewModelDetails controllerDetails;
    private RequestQueue mQueue;
    private ArrayList<Movie> movieList;
    private int page = 1;
    private MovieAdapter movieAdapter;
    private ListView listView;
    private int preLast;

    /**
     * Contém comandos que devem ser realizados assim que essa interface gráfica é criada.
     *
     * @param savedInstanceState: Busca o estado da aplicação caso ela já esteja em memória.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.movies_list);
        controllerDetails = new ViewModelDetails();
        movieList = new ArrayList();
        PopulateList();
        listView.setOnScrollListener(this);
    }


    /**
     * Realiza o request na API que contém todas as informações dos filmes, popula a lista de filmes
     * que será mostrada na tela e controla quando a próxima página da API deve ser requisitada.
     * */
    private void PopulateList(){
        mQueue = Volley.newRequestQueue(this);
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=d77a8e9c222a7e2f3b70d8f516ea7a42&language=pt-BR&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + page;
        page++;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            movieList = controllerDetails.jsonParse(jsonArray);
                            showMovieList();
                        } catch (JSONException error) {
                            error.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    /**
     * Utiliza o MovieAdapter para enviar os objetos Movie, que implementam Serializable, através do
     * Intent a classe Details, para que ela tenha informações dos filmes, e seleciona o
     * MovieAdapter como adaptador dos dados em Movie para a tela.
     * */
    private void showMovieList(){
        movieAdapter = new MovieAdapter(this, movieList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie tempMovie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, Details.class);
                intent.putExtra("Movie", tempMovie);
                startActivity(intent);
            }
        });
        listView.setAdapter(movieAdapter);;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    /**
     * Controla onde o usuário está durante a navegação da tela, e chama a função de repopular a
     * lista de filmes quando o usuário chega ao fim da tela.
     *
     * @param view
     * @param firstVisibleItem
     * @param visibleItemCount
     * @param totalItemCount
     * */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        {

            switch (view.getId()) {
                case R.id.movies_list:
                    final int lastItem = firstVisibleItem + visibleItemCount;
                    if (lastItem == totalItemCount) {
                        if (preLast != lastItem) {
                            preLast = lastItem;
                        }
                        PopulateList();
                    }
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
