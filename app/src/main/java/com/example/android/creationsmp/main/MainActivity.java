package com.example.android.creationsmp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.android.creationsmp.R;
import com.example.android.creationsmp.pieces.PieceAddDetailActivity;
import com.example.android.creationsmp.pieces.PiecesInventaireActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Places an icon inside the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_actionbar);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }


    /**
     * Starts an activity to add an object PieceModel to the collection
     * @param view
     */
    public void ajouterPieceListener(View view) {

        startActivity(new Intent(this, PieceAddDetailActivity.class));
    }

    /**
     * Starts an activity to show the collection of objets PieceModel
     * @param view
     */
    public void voirPiecesListener(View view) {

        startActivity(new Intent(this, PiecesInventaireActivity.class));
    }

    /**
     * Starts an activity to add an object "bracelet" to a collection
     * @param view
     */
    public void ajouterBraceletListener(View view) {
        //TODO le système affiche la fenêtre de bracelet pour prendre l’information
    }

    /**
     * Starts an activity to show the "bracelet" collection as a list
     * @param view
     */
    public void voirBraceletsListener(View view) {
        //TODO le système affiche la liste de bracelets
    }

}
