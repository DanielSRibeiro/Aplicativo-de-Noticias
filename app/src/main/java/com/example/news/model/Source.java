package com.example.news.model;

import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
