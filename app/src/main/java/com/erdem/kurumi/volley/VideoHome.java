package com.erdem.kurumi.volley;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.erdem.kurumi.volley.KisMevsimi.KisMevsimi;
import com.erdem.kurumi.volley.MSI.MSIMevsimi;
import com.erdem.kurumi.volley.SampiyonlukLigiOzet.YouTubeActivity;
import com.erdem.kurumi.volley.SampiyonlukLigiOzet.YouTubeFragment;
import com.erdem.kurumi.volley.TBF.TBFMevsimi;
import com.erdem.kurumi.volley.UluslararasiTurnuva.UluslararasiFragment;
import com.erdem.kurumi.volley.UluslararasiTurnuva.UluslararasiMevsimi;
import com.erdem.kurumi.volley.Wildcard.WildcardMevsimi;
import com.erdem.kurumi.volley.YukselmeLigi.YukselmeMevsimi;

public class VideoHome extends ActionBarActivity {
    ActionBar actionBar;
    ListView videolist;
    private String[] videolar={"Şampiyonluk Ligi 2015","Kış Mevsim Finalleri 2015",
            "Yukselme Ligi Finalleri 2015","Uluslar Arası Özel Turunuva","MSI 2015"
            ,"International Wildcard Invitational","Türkiye Büyük Finali 2014"};
    YouTubeFragment yt=new YouTubeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_home);
        videolist=(ListView)findViewById(R.id.VideoListesi);
        actionBar = getActionBar();
        getSupportActionBar().hide();
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ecf0f1")));
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,android.R.id.text1,videolar);
        videolist.setAdapter(arrayAdapter);
        videolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(VideoHome.this,YouTubeActivity.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Intent intent = new Intent(VideoHome.this, KisMevsimi.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent intent = new Intent(VideoHome.this, YukselmeMevsimi.class);
                    startActivity(intent);
                }
                if (position == 3) {
                    Intent intent = new Intent(VideoHome.this, UluslararasiMevsimi.class);
                    startActivity(intent);
                }
                if (position == 4) {
                    Intent intent = new Intent(VideoHome.this, MSIMevsimi.class);
                    startActivity(intent);
                }
                if (position == 5) {
                    Intent intent = new Intent(VideoHome.this, WildcardMevsimi.class);
                    startActivity(intent);
                }
                if (position == 6) {
                    Intent intent = new Intent(VideoHome.this, TBFMevsimi.class);
                    startActivity(intent);
                }

            }
        });


    }

}
