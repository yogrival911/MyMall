package com.yogdroidtech.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yogdroidtech.mymall.category.CategoryFragment;
import com.yogdroidtech.mymall.favourite.FavFragment;
import com.yogdroidtech.mymall.home.HomeFragment;
import com.yogdroidtech.mymall.wallet.WalletFragment;

public class MainActivity extends AppCompatActivity {
private BottomNavigationView bottomNavigationView;
private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frame);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new HomeFragment())
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.category:
                        selectedFragment = new CategoryFragment();
                        break;
                    case R.id.fav:
                        selectedFragment = new FavFragment();
                        break;
                    case R.id.wallet:
                        selectedFragment = new WalletFragment();
                        break;
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, selectedFragment)
                        .commit();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return  true;
    }
}