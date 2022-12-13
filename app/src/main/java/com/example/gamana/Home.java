package com.example.gamana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    RideFragment rideFragment = new RideFragment();
    NotifyFragment notifyFragment = new NotifyFragment();
    ProfileFragment profileFragment = new ProfileFragment() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.BottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout , homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout , homeFragment).commit();
                        return true ;

                    case R.id.ride:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout , rideFragment).commit();
                        return true ;

                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout , notifyFragment).commit();
                        return true ;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.FrameLayout , profileFragment).commit();
                        return true ;
                }
                return false;
            }
        });
    }
}