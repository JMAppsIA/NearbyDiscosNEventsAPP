package com.example.nearbydiscosnevents.Adapters;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbydiscosnevents.Fragments.MainFragment;
import com.example.nearbydiscosnevents.LocalDetailActivity;
import com.example.nearbydiscosnevents.Models.Local;
import com.example.nearbydiscosnevents.Models.Response.ResponseObtainLocals;
import com.example.nearbydiscosnevents.R;
import com.example.nearbydiscosnevents.Utils.IITemClickListener;
import com.example.nearbydiscosnevents.ViewHolder.EventsViewHolder;

import java.util.List;


public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    public boolean dataLoaded = true;
    public int SHIMMER_ITEM_NUMBER = 4; // number of shimmer items shown while loading.
    private Context context;
    ResponseObtainLocals locales;

    public EventsAdapter(Context context, ResponseObtainLocals locales) {
        this.context = context;
        this.locales = locales;
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

        if(!dataLoaded) {
            holder.shimmerFrameLayout.startShimmer(); //Starts shimmer animation
        } else {
                holder.shimmerFrameLayout.stopShimmer(); // Stops shimmer animation
                holder.shimmerFrameLayout.setShimmer(null); // it removes shimmer overlay
                String nomLocal = locales.getMessage().get(position).getNombreLocal();
                holder.rlImage.setBackground(ContextCompat.getDrawable(context,R.drawable.event_bg));
                holder.tvTitle.getLayoutParams().width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                holder.tvTitle.setBackground(null);
                holder.tvTitle.setText(nomLocal);

                holder.setiItemClickListener(new IITemClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, locales.getMessage().get(position).getIdLocal().toString(), Toast.LENGTH_SHORT).show();
                        showDetailOfLocal(locales.getMessage().get(position).getIdLocal().toString());
                    }
                });




            /*holder.btnRankTwo.setBackground(ContextCompat.getDrawable(context, R.drawable.custom_button_pink));
            holder.btnRankTwo.setText(R.string.ranking_detail);
            holder.btnRankTwo.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_star, 0);

            holder.btnPriceDTwo.setBackground(ContextCompat.getDrawable(context,R.drawable.rounded_custom_price));
            holder.btnPriceDTwo.setText(R.string.price_detail);

            holder.checkBoxFavoritesTwo.setVisibility(View.VISIBLE);

            holder.RLMainFragmentItemViewTwo.setBackground(ContextCompat.getDrawable(context, R.drawable.image));*/

        }

    }

    private void showDetailOfLocal(String idLocal) {
        Activity activity = (Activity) context;
        Intent intent = new Intent(context, LocalDetailActivity.class);
        context.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

    @Override
    public int getItemCount() {
        return dataLoaded?locales.getMessage().size():SHIMMER_ITEM_NUMBER; //after loading shows list SIZE (1);
    }
}
