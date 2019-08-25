package com.invaders.saathi;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        int card = getIntent().getIntExtra("card", 1);
        WebView webView = findViewById(R.id.webview);

        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        String address;
        webView.setWebViewClient(new WebViewClient());
        switch (card) {
            case 1:
                address = "file:///android_asset/rights/rules1.html";
                break;
            case 2:
                address = "file:///android_asset/ngo/ngo.html";
                break;
            case 3:
                address = "file:///android_asset/schemes/schemes.html";
                break;
            case 4:
                address = "file:///android_asset/jobs/jobs.html";
                break;
            case 6:
                address = "http://www.swavlambancard.gov.in";
                break;
            case 9:
                address = "file:///android_asset/about.html";
                break;
            case 10:
                address = "file:///android_asset/contact.html";
                break;
            default:
                address = "";
        }
        webView.loadUrl(address);
    }

    private class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
