package com.tomaskostadinov.openbeatz.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.tomaskostadinov.openbeatz.R;
import com.tomaskostadinov.openbeatz.fragment.GuideDetailFragment;
import com.tomaskostadinov.openbeatz.fragment.GuideDetailTabFragment;
import com.tomaskostadinov.openbeatz.fragment.GuideFragment;
import com.tomaskostadinov.openbeatz.fragment.NewsFragment;
import com.tomaskostadinov.openbeatz.model.Guide;
import com.tomaskostadinov.openbeatz.model.Stage;

public class GuideDetailActivity extends AppCompatActivity implements  GuideDetailTabFragment.OnFragmentInteractionListener, GuideDetailFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        Fragment guideDetailTabFragment = new GuideDetailTabFragment();
        guideDetailTabFragment.setRetainInstance(true);


        Guide s = (Guide) getIntent().getSerializableExtra("guide");
        switchFragment(guideDetailTabFragment, s);
        getSupportActionBar().setTitle(s.getTitle());

    }

    public void switchFragment(Fragment fragment, Guide guide) {
        Bundle args = new Bundle();
        args.putString("url", guide.getSectionUrl());
        fragment.setArguments(args);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, "Hiii", Toast.LENGTH_SHORT).show();
    }



}
