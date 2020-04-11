package com.example.nearbydiscosnevents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbydiscosnevents.R;
import com.example.nearbydiscosnevents.ViewHolder.EventsViewHolder;


public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    public boolean showShimmer = true;
    public int SHIMMER_ITEM_NUMBER = 1; // number of shimmer items shown while loading.
    private Context context;

    public EventsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_main_fragment,viewGroup,false);
        context = viewGroup.getContext();
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        ViewGroup viewGroup;
        if(showShimmer) {
            holder.shimmerFrameLayout.startShimmer(); //Starts shimmer animation
        } else {
            holder.shimmerFrameLayout.stopShimmer(); // Stops shimmer animation
            holder.shimmerFrameLayout.setShimmer(null); // it removes shimmer overlay

            holder.btnRank.setVisibility(View.VISIBLE);

            holder.tvTitle.setBackground(null);
            holder.tvTitle.setText("Evento numero uno");

            holder.relativeLayoutImage.setBackground(ContextCompat.getDrawable(context, R.drawable.register_bg));
            holder.relativeLayoutRanking.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_button_pink));
            holder.checkBoxFavorites.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return showShimmer?SHIMMER_ITEM_NUMBER:1; //after loading shows list SIZE (1);
    }
}
