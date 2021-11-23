package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RemoveFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyNeighbourFavoritesRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourFavoritesRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;
    private final Context mContext;
    private final NeighbourClickListener listener;

    public MyNeighbourFavoritesRecyclerViewAdapter(List<Neighbour> items, Context context, NeighbourClickListener clickListener) {
        mNeighbours = items;
        mContext = context;
        listener = clickListener;
    }

    @Override
    public MyNeighbourFavoritesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour_favorites, parent, false);
        return new MyNeighbourFavoritesRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyNeighbourFavoritesRecyclerViewAdapter.ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);
        holder.mFavoriteButton.setSelected(neighbour.getFavorite());
        holder.mConstraintLayout.setOnClickListener(view -> listener.navigateToNeighbourInfo(neighbour));
        holder.mFavoriteButton.setOnClickListener(view -> {
            neighbour.setFavorite(!neighbour.getFavorite());
            holder.mFavoriteButton.setSelected(neighbour.getFavorite());
            EventBus.getDefault().post(new RemoveFavoriteNeighbourEvent(neighbour));
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.user_container)
        public ConstraintLayout mConstraintLayout;
        @BindView(R.id.item_list_favorite_button)
        public ImageButton mFavoriteButton;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
