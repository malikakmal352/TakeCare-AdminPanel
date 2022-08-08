package com.example.takecareadminpanels;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Rider_Admin extends AppCompatActivity {
    private WebView webView;
    private ProgressBar spinner;
    BroadcastReceiver broadcastReceiver;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_rider_admin);
        getSupportActionBar ().hide ( );

        //////////////////////check internet connection start-1//////////////////////////
        broadcastReceiver = new ConnectionReceiver ();
        register ();

        //////////////////////check internet connection end-1//////////////////////////

        webView = findViewById (R.id.webs);
        spinner = (ProgressBar) findViewById (R.id.progressBar1);

        WebSettings webSettings = webView.getSettings ( );
        webSettings.setJavaScriptEnabled (true);
        webView.setWebViewClient (new Rider_Admin.callBack ( ));

        webView.loadUrl ("http://takecaresystem.pythonanywhere.com/Rider_Login/");
    }

    //////////////////////check internet connection start-2//////////////////////////

    public void register(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter (ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    //////////////////////check internet connection end-2//////////////////////////

    public class callBack extends WebViewClient {
        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            webview.setVisibility(WebView.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            spinner.setVisibility(View.GONE);

            view.setVisibility(WebView.VISIBLE);
            super.onPageFinished(view, url);

        }
        public boolean shouldOverrideUrlLoading(WebView view, KeyEvent event) {
            return false;
    }
    }
}