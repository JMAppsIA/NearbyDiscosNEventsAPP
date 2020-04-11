package com.example.nearbydiscosnevents.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbydiscosnevents.Adapters.EventsAdapter;
import com.example.nearbydiscosnevents.R;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    EventsAdapter eventsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("abc", "abc");
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = view.findViewById(R.id.rvMainFragment);
        eventsAdapter = new EventsAdapter(view.getContext());
        // recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(eventsAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    eventsAdapter.showShimmer=false;
                    eventsAdapter.notifyDataSetChanged();
            }
        },4000);

        return view;

    }
}
