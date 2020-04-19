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
import com.example.nearbydiscosnevents.Models.Local;
import com.example.nearbydiscosnevents.Models.Response.ResponseObtainLocals;
import com.example.nearbydiscosnevents.R;
import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Utils.Common;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    RecyclerView recyclerView;
    EventsAdapter eventsAdapter;
    IAPI mService;
    View view;
    boolean shimmer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shimmer = true;
        mService = Common.getAPI();
        view = inflater.inflate(R.layout.fragment_main,container,false);
        getListaLocales();
        return view;

    }


    private void getListaLocales() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key",Common.API_KEY);
            jsonObject.put("estadoLocal",8);
            RequestBody myreqbody = null;
            myreqbody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                    (new JSONObject(String.valueOf(jsonObject))).toString());
            mService.obtainLocals(myreqbody).enqueue(new Callback<ResponseObtainLocals>() {
                @Override
                public void onResponse(Call<ResponseObtainLocals> call, Response<ResponseObtainLocals> response) {

                    if(response.isSuccessful()) {
                        shimmer = false;
                        mostrarLista(response.body(),shimmer);
                    } else {
                        shimmer = true;
                    }

                }

                @Override
                public void onFailure(Call<ResponseObtainLocals> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void mostrarLista(ResponseObtainLocals locales, boolean shimmer) {
        recyclerView = view.findViewById(R.id.rvMainFragment);
        eventsAdapter = new EventsAdapter(getContext(),locales);
        // recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false));
        // recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(eventsAdapter);
        eventsAdapter.showShimmer=false;
        startLayoutAnimation(recyclerView);


    }

    private void startLayoutAnimation (RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }
}
