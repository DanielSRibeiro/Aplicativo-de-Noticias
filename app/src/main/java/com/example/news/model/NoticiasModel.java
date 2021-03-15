package com.example.news.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NoticiasModel {

    @SerializedName("totalResults")
    private Integer totalResults;

    @SerializedName("articles")
    private List<Noticias> articles;

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Noticias> getArticles() {
        return articles;
    }

    public void setArticles(List<Noticias> articles) {
        this.articles = articles;
    }
}
