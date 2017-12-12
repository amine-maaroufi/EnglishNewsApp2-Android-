package com.medamine.englishnewsapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.medamine.englishnewsapp.Fragment.DetailFragment;
import com.medamine.englishnewsapp.Modele.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Amine
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<News> albumList;

    public Adapter(Context mContext, List<News> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.design_card_view, viewGroup, false);

        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, int i) {
        viewHolder.title.setText(albumList.get(i).getName());
        viewHolder.count.setText(albumList.get(i).getDate());
        viewHolder.description.setText (albumList.get(i).getDescription());
        viewHolder.url.setText (albumList.get(i).getUrl());


        //load album cover using picasso
        Picasso.with(mContext)
                .load(albumList.get(i).getThumbnail())
                .placeholder(R.drawable.load)
                .into(viewHolder.thumbnail);

        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(viewHolder.overflow);
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_card_view, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                   // Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                   // Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title, count, description,url;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.date);
            description = (TextView) view.findViewById(R.id.desc);
            url = (TextView) view.findViewById(R.id.link);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
            itemView.setOnClickListener(this);

            //on item click
           /* itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        News clickedDataItem = albumList.get(pos);
                        // Create fragment and give it an argument for the selected article
                        DetailFragment newFragment = new DetailFragment();
                        Bundle args = new Bundle();
                        args.putString("title", albumList.get(pos).getName());
                        args.putString("date", albumList.get(pos).getDate());
                        args.putString("thumbnail", albumList.get(pos).getThumbnail());
                        args.putString("description", albumList.get(pos).getDescription());
                        args.putString("url", albumList.get(pos).getUrl());


                        newFragment.setArguments(args);
                        FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                        // Replace whatever is in the fragment_container view with this fragment,
                        // and add the transaction to the back stack so the user can navigate back
                        transaction.replace(R.id.containerID, newFragment);
                        transaction.addToBackStack(null);

                        // Commit the transaction
                        transaction.commit();

                    }
                }
            });*/

            thumbnail.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.thumbnail || itemView.isClickable()) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    News clickedDataItem = albumList.get(pos);
                    // Create fragment and give it an argument for the selected article
                    DetailFragment newFragment = new DetailFragment();
                    Bundle args = new Bundle();
                    args.putString("title", albumList.get(pos).getName());
                    args.putString("date", albumList.get(pos).getDate());
                    args.putString("thumbnail", albumList.get(pos).getThumbnail());
                    args.putString("description", albumList.get(pos).getDescription());
                    args.putString("url", albumList.get(pos).getUrl());


                    newFragment.setArguments(args);
                    FragmentTransaction transaction = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
                    // Replace whatever is in the fragment_container view with this fragment,
                    // and add the transaction to the back stack so the user can navigate back
                    transaction.replace(R.id.containerID, newFragment);
                    transaction.addToBackStack(null);

                    // Commit the transaction
                    transaction.commit();
                }
            }
        }
    }
}
