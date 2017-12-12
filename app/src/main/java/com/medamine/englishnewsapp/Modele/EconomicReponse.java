package com.medamine.englishnewsapp.Modele;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Amine.
 */
public class EconomicReponse {

    @SerializedName("articles")
    @Expose
    private List<News> albums;

    public List<News> getEconomic(){
        return albums;
    }
    public void setAlbums(List<News> albums){
        this.albums = albums;
    }
}
