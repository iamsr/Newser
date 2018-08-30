package com.example.shubhamr.newser.Views.Activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.shubhamr.newser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDisplayActivity extends AppCompatActivity {

    @BindView(R.id.newsWebView)WebView newsWebView;
    @BindView(R.id.newsProgressBar)ProgressBar newsProgressBar;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        //Get url
        URL = getIntent().getStringExtra("URL");


        // Render page in web view
        loadPage(URL);

    }

    public void loadPage(String url){

        /*
           Setting up WebViewClient so we can add progress bar when
           page is loading and hide progress bar when page load successfully

         */

        newsWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url,Bitmap bitmap){

                // Visible the progressbar
                newsProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                // Hide progress bar when page loading finished
               newsProgressBar.setVisibility(View.GONE);
            }

        });

        // Enable javascript
        newsWebView.getSettings().setJavaScriptEnabled(true);

        // Load URL into web view
        newsWebView.loadUrl(URL);

    }

}
