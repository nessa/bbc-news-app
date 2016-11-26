package com.example.noeliasales.bbcnews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.example.noeliasales.bbcnews.fragments.NewsItemDetailFragment;
import com.example.noeliasales.bbcnews.R;
import com.example.noeliasales.bbcnews.models.News;
import com.example.noeliasales.bbcnews.models.NewsItem;
import com.example.noeliasales.bbcnews.helpers.NewsApi;
import com.example.noeliasales.bbcnews.helpers.RetrofitServiceGenerator;
import com.example.noeliasales.bbcnews.adapters.NewsListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private NewsListAdapter newsListAdapter;

    private ArrayList<NewsItem> mNewsItems = new ArrayList<>();


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.new_list)
    RecyclerView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        ButterKnife.bind(this);

        setup();
        getData();

        if (findViewById(R.id.new_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setup() {
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.news_list));
        }


        newsListAdapter = new NewsListAdapter(this);
        newsListView.setAdapter(newsListAdapter);
    }

    private void getData() {
        NewsApi api = RetrofitServiceGenerator.createService(NewsApi.class);
        Call<News> call = api.get();

        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    News news = response.body();

                    mNewsItems = new ArrayList<>(news.channel.newsItems);
                    newsListAdapter.notifyDataSetChanged();
                } else {
                    Log.d("ERROR", "on RESPONSE");
                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("ERROR", "on FAILURE");
            }
        });
    }

    public List<NewsItem> getItems() {
        return mNewsItems;
    }

    public void selectItem(int position) {

        NewsItem item = mNewsItems.get(position);

        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(NewsItemDetailActivity.NEWS_ITEM_TITLE, item.title);
            arguments.putString(NewsItemDetailActivity.NEWS_ITEM_WEB_URL, item.link);
            NewsItemDetailFragment fragment = new NewsItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.new_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, NewsItemDetailActivity.class);
            intent.putExtra(NewsItemDetailActivity.NEWS_ITEM_TITLE, item.title);
            intent.putExtra(NewsItemDetailActivity.NEWS_ITEM_WEB_URL, item.link);

            this.startActivity(intent);
        }
    }
}
