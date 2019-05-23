package com.example.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText editText;
    private Button searchButton;
    private Button clearButton;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        searchButton = findViewById(R.id.button);
        clearButton = findViewById(R.id.clear_button);
        webView = findViewById(R.id.webView);

        searchButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                editText.setText(url);
            }
        });

        webView.loadUrl("http://google.com");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                handleSearchButton();
                break;
            case R.id.clear_button:
                handleClearButton();
                break;
        }
    }

    private void handleSearchButton() {
        String url = editText.getText() + "";
        url = url.toLowerCase();
        if (url.startsWith("http") == false) {
            url = "http://" + url;
        }
        webView.loadUrl(url.trim());

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private void handleClearButton() {
        editText.setText("");
    }
}