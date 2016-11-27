package com.example.noeliasales.bbcnews.activities;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.noeliasales.bbcnews.R;
import com.example.noeliasales.bbcnews.fragments.NewsItemDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsItemDetailActivity extends AppCompatActivity {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String NEWS_ITEM_TITLE = "news_item_title";
    public static final String NEWS_ITEM_WEB_URL = "news_item_web_url";
    public static final String NEWS_ITEM_IMAGE_URL = "news_item_image_url";

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.detail_image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item_detail);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (savedInstanceState == null) {

            String title = getIntent().getStringExtra(NEWS_ITEM_TITLE);
            if (title != null && title.length() > 0) {
                setTitle(title);
            }

            String imageURL = getIntent().getStringExtra(NEWS_ITEM_IMAGE_URL);
            if (imageURL != null && imageURL.length() > 0) {
                Glide.with(this)
                        .load(imageURL)
                        .into(imageView);
            }

            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(NEWS_ITEM_WEB_URL,
                    getIntent().getStringExtra(NEWS_ITEM_WEB_URL));
            NewsItemDetailFragment fragment = new NewsItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.news_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String title) {
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
