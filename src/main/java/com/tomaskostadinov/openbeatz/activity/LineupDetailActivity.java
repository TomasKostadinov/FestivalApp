package com.tomaskostadinov.openbeatz.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;
import com.tomaskostadinov.openbeatz.R;
import com.tomaskostadinov.openbeatz.adapter.LineupDetailRecyclerViewAdapter;
import com.tomaskostadinov.openbeatz.model.Stage;

public class LineupDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineup_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        Stage s = (Stage) getIntent().getSerializableExtra("stage");
        getSupportActionBar().setTitle(s.getTitle());

        SmartImageView stageImage = (SmartImageView) findViewById(R.id.stageImage);
        stageImage.setImageUrl(s.getBackground());

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // specify an adapter (see also next example)
        LineupDetailRecyclerViewAdapter mAdapter = new LineupDetailRecyclerViewAdapter(s.getArtists());
        mRecyclerView.setAdapter(mAdapter);

    }
}
