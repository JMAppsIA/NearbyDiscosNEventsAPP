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
import com.example.nearbydiscosnevents.Retrofit.IAPI;
import com.example.nearbydiscosnevents.Utils.Common;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bnvMain);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerMain,
                new MainFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_newsfeed: {
                           selectedFragment = new MainFragment();
                            break;
                        }
                        case R.id.nav_search: {
                            selectedFragment = new SearchFragment();
                            break;
                        }
                        case R.id.nav_profile: {
                            selectedFragment = new ProfileFragment();
                            break;
                        }
                        case R.id.nav_more: {
                            selectedFragment = new MoreFragment();
                            break;
                        }
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerMain,
                            selectedFragment).commit();

                    return true;
                }
            };
}
