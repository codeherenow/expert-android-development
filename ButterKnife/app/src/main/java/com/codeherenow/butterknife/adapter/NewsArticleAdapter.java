package com.codeherenow.butterknife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codeherenow.butterknife.R;
import com.codeherenow.butterknife.model.NewsArticle;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * This example illustrates the usage of {@link butterknife.InjectView}
 * annotation in {@link android.widget.ListAdapter}s.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class NewsArticleAdapter extends ArrayAdapter<NewsArticle> {

    private List<NewsArticle> mNewsArticles;
    private LayoutInflater mInflater;

    public NewsArticleAdapter(Context context, List<NewsArticle> newsArticles) {
        super(context, R.layout.list_item_news_article, newsArticles);
        mNewsArticles = newsArticles;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        NewsArticle newsArticle = mNewsArticles.get(position);

        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = mInflater.inflate(R.layout.list_item_news_article, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        // Set properties
        viewHolder.mAuthorTextView.setText(newsArticle.author);
        viewHolder.mCategoryTextView.setText(newsArticle.category);
        viewHolder.mTitleTextView.setText(newsArticle.title);
        viewHolder.mCommentsCountTextView.setText(newsArticle.commentsCount);
        viewHolder.mWebsiteTextView.setText(newsArticle.website);
        viewHolder.mDisplayTimeTextView.setText(newsArticle.displayTime);

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.authorTextView)
        TextView mAuthorTextView;

        @InjectView(R.id.categoryTextView)
        TextView mCategoryTextView;

        @InjectView(R.id.titleTextView)
        TextView mTitleTextView;

        @InjectView(R.id.commentsCountTextView)
        TextView mCommentsCountTextView;

        @InjectView(R.id.websiteTextView)
        TextView mWebsiteTextView;

        @InjectView(R.id.displayTimeTextView)
        TextView mDisplayTimeTextView;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
