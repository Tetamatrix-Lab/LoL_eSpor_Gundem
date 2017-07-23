package com.erdem.kurumi.volley.Ligler;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.erdem.kurumi.volley.R;
import com.erdem.kurumi.volley.adater.LigAdapter;
import com.erdem.kurumi.volley.app.AppController;
import com.erdem.kurumi.volley.model.TakımPuan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ChinaLig extends ActionBarActivity {
    private String urlJsonObj = "https://www.kimonolabs.com/api/68dgykqw?apikey=ISzQZSJvnwslwuRxxGRyqUvPXPP3pwoi";
    private static String TAG = LigPuanDurumu.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView listview;
    private LigAdapter adapter;
    ActionBar actionBar;

    private List<TakımPuan> takimList = new ArrayList<TakımPuan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lig_puan_durumu);
        actionBar = getActionBar();
        getSupportActionBar().hide();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Yükleniyor...");
        pDialog.setCancelable(false);

        listview=(ListView)findViewById(R.id.listView);
        adapter=new LigAdapter(this,takimList);
        listview.setAdapter(adapter);

        makeJsonObjectRequest();//Metodun başlamasını sağlayan cağırma
    }

    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
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
                    JSONArray collection1 = results.getJSONArray("collection1");//Json arrayı seçtik

                    for (int i = 0; i < collection1.length(); i++) {
                        JSONObject result11 = collection1.getJSONObject(i);//tüm json nesnelerini alır

                        //Nesne oluşturduk titleı set ettik
                        //Field da tanımlarsan birkere obje oluşturur yani tek takim olur
                        TakımPuan takim=new TakımPuan();
                        JSONObject results2 = result11.getJSONObject("TakimAdi");
                        //String at2 = results2.getString("href");
                        String at3 = results2.getString("text");
                        takim.setTitle(at3);

                        JSONArray arry12 = result11.getJSONArray("Puanlar");
                        String sira=arry12.getString(0);
                        String galibiyet = arry12.getString(1);
                        String malubiyet = arry12.getString(2);

                        takim.setSira(sira);
                        takim.setGalibiyet(galibiyet);
                        takim.setMalubiyet(malubiyet);


                        takimList.add(takim);
                        // tw2.setText(takimList.get(1).getTitle());
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
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