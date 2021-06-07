package com.example.raro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Esta classe tem a função de implementar o getView da classe ArrayAdapter para que ela aceite o objeto
 * Movie, que implementa Serializable, para que seja possível utilizar a classe Intent para enviar
 * as informações da lista de filmes da base de dados ao Fragment com a lista de filmes e a tela com
 * detalhes de cada filme.
 *
 * @author Rodrigo Aguiar
 * @since  01/06/2021
 * */
public class MovieAdapter extends ArrayAdapter <Movie>{

    private Context movieAdapterContext;
    private final List<Movie> moviesList;
    private TextView movieTitle;
    private TextView releaseDate;
    private ImageView poster;

    /**
     * Construtor herdado do ArrayAdapter e utilizado nessa extensão.
     *
     * @param context: Contexto onde o MovieAdapter será usado.
     * @param list: Lista contendo os filmes.
     * */
    public MovieAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Movie> list) {
        super(context, 0, list);
        movieAdapterContext = context;
        moviesList = list;
    }

    /**
     * Captura a View alvo das informações, cria o Fragment dentro dela e envia as informações dos
     * filmes para eles.
     *
     * @param position: Posição na lista.
     * @param convertView: View contendo as informações a serem inseridas na view inflada.
     * @param parent: Página que chamará o fragment a ser criado.
     *
     * @return listItem: Retorna a view inflada na tela.
     * */
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(movieAdapterContext)
                    .inflate(R.layout.list_movies, parent, false);
        }

        poster = listItem.findViewById(R.id.movie_poster);
        Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2".concat(moviesList
                .get(position).getPosterPath())).into(poster);

        movieTitle = listItem.findViewById(R.id.movie_title);
        movieTitle.setText(moviesList.get(position).getCurrentTitle()
                .concat(" (")
                .concat(moviesList.get(position).getOriginalTitle())
                .concat(")"));

        releaseDate = listItem.findViewById(R.id.movie_release_date);
        releaseDate.setText("Data de Lançamento: ".concat(moviesList.get(position).getReleaseDate()));

        return listItem;
    }
}
