package com.medamine.englishnewsapp.Modele;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amine.
 */
public class TechnologyReponse {

    @SerializedName("articles")
    @Expose
    private List<News> news;

    public List<News> getTechnology(){
        return news;
    }
    public void setTechnology(List<News> albums){
        this.news = albums;
    }
}
