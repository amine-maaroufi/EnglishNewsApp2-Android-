package com.medamine.englishnewsapp.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.medamine.englishnewsapp.MainActivity;
import com.medamine.englishnewsapp.R;
import com.bumptech.glide.Glide;


/**
 * Created by amine on 21/07/2017.
 */

public class DetailFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    TextView Title_detail, Description_detail,Date_detail,url_detail;
    private DrawerLayout drawer;
    Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, null);
         toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        drawer = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);


        /****************/

        Bundle bundle = this.getArguments();
        ImageView imageView = (ImageView) rootView.findViewById(R.id.thumbnail_image_header);
        Title_detail = (TextView) rootView.findViewById(R.id.title_detail);
        Description_detail = (TextView) rootView.findViewById(R.id.description_detail);
        Date_detail = (TextView) rootView.findViewById(R.id.date);
        url_detail = (TextView) rootView.findViewById(R.id.link_detail);

        String str_title = bundle.getString("title");
        String str_desc = bundle.getString("description");
        String thumbnail = bundle.getString("thumbnail");
        String str_date = bundle.getString("date");
        String str_url = bundle.getString("url");

        Title_detail.setText(str_title);
        Description_detail.setText(str_desc);
        Date_detail.setText(str_date);
        url_detail.setText(str_url);
        url_detail.setOnClickListener(this);

        //make link
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            url_detail.setText(Html.fromHtml(str_url,Html.FROM_HTML_MODE_LEGACY));
        } else {
            url_detail.setText(Html.fromHtml(str_url));
        }
        url_detail.setMovementMethod(LinkMovementMethod.getInstance());



        Glide.with(this)
                .load(thumbnail)
                .placeholder(R.drawable.load)
                .into(imageView);


        return rootView;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.link_detail){
            //send the link to webview


            String link = url_detail.toString();
            WebviewFragment newFragment = new WebviewFragment();
            Bundle args = new Bundle();
            args.putString("url", link);


            newFragment.setArguments(args);
            FragmentTransaction transaction = ((FragmentActivity) getContext()).getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.containerID, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                getActivity().onBackPressed();
                Toast.makeText(getContext(), "Back", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}