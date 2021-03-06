package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.RemoveFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NeighbourFavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NeighbourFavoritesFragment extends Fragment implements NeighbourClickListener {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> mFavoriteNeighbours = new ArrayList<>();
    private RecyclerView mRecyclerView;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFavoritesFragment}
     */
    public static NeighbourFavoritesFragment newInstance() {
        NeighbourFavoritesFragment fragment = new NeighbourFavoritesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_favorites_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();
        mFavoriteNeighbours = new ArrayList<>();
        mFavoriteNeighbours = mApiService.getFavorites();
        mRecyclerView.setAdapter(new MyNeighbourFavoritesRecyclerViewAdapter(mFavoriteNeighbours, getActivity(), this));
    }


    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }



    /**
     * Fired if the user clicks on a favorite button
     * @param event
     */
    @Subscribe
    public void onRemoveFavoriteNeighbour(RemoveFavoriteNeighbourEvent event) {
        initList();
    }

    @Override
    public void navigateToNeighbourInfo(Neighbour neighbour) {
        Intent intent = new Intent(getActivity(), InfoNeighbourActivity.class);
        intent.putExtra(InfoNeighbourActivity.BUNDLE_NEIGHBOUR, neighbour.getId());
        startActivity(intent);
    }
}