package com.medamine.englishnewsapp.Modele;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amine.
 */
public class MusicReponse {

    @SerializedName("articles")
    @Expose
    private List<News> news;

    public List<News> getMusic(){
        return news;
    }
    public void setMusic(List<News> albums){
        this.news = albums;
    }
}
