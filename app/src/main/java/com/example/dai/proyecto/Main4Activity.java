package com.example.dai.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.EditText;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        String url ="http://tienda.itam.mx";
        WebView wv =(WebView)findViewById(R.id.wvWebView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }
}
