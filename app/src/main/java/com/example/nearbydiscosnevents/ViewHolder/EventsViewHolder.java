package com.example.nearbydiscosnevents.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbydiscosnevents.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class EventsViewHolder extends RecyclerView.ViewHolder {

    public ShimmerFrameLayout shimmerFrameLayout;
    public Button btnRank;
    public TextView tvTitle;
    public RelativeLayout relativeLayoutImage,relativeLayoutRanking;
    public CheckBox checkBoxFavorites;

    public EventsViewHolder(@NonNull View itemView) {
        super(itemView);

        shimmerFrameLayout = itemView.findViewById(R.id.shimmer_layout_main);
        btnRank = itemView.findViewById(R.id.btnRanking);
        tvTitle = itemView.findViewById(R.id.tvTitleEvent);
        relativeLayoutImage = itemView.findViewById(R.id.RLMainFragment);
        relativeLayoutRanking = itemView.findViewById(R.id.RLButtonRank);
        checkBoxFavorites = itemView.findViewById(R.id.btnFavoriteButton);
    }


}