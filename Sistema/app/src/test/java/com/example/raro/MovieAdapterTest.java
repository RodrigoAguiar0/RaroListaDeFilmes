package com.example.raro;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.test.core.app.ApplicationProvider;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.raro.ViewModel.ViewModelMovieDetails;
import junit.framework.TestCase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
@RunWith(RobolectricTestRunner.class)
public class MovieAdapterTest extends TestCase{

    private Context context = ApplicationProvider.getApplicationContext();
    private ViewGroup viewGroup = new ViewGroup(context) {
        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int count = getChildCount();
            int prevChildRight = 0;
            int prevChildBottom = 0;
            for (int i = 0; i < count; i++) {
                final View child = getChildAt(i);
                child.layout(prevChildRight, prevChildBottom,
                        prevChildRight + child.getMeasuredWidth(),
                        prevChildBottom + child.getMeasuredHeight());
                prevChildRight += child.getMeasuredWidth();
            }
        }
    };
    private ViewModelMovieDetails controllerDetails;
    private RequestQueue mQueue;
    private ArrayList<Movie> movieList;
    private int page = 1;
    public final MovieAdapter movieAdapter = new MovieAdapter(context, PopulateList());

    @Test
    public void testGetViewNormalCase() {
        controllerDetails = new ViewModelMovieDetails();
        movieList = new ArrayList();
        final int positionNormalCase = 458576;
        assertEquals(movieAdapter.getView(positionNormalCase, null, viewGroup).
                findViewById(R.id.movie_title), "Monster Hunter");
    }


    private ArrayList<Movie> PopulateList(){
        mQueue = Volley.newRequestQueue(context);
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=d77a8e9c222a7e2f3b70d8f516ea7a42&language=pt-BR&sort_by=popularity.desc&include_adult=false&include_video=false&page=" + page;
        page++;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            movieList = controllerDetails.jsonParse(jsonArray);
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
        return movieList;
    }
}