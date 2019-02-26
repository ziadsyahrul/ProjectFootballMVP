package com.ziadsyahrul.projectfootballmvp.UI.teams.Main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.ziadsyahrul.projectfootballmvp.R;
import com.ziadsyahrul.projectfootballmvp.UI.favorite.FavoriteFragment;
import com.ziadsyahrul.projectfootballmvp.UI.teams.TeamsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fl_container)
    FrameLayout flContainer;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_team:
                    // Menampilkan Title
                    getSupportActionBar().setTitle("Teams");
                    // Menampilkan teams fragment
                    TeamsFragment teamsFragment = new TeamsFragment();
                    loadFragment(teamsFragment);
                    return true;
                case R.id.navigation_favorite:
                    // Menampilkan Title
                    getSupportActionBar().setTitle("Favorite Teams");
                    // Menampilkan favorite fragment
                    FavoriteFragment favoriteFragment = new FavoriteFragment();
                    loadFragment(favoriteFragment);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // Menampilkan Title
        getSupportActionBar().setTitle("List Teams");

        // Buat object fragment teams
        TeamsFragment teamsFragment = new TeamsFragment();
        loadFragment(teamsFragment);
    }

    private void loadFragment(Fragment fragment){
        // Menampilkan Fragment
        // membuat object function transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // replace berguna untuk menggantikan frame layout dengan fragment
        transaction.replace(R.id.fl_container, fragment);
        transaction.addToBackStack(null);
        // Commit untuk melaksanakannya
        transaction.commit();
    }

}
