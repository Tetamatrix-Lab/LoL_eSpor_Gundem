package com.erdem.kurumi.volley;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.GridView;

import com.erdem.kurumi.volley.Ligler.AllStar;
import com.erdem.kurumi.volley.Ligler.ChinaLig;
import com.erdem.kurumi.volley.Ligler.EUChallanger;
import com.erdem.kurumi.volley.Ligler.EuLcs;
import com.erdem.kurumi.volley.Ligler.KoreaLig;
import com.erdem.kurumi.volley.Ligler.LigPuanDurumu;
import com.erdem.kurumi.volley.Ligler.NAChallanger;
import com.erdem.kurumi.volley.Ligler.NaLcs;
import com.erdem.kurumi.volley.Ligler.Taiwand;
import com.erdem.kurumi.volley.Ligler.WildCard;
import com.erdem.kurumi.volley.Ligler.YukselmePuan;
import com.erdem.kurumi.volley.adater.CustomGridAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
public class Ligler_Grid extends ActionBarActivity {
    ActionBar actionBar;
    GridView gv;
    Context context;
    ArrayList prgmName;
    private Menu optionsMenu;
    public static String[] ligNameList = {"Şampiyonluk Ligi", "Yükselme Ligi", "Wildcard", "Na Lcs", "Europe Lcs", "Lms Taiwan",
            "Lpl China", "All-Star", "Champions Korea", "Na Challeanger", "Eu Challanger"};
    public static int[] ligImages = {R.drawable.tr, R.drawable.yukselme, R.drawable.wild, R.drawable.nalcs, R.drawable.eulcs, R.drawable.taiwan,
            R.drawable.china, R.drawable.allstar, R.drawable.korea, R.drawable.logo_challangerna, R.drawable.logo_challangereu};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligler__grid);
        actionBar = getActionBar();
        getSupportActionBar().hide();
        gv = (GridView) findViewById(R.id.gridView);
        gv.setAdapter(new CustomGridAdapter(this, ligNameList, ligImages));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ecf0f1")));
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(Ligler_Grid.this, LigPuanDurumu.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(Ligler_Grid.this, YukselmePuan.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(Ligler_Grid.this, WildCard.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(Ligler_Grid.this, NaLcs.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(Ligler_Grid.this, EuLcs.class);
                    startActivity(intent);
                }
                if (position == 5) {
                    Intent intent = new Intent(Ligler_Grid.this, Taiwand.class);
                    startActivity(intent);
                }
                if (position == 6) {
                    Intent intent = new Intent(Ligler_Grid.this, ChinaLig.class);
                    startActivity(intent);
                }
                if (position == 7) {
                    Intent intent = new Intent(Ligler_Grid.this, AllStar.class);
                    startActivity(intent);
                }
                if (position == 8) {
                    Intent intent = new Intent(Ligler_Grid.this, KoreaLig.class);
                    startActivity(intent);
                }
                if (position == 9) {
                    Intent intent = new Intent(Ligler_Grid.this, NAChallanger.class);
                    startActivity(intent);
                }
                if (position == 10) {
                    Intent intent = new Intent(Ligler_Grid.this, EUChallanger.class);
                    startActivity(intent);
                }
            }
        });
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
