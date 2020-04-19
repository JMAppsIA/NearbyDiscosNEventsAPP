package com.example.nearbydiscosnevents.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbydiscosnevents.Adapters.EventsAdapter;
import com.example.nearbydiscosnevents.R;
import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Utils.Common;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    EventsAdapter eventsAdapter;
    IAPI mService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mService = Common.getAPI();
        View view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = view.findViewById(R.id.rvMainFragment);
        eventsAdapter = new EventsAdapter(view.getContext());
        // recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false));
        // recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(eventsAdapter);
        startLayoutAnimation(recyclerView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    eventsAdapter.showShimmer=false;
                    eventsAdapter.notifyDataSetChanged();
            }
        },4000);

        return view;

    }

    private void getListaDocentes(String idfacultad) {

       /* mService.loadAvailableEvents()
                .enqueue(new Callback<List<Docente>>() {
                    @Override
                    public void onResponse(Call<List<Docente>> call, Response<List<Docente>> response) {

                        llDocentes.setVisibility(View.GONE);
                        listaDocentes.setVisibility(View.VISIBLE);
                        mostrarLista(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Docente>> call, Throwable t) {
                        listaDocentes.setVisibility(View.GONE);
                        llDocentes.setVisibility(View.VISIBLE);
                        Toast.makeText(DocentesActivity.this, "No hay Docentes.", Toast.LENGTH_SHORT).show();
                    }
                });*/



    }

   /* private void mostrarLista(List<Docente> docentes) {

        DocenteAdaptador adaptador = new DocenteAdaptador(this,docentes);
        listaDocentes.setAdapter(adaptador);
    }*/


    private void startLayoutAnimation (RecyclerView recyclerView) {
        Context context = recyclerView.getContext();

        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
