package com.laioffer.tinnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. find bottom navigation view from its id
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //2. find fragment container view, it is a fixed format, remember
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();

        //connect navView and navController, click each nav view, show its fragment
        NavigationUI.setupWithNavController(navView, navController);

        //点击save, 左上角title就会显示save, 点击search就会显示search
        NavigationUI.setupActionBarWithNavController(this, navController);
        NewsApi api = RetrofitClient.newInstance().create(NewsApi.class);

    }

    //Overriding onSupportNavigateUp is for handling the top left back button.
    // Notice the title bar also changes with bottom navigation.
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

}