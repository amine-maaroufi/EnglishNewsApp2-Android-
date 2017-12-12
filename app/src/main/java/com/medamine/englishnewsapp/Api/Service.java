package com.medamine.englishnewsapp.Api;

import com.medamine.englishnewsapp.Modele.EconomicReponse;
import com.medamine.englishnewsapp.Modele.HomeReponse;
import com.medamine.englishnewsapp.Modele.MusicReponse;
import com.medamine.englishnewsapp.Modele.PoliticReponse;
import com.medamine.englishnewsapp.Modele.ScienceReponse;
import com.medamine.englishnewsapp.Modele.SportReponse;
import com.medamine.englishnewsapp.Modele.TechnologyReponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Amine on 20/07/2017.
 */
public interface Service {
    //service politic
    @GET("articles?source=al-jazeera-english&sortBy=top&apiKey=11ae53c5feaa46bd94fcc23b84a915e2")
    Call<PoliticReponse> getAlbums();

    //service economic
    @GET("articles?source=the-economist&sortBy=top&apiKey=11ae53c5feaa46bd94fcc23b84a915e2")
    Call<EconomicReponse> getEconomic();

    //service sport
    @GET("articles?source=bbc-sport&sortBy=top&apiKey=11ae53c5feaa46bd94fcc23b84a915e2")
    Call<SportReponse> getSport();

    //service technology
    @GET("articles?source=ars-technica&sortBy=top&apiKey=11ae53c5feaa46bd94fcc23b84a915e2")
    Call<TechnologyReponse> getTechnology();

    //service home
    @GET("articles?source=al-jazeera-english&sortBy=latest&apiKey=11ae53c5feaa46bd94fcc23b84a915e2")
    Call<HomeReponse> getHome();

    //service science
    @GET("articles?source=national-geographic&sortBy=top&apiKey=11ae53c5feaa46bd94fcc23b84a915e2")
    Call<ScienceReponse> getScience();

    //service music
    @GET("articles?source=mtv-news&sortBy=top&apiKey=11ae53c5feaa46bd94fcc23b84a915e2")
    Call<MusicReponse> getMusic();




}
