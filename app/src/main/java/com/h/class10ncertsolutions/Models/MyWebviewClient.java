package com.h.class10ncertsolutions.Models;

import android.app.Activity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* created by ashbatra in June 2019 */

public class MyWebviewClient extends WebViewClient {

    Activity activity;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }

    public MyWebviewClient(Activity activity) {
        this.activity = activity;
    }
}
