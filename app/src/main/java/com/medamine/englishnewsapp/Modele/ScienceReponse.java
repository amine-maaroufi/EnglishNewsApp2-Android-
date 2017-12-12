package com.medamine.englishnewsapp.Modele;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amine.
 */
public class ScienceReponse {

    @SerializedName("articles")
    @Expose
    private List<News> news;

    public List<News> getScience(){
        return news;
    }
    public void setScience(List<News> nature){
        this.news = nature;
    }
}
