package com.tomaskostadinov.openbeatz.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.tomaskostadinov.openbeatz.R;
import com.tomaskostadinov.openbeatz.fragment.GuideFragment;
import com.tomaskostadinov.openbeatz.fragment.LineupDayFragment;
import com.tomaskostadinov.openbeatz.fragment.LineupFragment;
import com.tomaskostadinov.openbeatz.fragment.NewsFragment;
import com.tomaskostadinov.openbeatz.fragment.TicketFragment;
import com.tomaskostadinov.openbeatz.model.Guide;
import com.tomaskostadinov.openbeatz.model.Message;
import com.tomaskostadinov.openbeatz.model.Stage;



public class MainActivity extends App implements NewsFragment.OnListFragmentInteractionListener, LineupFragment.OnListFragmentInteractionListener, LineupDayFragment.OnFragmentInteractionListener, GuideFragment.OnListFragmentInteractionListener, TicketFragment.OnFragmentInteractionListener {

    private ActionBar mActionbar;
    private Fragment newsFragment, lineupFragment, assistantFragment, guideFragment, ticketFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(newsFragment);
                    return true;
                case R.id.navigation_assistant:
                    return true;
                case R.id.navigation_lineup:
                    showLineup();
                    return true;
                case R.id.navigation_guide:
                    if (guideFragment == null) {
                        guideFragment = new GuideFragment();
                    }
                    switchFragment(guideFragment);
                    return true;
                case R.id.navigation_ticket:
                    if (ticketFragment == null) {
                        ticketFragment = new TicketFragment();
                    }
                    switchFragment(ticketFragment);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mActionbar = getSupportActionBar();
        if (mActionbar != null) {
            //mActionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        }

        newsFragment = new NewsFragment();
        newsFragment.setRetainInstance(true);
        switchFragment(newsFragment);

    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }
    //@Shortcut(id = "movies", shortLabel = "Movies", icon = R.drawable.ic_album_black_24dp)
    public void showLineup(){
        if (lineupFragment == null) {
            lineupFragment = new LineupDayFragment();
        }
        switchFragment(lineupFragment);
    }

    @Override
    public void onListFragmentInteraction(Message item) {
        Log.i("Hi", "Hi");
        Toast.makeText(this, "Hiii", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onListFragmentInteraction(Stage item) {
        Intent i = new Intent(MainActivity.this, LineupDetailActivity.class);

        i.putExtra("stage", item);


        startActivity(i);
    }

    @Override
    public void onListFragmentInteraction(Guide item) {
        Intent i = new Intent(MainActivity.this, GuideDetailActivity.class);

        i.putExtra("guide", item);

        startActivity(i);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Hiii", Toast.LENGTH_SHORT).show();
    }

}
