package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //private ArrayAdapter<PieceModel> inventairePiecesAdapter;
    //private InventairePieces inventairePieces;
    //private ArrayList<PieceModel> inventairePieces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    /**
     * Affiche la fenêtre pour ajouter une pièce
     * @param view
     */
    public void ajouterPieceListener(View view) {

        startActivity(new Intent(this, PieceActivity.class));
    }

    /**
     * Affiche la liste des pièces en inventaire
     * @param view
     */
    public void voirPiecesListener(View view) {

        startActivity(new Intent(this, InventairePiecesActivity.class));

    }

    public void ajouterBraceletListener(View view) {
        //TODO le système affiche la fenêtre de bracelet pour prendre l’information
    }

    public void voirBraceletsListener(View view) {
        //TODO le système affiche la liste de bracelets
    }

}
