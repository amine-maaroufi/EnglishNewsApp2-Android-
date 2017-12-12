package com.medamine.englishnewsapp.Modele;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amine
 */
public class PoliticReponse {

    @SerializedName("articles")
    @Expose
    private List<News> albums;

    public List<News> getAlbums(){
        return albums;
    }
    public void setAlbums(List<News> albums){
        this.albums = albums;
    }
}
