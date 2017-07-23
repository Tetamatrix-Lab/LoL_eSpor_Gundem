package com.erdem.kurumi.volley;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.erdem.kurumi.volley.R;

public class MacFiksturu extends ActionBarActivity {
    WebView wb;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        wb = (WebView) findViewById(R.id.webView2);
        wb.loadUrl("http://www.lolespor.com/");
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setDisplayZoomControls(false);
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
