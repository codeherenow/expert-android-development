package com.codeherenow.butterknife;

import android.app.ListActivity;
import android.os.Bundle;

import com.codeherenow.butterknife.adapter.NewsArticleAdapter;
import com.codeherenow.butterknife.model.NewsArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * This example illustrates the usage of {@link butterknife.InjectView}
 * annotation in {@link android.widget.ListAdapter}s.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class NewsActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Set Adapter for ListView
        NewsArticleAdapter newsArticleAdapter = new NewsArticleAdapter(this, getSampleNews());
        getListView().setAdapter(newsArticleAdapter);
    }

    private List<NewsArticle> getSampleNews() {
        List<NewsArticle> newsArticles = new ArrayList<>();
        newsArticles.add(new NewsArticle("Binoy Valsan", "India",
            "ISRO successfully test-fires GSLV Mark III carrying unmanned crew module", "987",
            "isro.com", "09.39 AM IST"));

        newsArticles.add(new NewsArticle("Saurabh Sinha", "India Business",
            "SpiceJet cancellations ground holiday plans", "655",
            "spicejet.com", "03.08AM IST"));

        newsArticles.add(new NewsArticle("TNN", "Top News",
            "3 H1N1 deaths in Hyderabad lead to epidemic fears", "137",
            "swineflu.com", "12.46 AM IST"));

        newsArticles.add(new NewsArticle("TNN", "India",
            "BJP pulls out Nehru letter seeking a horoscope for Rajiv Gandhi", "400",
            "indiatimes.com", "01.32 AM IST"));

        newsArticles.add(new NewsArticle("Swati Deshpande", "India",
            "Supreme Court refuses to interfere with Bombay High Court order", "59",
            "isro.com", "12.29 PM IST"));

        return newsArticles;
    }

}
