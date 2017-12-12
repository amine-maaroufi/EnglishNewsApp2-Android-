package com.medamine.englishnewsapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.medamine.englishnewsapp.R;


/**
 * Created by amine on 21/07/2017.
 */

public class WebviewFragment extends android.support.v4.app.Fragment  {

    WebView mWebView;
    public static WebviewFragment newInstance() {
        return new WebviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.web_view, null);

        mWebView = (WebView) rootView.findViewById(R.id.webview);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //mWebView.loadUrl("http://beta.html5test.com/");
        Bundle bundle = this.getArguments();
        String str_url = bundle.getString("url");
        mWebView.loadUrl(str_url);
        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return rootView;
    }

}