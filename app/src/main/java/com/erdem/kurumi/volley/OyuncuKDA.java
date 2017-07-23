package com.erdem.kurumi.volley;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.erdem.kurumi.volley.adater.KDAadapter;
import com.erdem.kurumi.volley.app.AppController;
import com.erdem.kurumi.volley.model.Movie2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class OyuncuKDA extends ActionBarActivity {


    // Log tag
    private static final String TAG = OyuncuKDA.class.getSimpleName();
    private Menu optionsMenu;

    //Anasayfadaki jsondan gelecek 2.sayfanın linklerini içeren arraylist
    private ArrayList<String> linklistesi = new ArrayList<String>();

    // Movies json url
    private static final String url = "http://animereplikleri.esy.es/LOL/OyuncuKDA.json";
    private ProgressDialog pDialog;
    private List<Movie2> movieList = new ArrayList<Movie2>();
    private ListView listView;
    private KDAadapter adapter;

    private ConnectivityManager myConnectivityManager;
    private NetworkInfo myNetworkInfo;
    private String title;
    private String title2;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyuncu_kd);
        actionBar = getActionBar();
        getSupportActionBar().hide();
        listView = (ListView) findViewById(R.id.list);
        adapter = new KDAadapter(this, movieList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Yükleniyor...");
        pDialog.show();
        pDialog.setCancelable(false);


        // Creating json request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                hidePDialog();

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);
                        Movie2 movie = new Movie2();
                        title = obj.getString("title");
                        title2=obj.getString("releaseYear");
                        movie.setTitle(title);
                        movie.setYear(title2);
                        movie.setThumbnailUrl(obj.getString("image"));
                        movie.setRating(((Number) obj.get("rating")).doubleValue());


                        // adding movie to movies array
                        movieList.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        //Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                    }

                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}