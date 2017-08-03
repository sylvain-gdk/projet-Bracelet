package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Places an icon inside the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_actionbar);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }


    /**
     * Starts an activity to add an object "piece" to the inventory
     * @param view
     */
    public void ajouterPieceListener(View view) {

        startActivity(new Intent(this, PieceEditActivity.class));
    }

    /**
     * Starts an activity to show the "piece" inventory as a list
     * @param view
     */
    public void voirPiecesListener(View view) {

        startActivity(new Intent(this, InventairePiecesActivity.class));

    }

    /**
     * Starts an activity to add an object "bracelet" to the inventory
     * @param view
     */
    public void ajouterBraceletListener(View view) {
        //TODO le système affiche la fenêtre de bracelet pour prendre l’information
    }

    /**
     * Starts an activity to show the "bracelet" inventory as a list
     * @param view
     */
    public void voirBraceletsListener(View view) {
        //TODO le système affiche la liste de bracelets
    }

}
