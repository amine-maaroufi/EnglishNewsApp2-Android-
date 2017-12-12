package com.medamine.englishnewsapp.Modele;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amine.
 */
public class SportReponse {

    @SerializedName("articles")
    @Expose
    private List<News> news;

    public List<News> getSport(){
        return news;
    }
    public void setSport(List<News> albums){
        this.news = albums;
    }
}
