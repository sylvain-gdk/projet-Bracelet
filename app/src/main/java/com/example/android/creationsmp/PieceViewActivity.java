package com.example.android.creationsmp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sylvain on 2017-05-16.
 * This class is the Activity for the details of each item
 */

public class PieceViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_piece_view_container, new PieceViewFragment())
                    .commit();
        }
    }
}
