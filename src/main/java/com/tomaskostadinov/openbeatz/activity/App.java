package com.tomaskostadinov.openbeatz.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import shortbread.Shortbread;

/**
 * Created by tomas on 07.04.17
 */

public class App extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Shortbread.create(this);
    }
}
