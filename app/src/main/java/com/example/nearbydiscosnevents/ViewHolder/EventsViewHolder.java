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
    public RelativeLayout rlImage;
    public TextView tvTitle;

    /*public Button btnRankTwo,btnPriceDTwo;
    public CheckBox checkBoxFavoritesTwo;
    public RelativeLayout RLMainFragmentItemViewTwo;*/

    public EventsViewHolder(@NonNull View itemView) {
        super(itemView);

        shimmerFrameLayout = itemView.findViewById(R.id.shimmer_layout_main);
        rlImage = itemView.findViewById(R.id.rlEventImage);
        tvTitle = itemView.findViewById(R.id.tvTitleEvent);

        /*RLMainFragmentItemViewTwo = itemView.findViewById(R.id.RLMainFragmentItemView);
        btnRankTwo = itemView.findViewById(R.id.btnRankingTwo);
        btnPriceDTwo = itemView.findViewById(R.id.btnPriceTwo);
        checkBoxFavoritesTwo = itemView.findViewById(R.id.btnFavoriteButtonTwo);*/
    }


}