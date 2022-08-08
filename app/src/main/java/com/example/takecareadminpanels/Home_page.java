package com.example.takecareadminpanels;

import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.takecareadminpanels.databinding.ActivityHomePageBinding;

public class Home_page extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomePageBinding binding;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        //////////////////////check internet connection start-1//////////////////////////
        broadcastReceiver = new Home_Page_internet_check ();
        register ();

        //////////////////////check internet connection end-1//////////////////////////

        binding = ActivityHomePageBinding.inflate (getLayoutInflater ( ));
        setContentView (binding.getRoot ( ));

        setSupportActionBar (binding.appBarHomePage.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder (
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout (drawer)
                .build ( );
        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment_content_home_page);
        NavigationUI.setupActionBarWithNavController (this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController (navigationView, navController);
    }

    //////////////////////check internet connection start-2//////////////////////////

    public void register(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver,new IntentFilter (ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    //////////////////////check internet connection end-2//////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater ( ).inflate (R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController (this, R.id.nav_host_fragment_content_home_page);
        return NavigationUI.navigateUp (navController, mAppBarConfiguration)
                || super.onSupportNavigateUp ( );
    }

    public void Con(View view) {
        startActivity (new Intent ( getApplicationContext (),Contact_Us.class ));
    }

    public void SuperAdmin(View view) {
        startActivity (new Intent ( getApplicationContext (),SuperAdmin_Panel.class ));
    }

    public void Doctor_Admin(View view) {
        startActivity (new Intent ( getApplicationContext (), Doctor_Admin.class ));
    }

    public void Lab_Admin(View view) {
        startActivity (new Intent ( getApplicationContext (),Lab_Admin.class ));
    }

    public void Pharmacy_Admin(View view) {
        startActivity (new Intent ( getApplicationContext (),Pharmacy_Admin.class ));
    }

    public void Rider(View view) {
        startActivity (new Intent ( getApplicationContext (), Rider_Admin.class ));
    }

    public void onBackPressed(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Close this Application")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

//        super.onBackPressed();
    }

    public void Con(MenuItem item) {
        startActivity (new Intent ( getApplicationContext (),Contact_Us.class ));
    }

    public void rating(MenuItem item) {
        startActivity (new Intent ( getApplicationContext (),Ratingbar.class ));

    }
    public void Doctor_Request_Form(MenuItem item) {
        startActivity (new Intent ( getApplicationContext (),Doctor_Request_Form.class ));

    }

    public void d(MenuItem item) {
        startActivity (new Intent ( getApplicationContext (),Contact_Us.class ));

    }
}