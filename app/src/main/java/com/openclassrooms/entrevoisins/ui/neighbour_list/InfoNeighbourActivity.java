package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.text.Normalizer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoNeighbourActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
    public static final String BUNDLE_NEIGHBOUR = "BUNDLE_NEIGHBOUR";
    long neighbour_id;
    Neighbour neighbour;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.name_field)
    TextView mTextView_name;
    @BindView(R.id.address_field)
    TextView mTextView_address;
    @BindView(R.id.phone_field)
    TextView mTextView_phone;
    @BindView(R.id.social_field)
    TextView mTextView_social;
    @BindView(R.id.about_me_field)
    TextView mTextView_about_me;
    @BindView(R.id.image_view_avatar)
    ImageView mImageView_avatar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.favorite_floating_button)
    FloatingActionButton mFavoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_neighbour);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        Intent intent = getIntent();
        neighbour_id = intent.getLongExtra(BUNDLE_NEIGHBOUR, 0);
        neighbour = mApiService.getNeighbour(neighbour_id);
        String avatarUrl = biggerAvatar(neighbour.getAvatarUrl());
        Glide.with(this).load(avatarUrl).into(mImageView_avatar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCollapsingToolbarLayout.setTitle(neighbour.getName());
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        mTextView_name.setText(neighbour.getName());
        mTextView_address.setText(neighbour.getAddress());
        mTextView_phone.setText(neighbour.getPhoneNumber());
        mTextView_about_me.setText(neighbour.getAboutMe());
        mTextView_social.setText(getFacebookLink(stripAccents(neighbour.getName().toLowerCase())));
        init();
    }

    private void init() {
        mFavoriteButton.setSelected(neighbour.getFavorite());
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.setFavoriteById(neighbour_id,!neighbour.getFavorite());
                mFavoriteButton.setSelected(neighbour.getFavorite());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public String getFacebookLink (String s) {
        return ("www.facebook.fr/" + s);
    }

    public String biggerAvatar(String avatarUrl) {
        return avatarUrl.replace("150?","400?");
    }
}