package com.leARn.arcore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.leARn.arcore.fragment.BlogFragment;
import com.leARn.arcore.fragment.BotFragment;
import com.leARn.arcore.fragment.HomeArFragment;
import com.leARn.arcore.fragment.YoutubeRecyclerViewFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    BottomNavigationView bottomNavigationView;
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();
    private static final String[] YOUTUBE_PLAYLISTS = {
            "PLLF_mZmNqOn25pLFQkhJBl6gxHeKyB0Mo"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();
        fragmentManager = getSupportFragmentManager();
         fragmentManager.beginTransaction()
                .replace(R.id.frame , new HomeArFragment())
                .commit();



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.bot) {
                menuItem.setIcon(R.drawable.bot_active);

                fragmentManager.beginTransaction()
                        .replace(R.id.frame , new BotFragment())
                        .commit();
                return true;
            }
            if (menuItem.getItemId() == R.id.youtube) {
                menuItem.setIcon(R.drawable.video_active);

                fragmentManager.beginTransaction()
                        .replace(R.id.frame , new YoutubeRecyclerViewFragment().newInstance(mYoutubeDataApi , YOUTUBE_PLAYLISTS))
                        .commit();
                return true;
            }
            if (menuItem.getItemId() == R.id.home) {
                menuItem.setIcon(R.drawable.ar_active);
                fragmentManager.beginTransaction()
                        .replace(R.id.frame , new HomeArFragment())
                        .commit();
                return true;
            }
            if (menuItem.getItemId() == R.id.blog) {
                menuItem.setIcon(R.drawable.blog_active);

                fragmentManager.beginTransaction()
                        .replace(R.id.frame , new BlogFragment())
                        .commit();
                return true;
            }
            return false;
        });
    }


}