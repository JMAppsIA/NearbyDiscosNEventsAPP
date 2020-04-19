package com.example.nearbydiscosnevents.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbydiscosnevents.R;
import com.example.nearbydiscosnevents.Utils.IITemClickListener;
import com.facebook.shimmer.ShimmerFrameLayout;

public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ShimmerFrameLayout shimmerFrameLayout;
    public RelativeLayout rlImage;
    public TextView tvTitle;
    IITemClickListener iItemClickListener;
    /*public Button btnRankTwo,btnPriceDTwo;
    public CheckBox checkBoxFavoritesTwo;
    public RelativeLayout RLMainFragmentItemViewTwo;*/

    public void setiItemClickListener(IITemClickListener iItemClickListener) {
        this.iItemClickListener = iItemClickListener;
    }


    public EventsViewHolder(@NonNull View itemView) {
        super(itemView);

        shimmerFrameLayout = itemView.findViewById(R.id.shimmer_layout_main);
        rlImage = itemView.findViewById(R.id.rlEventImage);
        tvTitle = itemView.findViewById(R.id.tvTitleEvent);
        itemView.setOnClickListener(this);

        /*RLMainFragmentItemViewTwo = itemView.findViewById(R.id.RLMainFragmentItemView);
        btnRankTwo = itemView.findViewById(R.id.btnRankingTwo);
        btnPriceDTwo = itemView.findViewById(R.id.btnPriceTwo);
        checkBoxFavoritesTwo = itemView.findViewById(R.id.btnFavoriteButtonTwo);*/
    }

    @Override
    public void onClick(View v) {
        iItemClickListener.onClick(v);
    }


}