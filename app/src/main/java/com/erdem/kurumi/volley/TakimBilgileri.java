package com.erdem.kurumi.volley;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.erdem.kurumi.volley.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TakimBilgileri extends ActionBarActivity {

    private static String TAG = Takimlar.class.getSimpleName();
    String url;
    String isim,takimGeçmişi,takimBa;
    ActionBar actionBar;
    //Arayüzdeki nesnelerin tanımlandığı alan
    TextView takimAdi;
    TextView takimBil;
    TextView takimBasari;
    TextView ustkoridor;
    TextView ormanci;
    TextView ortakoridor;
    TextView nisanci;
    TextView destek;


    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.left_to_rigth, R.anim.right_to_left);//sağdan kayma animasyonu
        actionBar = getActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ecf0f1")));

        setContentView(R.layout.activity_takim_bilgileri);
        // ilk sayfadan gelen json içindeki takım linki
        url = getIntent().getStringExtra("jsonlink");
        takimAdi = (TextView)findViewById(R.id.takimAdi);
        takimBil=(TextView)findViewById(R.id.bilgi);
        takimBasari=(TextView)findViewById(R.id.basari);
        ustkoridor=(TextView)findViewById(R.id.textView8);
        ormanci=(TextView)findViewById(R.id.textView10);
        ortakoridor=(TextView)findViewById(R.id.ortakoridor);
        nisanci=(TextView)findViewById(R.id.nisanci);
        destek=(TextView)findViewById(R.id.destek);

        final ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        // Showing progress dialog before making http request
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Yükleniyor...");
        pDialog.show();
        pDialog.setCancelable(false);

        // Creating json request obj
        JsonArrayRequest js = new JsonArrayRequest(url, new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, response.toString());
                hidePDialog();

                try {
                    JSONObject obj = response.getJSONObject(0);
                    isim = obj.getString("title");
                    takimAdi.setText(isim);
                    takimGeçmişi=obj.getString("description");
                    takimBil.setText(takimGeçmişi);
                    takimBa=obj.getString("award");
                    takimBasari.setText(takimBa);
                    //Takim logo resmi
                    NetworkImageView thumbNail = (NetworkImageView)findViewById(R.id.thumbnail);
                    thumbNail.setImageUrl(obj.getString("image"), imageLoader);

                    //Üst Koridor
                    NetworkImageView thumbNail1 = (NetworkImageView)findViewById(R.id.thumbnail1);
                    thumbNail1.setImageUrl(obj.getString("image1"), imageLoader);
                    ustkoridor.setText(obj.getString("oyuncu1"));

                    //Ormancı
                    NetworkImageView thumbNail2 = (NetworkImageView)findViewById(R.id.thumbnail2);
                    thumbNail2.setImageUrl(obj.getString("image2"), imageLoader);
                    ormanci.setText(obj.getString("oyuncu2"));

                    //Orta Koridor
                    NetworkImageView thumbNail3 = (NetworkImageView)findViewById(R.id.thumbnail3);
                    thumbNail3.setImageUrl(obj.getString("image3"), imageLoader);
                    ortakoridor.setText(obj.getString("oyuncu3"));

                    //Nişancı
                    NetworkImageView thumbNail4 = (NetworkImageView)findViewById(R.id.thumbnail4);
                    thumbNail4.setImageUrl(obj.getString("image4"), imageLoader);
                    nisanci.setText(obj.getString("oyuncu4"));

                    //Destek
                    NetworkImageView thumbNail5 = (NetworkImageView)findViewById(R.id.thumbnail5);
                    thumbNail5.setImageUrl(obj.getString("image5"), imageLoader);
                    destek.setText(obj.getString("oyuncu5"));

                } catch (JSONException e) {
                    e.printStackTrace();
                   // Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                hidePDialog();
            }

        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(js);

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
