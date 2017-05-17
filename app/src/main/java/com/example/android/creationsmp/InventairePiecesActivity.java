package com.example.android.creationsmp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InventairePiecesActivity extends AppCompatActivity {


    private InventairePieces inventairePieces;
    private PieceModel piece;

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


    /**
     * Montre la pièce
     */
    /*public void montrerPieceListener(String item) {

        PieceModel piece = new PieceModel();

        piece.setCodePiece(3245);
        piece.setNomPiece(item);
        piece.setDescriptionPiece("N/A");
        piece.setDimensionPiece(3);
        piece.setPrixCoutantPiece(15);
        piece.setQtyPiece(32);
        piece.setTypePiece("N/A");

        //inventaireP.addToInventairePieces(piece);

        final int result = 1;
        Intent pieceIntent = new Intent(this, PieceActivityView.class).putExtra("pieceView", piece);
        startActivityForResult(pieceIntent, result);

        finish();

    }*/

}
