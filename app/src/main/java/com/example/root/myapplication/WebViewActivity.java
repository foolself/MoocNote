package com.example.root.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by root on 16-3-21.
 */
public class WebViewActivity extends Activity {
    private WebView webView;
    private EditText editText;
    private Button subimt;
    private String url;

    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        // browser url with the third app
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);

        // browser url with the WebView
        init();
    }

    private void init() {
        webView = (WebView) findViewById(R.id.myWebView);
        editText = (EditText) findViewById(R.id.input);
        subimt = (Button) findViewById(R.id.submit);

        subimt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                url = editText.getText().toString();
                webView.loadUrl(url);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        // use JavaScript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // use cache mode
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // show the load progress
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.i("tag", newProgress + "");
//                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    closeDialog();
                } else {
                    openDialog(newProgress);
                }
            }

            private void openDialog(int newProgress) {
                if (dialog == null) {
                    Log.i("tag", "new dialog");
                    dialog = new ProgressDialog(WebViewActivity.this);
                    dialog.setTitle("loading...");
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    dialog.setProgress(newProgress);
                    dialog.show();
                }
                else {
                    Log.i("tag", "update progress");
                    dialog.setProgress(newProgress);
                }
            }

            private void closeDialog() {
                Log.i("tag", "drop dialog");
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }
        });

    }

    // rewrite logic of back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Toast.makeText(WebViewActivity.this, webView.getUrl(), Toast.LENGTH_SHORT).show();
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
            else {
                System.exit(0);
            }
        }
        return true;
    }
}
