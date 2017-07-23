package com.erdem.kurumi.volley;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.erdem.kurumi.volley.adater.CustomListAdapter;
import com.erdem.kurumi.volley.app.AppController;
import com.erdem.kurumi.volley.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Takimlar extends ActionBarActivity {


    // Log tag
    private static final String TAG = Takimlar.class.getSimpleName();
    private Menu optionsMenu;
    //Anasayfadaki jsondan gelecek 2.sayfanın linklerini içeren arraylist
    private ArrayList<String> linklistesi = new ArrayList<String>();
    // Movies json url
    private static final String url = "http://animereplikleri.esy.es/LOL/Takimlar/Tak%C4%B1m.json";
    private ProgressDialog pDialog;
    private List<Movie> movieList = new ArrayList<Movie>();
    private ListView listView;
    private CustomListAdapter adapter;
    private ConnectivityManager myConnectivityManager;
    private NetworkInfo myNetworkInfo;
    private String title;

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takimlar);

        actionBar = getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ecf0f1")));


        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, movieList);
        listView.setAdapter(adapter);

        //List view deki iteme  tıklanınca olacak olaylar
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Takimlar.this, TakimBilgileri.class);
                // olusturulan intent e ana sayfadaki json okumasından gelen linkleri atıyor.
                intent.putExtra("jsonlink", linklistesi.get(position));
                startActivity(intent);
            }
        });



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
                        Movie movie = new Movie();
                        title = obj.getString("title");

                        movie.setTitle(title);
                        movie.setThumbnailUrl(obj.getString("image"));
                        movie.setRating(((Number) obj.get("rating")).doubleValue());

                        String gelen = obj.getString("link");
                        linklistesi.add(gelen);
                        movie.setLink(gelen);
                        //movie.setLink(obj.getString("link"));

                        movie.setYear(obj.getInt("releaseYear"));

                        // Genre is json array
                        JSONArray genreArry = obj.getJSONArray("genre");
                        ArrayList<String> genre = new ArrayList<String>();
                        for (int j = 0; j < genreArry.length(); j++) {
                            genre.add((String) genreArry.get(j));
                        }
                        movie.setGenre(genre);

                        // adding movie to movies array
                        movieList.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                       // Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_item, menu);

        return super.onCreateOptionsMenu(menu);
    }


}