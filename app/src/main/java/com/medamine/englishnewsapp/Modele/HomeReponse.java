package com.medamine.englishnewsapp.Modele;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amine.
 */
public class HomeReponse {

    @SerializedName("articles")
    @Expose
    private List<News> news;

    public List<News> getHome(){
        return news;
    }
    public void setHome(List<News> news){
        this.news = news;
    }
}
