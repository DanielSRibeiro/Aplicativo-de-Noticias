package com.example.news.model;

import android.graphics.Bitmap;

public class New {

    private String author, title, description,
            publishedAt;
    private Bitmap urlToImage;

    @Override
    public String toString() {
        return "\nauthor: "+author+
                "\n title: "+title+
                "\n description: "+description+
                "\n publishedAt: "+publishedAt;
    }

    public Bitmap getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(Bitmap urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
