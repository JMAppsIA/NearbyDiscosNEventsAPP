package com.example.nearbydiscosnevents.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nearbydiscosnevents.Models.User;
import com.example.nearbydiscosnevents.R;
import com.example.nearbydiscosnevents.Utils.Common;

public class ProfileFragment extends Fragment {

    TextView tvTitleFragmentP;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        //tvTitleFragmentP = view.findViewById(R.id.tvTitleFragmentProfile);
        //User usuario = Common.usuarioActual;
        //tvTitleFragmentP.setText(usuario.getFullName());


        return view;


    }
}
