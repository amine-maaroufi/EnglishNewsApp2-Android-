package com.medamine.englishnewsapp.Fragment;


import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.medamine.englishnewsapp.Adapter;
import com.medamine.englishnewsapp.Api.Client;
import com.medamine.englishnewsapp.Api.Service;
import com.medamine.englishnewsapp.Api.ServiceLatestNews;
import com.medamine.englishnewsapp.Modele.EconomicReponse;
import com.medamine.englishnewsapp.Modele.HomeReponse;
import com.medamine.englishnewsapp.Modele.MusicReponse;
import com.medamine.englishnewsapp.Modele.News;
import com.medamine.englishnewsapp.Modele.PoliticReponse;
import com.medamine.englishnewsapp.Modele.ScienceReponse;
import com.medamine.englishnewsapp.Modele.SportReponse;
import com.medamine.englishnewsapp.Modele.TechnologyReponse;
import com.medamine.englishnewsapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Amine on 21/07/2017.
 */
public class HomeFragment extends Fragment {

    private RecyclerView recyclerPolitic,recyclerEconomic,recyclerSport,recyclerTechnology,recyclerScience,recyclerMusic;
    private Adapter adapter;
    private List<News> newsList;
    private News album;
    ProgressDialog pd;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, null);


        initViews(rootView);
        //initCollapsingToolbar(rootView);

        return rootView;
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
   public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void initViews(View view){
        pd = new ProgressDialog(getContext());
        pd.setMessage("Fetching Data...");
        pd.setCancelable(false);
        pd.show();

       /* try {
            Glide.with(this).load(R.drawable.coverpolitic).into((ImageView) view.findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        //Recycler View for politic news
        recyclerPolitic = (RecyclerView) view.findViewById(R.id.recycler_politic);
        newsList = new ArrayList<>();
        adapter = new Adapter(getContext(), newsList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerPolitic.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //recyclerPolitic.setLayoutManager(mLayoutManager);
        recyclerPolitic.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerPolitic.setItemAnimator(new DefaultItemAnimator());
        recyclerPolitic.setAdapter(adapter);


        //Recycler View for economic news
        recyclerEconomic = (RecyclerView) view.findViewById(R.id.recycler_economic);
        newsList = new ArrayList<>();
        adapter = new Adapter(getContext(), newsList);
        RecyclerView.LayoutManager mLayoutManagerEconomic = new GridLayoutManager(getContext(), 2);
        recyclerEconomic.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //recyclerEconomic.setLayoutManager(mLayoutManagerEconomic);
        recyclerEconomic.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerEconomic.setItemAnimator(new DefaultItemAnimator());
        recyclerEconomic.setAdapter(adapter);

        //Recycler View for sport news
        recyclerSport = (RecyclerView) view.findViewById(R.id.recycler_sport);
        newsList = new ArrayList<>();
        adapter = new Adapter(getContext(), newsList);
        RecyclerView.LayoutManager mLayoutManagerSport = new GridLayoutManager(getContext(), 2);
        recyclerSport.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //recyclerEconomic.setLayoutManager(mLayoutManagerSport);
        recyclerSport.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerSport.setItemAnimator(new DefaultItemAnimator());
        recyclerSport.setAdapter(adapter);


        //Recycler View for technology news
        recyclerTechnology = (RecyclerView) view.findViewById(R.id.recycler_technology);
        newsList = new ArrayList<>();
        adapter = new Adapter(getContext(), newsList);
        RecyclerView.LayoutManager mLayoutManagerTechnology = new GridLayoutManager(getContext(), 2);
        recyclerTechnology.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //recyclerEconomic.setLayoutManager(mLayoutManagerTechnology);
        recyclerTechnology.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerTechnology.setItemAnimator(new DefaultItemAnimator());
        recyclerTechnology.setAdapter(adapter);

        //Recycler View for science news
        recyclerScience = (RecyclerView) view.findViewById(R.id.recycler_science);
        newsList = new ArrayList<>();
        adapter = new Adapter(getContext(), newsList);
        RecyclerView.LayoutManager mLayoutManagerScience = new GridLayoutManager(getContext(), 2);
        recyclerScience.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //recyclerEconomic.setLayoutManager(mLayoutManagerScience);
        recyclerScience.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerScience.setItemAnimator(new DefaultItemAnimator());
        recyclerScience.setAdapter(adapter);

        //Recycler View for music news
        recyclerMusic = (RecyclerView) view.findViewById(R.id.recycler_Music);
        newsList = new ArrayList<>();
        adapter = new Adapter(getContext(), newsList);
        RecyclerView.LayoutManager mLayoutManagerMusic = new GridLayoutManager(getContext(), 2);
        recyclerMusic.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //recyclerEconomic.setLayoutManager(mLayoutManagerMusic);
        recyclerMusic.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerMusic.setItemAnimator(new DefaultItemAnimator());
        recyclerMusic.setAdapter(adapter);

        loadJSON();
        loadJSONEconomic();
        loadJSONSport();
        loadJSONTechnology();
        loadJSONMusic();
        loadJSONScience();
    }

    private void loadJSON(){
        try{
            Client Client = new Client();
            ServiceLatestNews apiService =
                    Client.getClient().create(ServiceLatestNews.class);
            Call<PoliticReponse> call = apiService.getAlbums();
            call.enqueue(new Callback<PoliticReponse>() {
                @Override
                public void onResponse(Call<PoliticReponse> call, Response<PoliticReponse> response) {
                    List<News> items = response.body().getAlbums();

                    recyclerPolitic.setAdapter(new Adapter(getContext(), items));
                    recyclerPolitic.smoothScrollToPosition(0);

                    pd.hide();
                }

                @Override
                public void onFailure(Call<PoliticReponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getContext(), "Connection Not Available !", Toast.LENGTH_SHORT).show();
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void loadJSONEconomic(){
        try{

            Client ClientEconomic = new Client();
            ServiceLatestNews apiServiceEconomic =
                    ClientEconomic.getClient().create(ServiceLatestNews.class);
            Call<EconomicReponse> call = apiServiceEconomic.getEconomic();
            call.enqueue(new Callback<EconomicReponse>() {
                @Override
                public void onResponse(Call<EconomicReponse> call, Response<EconomicReponse> response) {
                    List<News> items = response.body().getEconomic();

                    recyclerEconomic.setAdapter(new Adapter(getContext(), items));
                    recyclerEconomic.smoothScrollToPosition(0);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<EconomicReponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getContext(), "Connection Not Available !", Toast.LENGTH_SHORT).show();
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }




    private void loadJSONSport(){
        try{

            Client ClientSport = new Client();
            ServiceLatestNews apiServiceSport =
                    ClientSport.getClient().create(ServiceLatestNews.class);
            Call<SportReponse> call = apiServiceSport.getSport();
            call.enqueue(new Callback<SportReponse>() {
                @Override
                public void onResponse(Call<SportReponse> call, Response<SportReponse> response) {
                    List<News> items = response.body().getSport();

                    recyclerSport.setAdapter(new Adapter(getContext(), items));
                    recyclerSport.smoothScrollToPosition(0);
                    pd.hide();
                }

                @Override
                public void onFailure(Call<SportReponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getContext(), "Connection Not Available !", Toast.LENGTH_SHORT).show();
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }



    private void loadJSONTechnology(){
        try{
            Client ClientTechnology = new Client();
            ServiceLatestNews apiServiceTechnology =
                    ClientTechnology.getClient().create(ServiceLatestNews.class);
            Call<TechnologyReponse> call = apiServiceTechnology.getTechnology();
            call.enqueue(new Callback<TechnologyReponse>() {
                @Override
                public void onResponse(Call<TechnologyReponse> call, Response<TechnologyReponse> response) {
                    List<News> items = response.body().getTechnology();

                    recyclerTechnology.setAdapter(new Adapter(getContext(), items));
                    recyclerTechnology.smoothScrollToPosition(0);

                    pd.hide();
                }

                @Override
                public void onFailure(Call<TechnologyReponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getContext(), "Connection Not Available !", Toast.LENGTH_SHORT).show();
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }



    private void loadJSONScience(){
        try{
            Client ClientScience = new Client();
            ServiceLatestNews apiServiceScience =
                    ClientScience.getClient().create(ServiceLatestNews.class);
            Call<ScienceReponse> call = apiServiceScience.getScience();
            call.enqueue(new Callback<ScienceReponse>() {
                @Override
                public void onResponse(Call<ScienceReponse> call, Response<ScienceReponse> response) {
                    List<News> items = response.body().getScience();

                    recyclerScience.setAdapter(new Adapter(getContext(), items));
                    recyclerScience.smoothScrollToPosition(0);

                    pd.hide();
                }

                @Override
                public void onFailure(Call<ScienceReponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getContext(), "Connection Not Available !", Toast.LENGTH_SHORT).show();
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }




    private void loadJSONMusic(){
        try{
            Client ClientMusic = new Client();
            ServiceLatestNews apiServiceMusic =
                    ClientMusic.getClient().create(ServiceLatestNews.class);
            Call<MusicReponse> call = apiServiceMusic.getMusic();
            call.enqueue(new Callback<MusicReponse>() {
                @Override
                public void onResponse(Call<MusicReponse> call, Response<MusicReponse> response) {
                    List<News> items = response.body().getMusic();

                    recyclerMusic.setAdapter(new Adapter(getContext(), items));
                    recyclerMusic.smoothScrollToPosition(0);

                    pd.hide();
                }

                @Override
                public void onFailure(Call<MusicReponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(getContext(), "Connection Not Available !", Toast.LENGTH_SHORT).show();
                    pd.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}