package com.tomaskostadinov.openbeatz.activitiy;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;


import com.tomaskostadinov.openbeatz.R;
import com.tomaskostadinov.openbeatz.fragment.LineupDayFragment;
import com.tomaskostadinov.openbeatz.fragment.LineupFragment;
import com.tomaskostadinov.openbeatz.fragment.NewsFragment;
import com.tomaskostadinov.openbeatz.model.Message;
import com.tomaskostadinov.openbeatz.model.Stage;

public class MainActivity extends AppCompatActivity implements NewsFragment.OnListFragmentInteractionListener, LineupFragment.OnListFragmentInteractionListener, LineupDayFragment.OnFragmentInteractionListener {

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
                    if (lineupFragment == null) {
                        lineupFragment = new LineupDayFragment();
                    }
                    switchFragment(lineupFragment);
                    //mTextMessage.setText(R.string.title_lineup);
                    return true;
                case R.id.navigation_guide:
                    //mTextMessage.setText(R.string.title_assistant);
                    return true;
                case R.id.navigation_ticket:
                    //mTextMessage.setText(R.string.title_lineup);
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
            mActionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorBackground)));
        }

        newsFragment = new NewsFragment();
        switchFragment(newsFragment);

    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }

    @Override
    public void onListFragmentInteraction(Message item) {
        Log.i("Hi", "Hi");
        Toast.makeText(this, "Hiii", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onListFragmentInteraction(Stage item) {
        Log.i("Hi", "Hi");
        Toast.makeText(this, "Hiii", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Hiii", Toast.LENGTH_SHORT).show();
    }
}
