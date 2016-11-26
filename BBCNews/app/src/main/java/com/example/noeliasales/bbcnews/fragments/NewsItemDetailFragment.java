package com.example.noeliasales.bbcnews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.noeliasales.bbcnews.activities.NewsItemDetailActivity;
import com.example.noeliasales.bbcnews.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsItemDetailFragment extends Fragment {

    private String mURL;

    @BindView(R.id.web_view)
    WebView webView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    public NewsItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(NewsItemDetailActivity.NEWS_ITEM_WEB_URL)) {
            mURL = getArguments().getString(NewsItemDetailActivity.NEWS_ITEM_WEB_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_item_detail, container, false);
        ButterKnife.bind(this, rootView);

        if (mURL != null) {
            //webView.getSettings().setJavaScriptEnabled(true);

            webView.setWebChromeClient(new WebChromeClient(){

                public void onProgressChanged(WebView view, int progress) {
                    progressBar.setProgress(progress);
                    if (progress == 100) {
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

            webView.loadUrl(mURL);
        } else {
            progressBar.setVisibility(View.GONE);
        }

        return rootView;
    }
}
