package com.example.android.creationsmp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InventairePiecesActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pieces_inventaire);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_pieces_inventaire_container, new InventairePiecesFragment())
                    .commit();
        }

    }

}
