package com.erdem.kurumi.volley;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erdem.kurumi.volley.R;
import com.erdem.kurumi.volley.adater.AnasayfaMeterialAdapter;
import com.erdem.kurumi.volley.adater.CustomGridAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class AnasayfaMeterial extends ActionBarActivity {
    ActionBar actionBar;
    GridView gv;
    private ConnectivityManager myConnectivityManager;
    private NetworkInfo myNetworkInfo;
    private Menu optionsMenu;
    public static String[] Sayfalar = {"Haberler", "Takımlar", "Puan Durumları", "Maç Özetleri", "Kda Oranları","Maç Fikstürleri"};
    public static int[] SayfaImages = {R.drawable.haberler, R.drawable.takimlar, R.drawable.avrupaligs, R.drawable.macozet, R.drawable.kdaa,R.drawable.kmf};
    MediaPlayer btnSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa_meterial);
        actionBar = getActionBar();
        getOverflowMenu();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#bdc3c7")));
        if (!checkMyConnection()) {
            //Toast.makeText(getApplicationContext(), "Internet Baglantinizi kontrol Ediniz!...", Toast.LENGTH_LONG).show();
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AnasayfaMeterial.this);
            alertDialog.setMessage("Internet Baglantinizi kontrol Ediniz!...");
            AlertDialog alert = alertDialog.create();
            alert.show();
        }

        btnSound= MediaPlayer.create(AnasayfaMeterial.this, R.drawable.button_music);


        gv = (GridView) findViewById(R.id.gridViewAnasayfa);
        gv.setAdapter(new AnasayfaMeterialAdapter(this, Sayfalar, SayfaImages));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(AnasayfaMeterial.this, Haberler.class);
                    startActivity(intent);
                    btnSound.start();

                }
                if(position==1){
                    Intent intent = new Intent(AnasayfaMeterial.this,Takimlar.class);
                    startActivity(intent);
                    btnSound.start();

                }
                if(position==2){
                    Intent intent = new Intent(AnasayfaMeterial.this,Ligler_Grid.class);
                    startActivity(intent);
                    btnSound.start();
 ;
                }
                if(position==3){
                    Intent intent = new Intent(AnasayfaMeterial.this, VideoHome.class);
                    startActivity(intent);
                    btnSound.start();

                }
                if(position==4){
                    Intent intent = new Intent(AnasayfaMeterial.this, OyuncuKDA.class);
                    startActivity(intent);
                    btnSound.start();

                }
                if(position==5){
                    Intent intent = new Intent(AnasayfaMeterial.this, MacFiksturu.class);
                    startActivity(intent);
                    btnSound.start();

                }

            }
        });
    }

    //Back Tusuna Basinca Uygulama Kapatma Ekrani
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AnasayfaMeterial.this);
        alertDialog.setMessage("Uygulama Sonlandirilsin mi?");
        alertDialog.setCancelable(false).setPositiveButton("Evet",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        System.exit(0);

                    }
                }).setNegativeButton("Hayir",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.cancel();

                    }
                });

        AlertDialog alert = alertDialog.create();
        alert.show();

    }
    private boolean checkMyConnection(){//Net Kontrolu

        myConnectivityManager =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        myNetworkInfo = myConnectivityManager.getActiveNetworkInfo();

        if (myNetworkInfo != null && myNetworkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.optionsMenu = menu;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_item, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Action Bar ogelerindeki basilmalari idare edelim
        switch (item.getItemId()) {
            case R.id.action_bar2:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void getOverflowMenu() {

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}