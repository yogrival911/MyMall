package com.yogdroidtech.mymall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.yogdroidtech.mymall.addresses.AddressFragment;
import com.yogdroidtech.mymall.category.CategoryFragment;
import com.yogdroidtech.mymall.contact.ContactFragment;
import com.yogdroidtech.mymall.favourite.FavFragment;
import com.yogdroidtech.mymall.home.HomeFragment;
import com.yogdroidtech.mymall.register.SignUpFragment;
import com.yogdroidtech.mymall.register.loginFragment;
import com.yogdroidtech.mymall.wallet.WalletFragment;

public class MainActivity extends AppCompatActivity {
private BottomNavigationView bottomNavigationView;
private FrameLayout frameLayout;
private DrawerLayout drawerLayout;
private NavigationView navigationView;
private ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frame);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new HomeFragment())
                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.review:
                        showAlertDialogButtonClicked();
                        break;

                    case R.id.order:
                        Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.address:
                        drawerLayout.closeDrawers();
                        Fragment fragment = new AddressFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.replace(R.id.frame, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;

                    case R.id.contact:
                        drawerLayout.closeDrawers();
                        Fragment fragment2 = new ContactFragment();
                        FragmentManager fragmentManager2 = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                        fragmentTransaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction2.replace(R.id.frame, fragment2);
                        fragmentTransaction2.addToBackStack(null);
                        fragmentTransaction2.commit();
                        break;

                    case R.id.share:
                        drawerLayout.closeDrawers();
                        Intent sharingIntent = new Intent();
                        sharingIntent.setAction(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "Download VeggieGram";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "VeggieGram");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        break;
                }

                return true;
            }
        });

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()){


            case R.id.address:

            case R.id.order:
                Fragment fragment = new loginFragment();
                break;
            case R.id.share:
                Intent sharingIntent = new Intent();
                sharingIntent.setAction(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Check out Weather & Air Pollution Monitor";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Weather & Air Pollution Monitor");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
        }
        return true;

    }

    public void showAlertDialogButtonClicked() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View customLayout = getLayoutInflater().inflate(R.layout.custom_diloge, null);
        builder.setView(customLayout);

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void sendDialogDataToActivity(String data) {
        Toast.makeText(this,
                data,
                Toast.LENGTH_SHORT)
                .show();
    }

}