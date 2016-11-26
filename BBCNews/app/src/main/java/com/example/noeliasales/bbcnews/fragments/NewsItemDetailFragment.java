package com.example.noeliasales.bbcnews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.noeliasales.bbcnews.activities.NewsItemDetailActivity;
import com.example.noeliasales.bbcnews.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsItemDetailFragment extends Fragment {

    private String mURL;

    @BindView(R.id.web_view)
    WebView webView;

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
            webView.loadUrl(mURL);
        }

        return rootView;
    }
}
