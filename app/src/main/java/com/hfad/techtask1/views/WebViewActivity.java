package com.hfad.techtask1.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.hfad.techtask1.R;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.web_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("url");
            if(webView.getParent() != null) webView.removeView(webView);
            webView.loadUrl(value);
            setContentView(webView);
        }
        else{
            webView.loadUrl("https://www.google.com/");
        }
    }
}