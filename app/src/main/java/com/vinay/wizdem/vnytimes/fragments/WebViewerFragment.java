package com.vinay.wizdem.vnytimes.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vinay.wizdem.vnytimes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebViewerFragment extends Fragment {

    private WebView mWebView;
    public WebViewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String url = getArguments().getString("url");
        View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
        mWebView = (WebView)rootView.findViewById(R.id.web_viewer);
        mWebView.loadUrl(url);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());

        return rootView;
    }

}
