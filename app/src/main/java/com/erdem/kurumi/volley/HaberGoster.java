package com.erdem.kurumi.volley;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.erdem.kurumi.volley.R;

public class HaberGoster extends ActionBarActivity {
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haber_goster);
        String url = getIntent().getStringExtra("haberlinki");
        wb=(WebView)findViewById(R.id.webView);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.loadUrl(url);
        final ProgressDialog progress = ProgressDialog.show(this, "Sayfa", "Yukleniyor....", true);
        progress.setCancelable(true);
        progress.show();
        wb.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //Toast.makeText(getApplicationContext(), "Sayfa yuklendi", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Bir hata olustu", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

    }

}
