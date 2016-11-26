package com.example.noeliasales.bbcnews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.noeliasales.bbcnews.activities.MainActivity;
import com.example.noeliasales.bbcnews.R;
import com.example.noeliasales.bbcnews.models.NewsItem;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListAdapter
        extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    private MainActivity context;
    private final SimpleDateFormat SDF;

    public NewsListAdapter(Context context) {
        this.context = (MainActivity) context;
        SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        NewsItem item = getItem(position);

        holder.mView.setTag(position);
        holder.mTitle.setText(item.title);
        holder.mDate.setText(SDF.format(item.date));
        holder.mDescription.setText(item.description);

        Glide.with(context)
                .load(item.thumbnail.url)
                .centerCrop()
                .into(holder.mImage);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsListAdapter.this.context.selectItem((int) v.getTag());
            }
        });
    }

    private NewsItem getItem(int position) {
        return this.context.getItems().get(position);
    }


    @Override
    public int getItemCount() {
        return this.context.getItems().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final View mView;

        @BindView(R.id.image)
        ImageView mImage;

        @BindView(R.id.date)
        TextView mDate;

        @BindView(R.id.title)
        TextView mTitle;

        @BindView(R.id.description)
        TextView mDescription;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mView = view;
        }
    }
}

