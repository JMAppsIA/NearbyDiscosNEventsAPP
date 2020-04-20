package com.example.nearbydiscosnevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.nearbydiscosnevents.Fragments.MainFragment;
import com.example.nearbydiscosnevents.Fragments.MoreFragment;
import com.example.nearbydiscosnevents.Fragments.ProfileFragment;
import com.example.nearbydiscosnevents.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LocalDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_detail);


    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
