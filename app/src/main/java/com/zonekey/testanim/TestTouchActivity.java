package com.zonekey.testanim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;

/**
 * Created by xu.wang
 * Date on  2018/1/11 11:00:44.
 *
 * @Desc
 */

public class TestTouchActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_touch);
        initialView();
    }

    private void initialView() {
        scrollView = findViewById(R.id.sv_test);
        webView = findViewById(R.id.web_view_test);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
