package com.codeherenow.butterknife.model;

/**
 * This model class is part of the adapter view injection example.
 * @see com.codeherenow.butterknife.adapter.NewsArticleAdapter
 *
 * @author Ramanan Vijayakumar <www.codeherenow.com>
 */
public class NewsArticle {

    public String author;
    public String category;
    public String title;
    public String commentsCount;
    public String website;
    public String displayTime;

    public NewsArticle(String author, String category,
            String title, String commentsCount,
            String website, String displayTime) {
        this.author = author;
        this.category = category;
        this.title = title;
        this.commentsCount = commentsCount;
        this.website = website;
        this.displayTime = displayTime;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
            "author='" + author + '\'' +
            ", category='" + category + '\'' +
            ", title='" + title + '\'' +
            ", commentsCount='" + commentsCount + '\'' +
            ", website='" + website + '\'' +
            ", displayTime='" + displayTime + '\'' +
            '}';
    }
}
