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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.erdem.kurumi.volley.adater.HaberlerAdapter;
import com.erdem.kurumi.volley.adater.KDAadapter;
import com.erdem.kurumi.volley.app.AppController;
import com.erdem.kurumi.volley.model.Movie2;
import com.erdem.kurumi.volley.model.TakımPuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Haberler extends ActionBarActivity {


    // Log tag
    private static final String TAG = Haberler.class.getSimpleName();
    private Menu optionsMenu;

    //Anasayfadaki jsondan gelecek 2.sayfanin linklerini iceren arraylist
    private ArrayList<String> linklistesi = new ArrayList<String>();

    // Movies json url
    private static final String urlJsonObj = "https://www.kimonolabs.com/api/5aj9dzu4?apikey=ISzQZSJvnwslwuRxxGRyqUvPXPP3pwoi";
    private ProgressDialog pDialog;
    private List<Movie2> movieList = new ArrayList<Movie2>();
    private ListView listView;
    private HaberlerAdapter adapter;
    private ConnectivityManager myConnectivityManager;
    private NetworkInfo myNetworkInfo;
    private String title;
    private String title2;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haberler);
        actionBar = getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ecf0f1")));
        listView = (ListView) findViewById(R.id.listViewhaberler);
        adapter = new HaberlerAdapter(this, movieList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Yükleniyor...");
        pDialog.show();
        pDialog.setCancelable(true);
        makeJsonObjectRequest();
        //List view deki iteme  tıklanınca olacak olaylar
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Haberler.this, HaberGoster.class);
                // olusturulan intent e ana sayfadaki json okumasından gelen linkleri atıyor.
                intent.putExtra("haberlinki", linklistesi.get(position));
                startActivity(intent);
            }
        });
    }

        // Creating json request obj
    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                          /*String at1= response.getString("name");
                          	String at2 = response.getString("count");
                        	String at3 = response.getString("frequency");
                        	String at4 = response.getString("version");
                        	String at5 = response.getString("newdata");
                        	String at6 = response.getString("lastrunstatus");
                        	String at7 = response.getString("lastsuccess");
                        	String at8 = response.getString("thisversionstatus");
                        	String at9 = response.getString("nextrun");
                        	String at10 = response.getString("thisversionrun");*/

                    JSONObject results = response.getJSONObject("results");//Json nesnesine girdik
                    JSONArray collection1 = results.getJSONArray("collection1");//Json array sectik
                    for (int i = 0; i < collection1.length(); i++) {
                        JSONObject result11 = collection1.getJSONObject(i);//tum json nesnelerini alır

                        //Nesne olusturduk title  set ettik
                        //Field da tanimlarsan birkere obje olusturur yani tek takim olur
                        Movie2 movie = new Movie2();
                        JSONObject results2 = result11.getJSONObject("resimler");
                        movie.setThumbnailUrl(results2.getString("src"));

                        JSONObject results3 = result11.getJSONObject("baslik");
                        String at3 = results3.getString("text");
                        movie.setTitle(at3);//haber basligi set edildi*/
                        String link= results3.getString("href");
                        linklistesi.add(link);
                        movie.setLink(link);
                        movieList.add(movie);
                    }
                    adapter.notifyDataSetChanged();

                    // Toast.makeText(LigPuanDurumu.this,takimList.get(2).getGalibiyet(),Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                    // Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_LONG).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}