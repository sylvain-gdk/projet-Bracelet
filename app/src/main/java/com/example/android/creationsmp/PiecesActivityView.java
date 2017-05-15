package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PiecesActivityView extends AppCompatActivity {

    private Pieces piece;
    private TextView codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece, typePiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieces_view);

        Intent activityThatCalled = getIntent();
        piece = (Pieces) activityThatCalled.getSerializableExtra("pieceView");

        codePiece = (TextView) findViewById(R.id.codePiece);
        nomPiece = (TextView) findViewById(R.id.nomPiece);
        descriptionPiece = (TextView) findViewById(R.id.descriptionPiece);
        dimensionPiece = (TextView) findViewById(R.id.dimensionPiece);
        prixCoutantPiece = (TextView) findViewById(R.id.prixCoutantPiece);
        qtyPiece = (TextView) findViewById(R.id.qtyPiece);
        typePiece = (TextView) findViewById(R.id.typePiece);

        setPiece(piece);

    }

    private void setPiece(Pieces piece) {

        codePiece.setText(String.valueOf(piece.getCodePiece()));
        nomPiece.setText(String.valueOf(piece.getNomPiece()));
        descriptionPiece.setText(String.valueOf(piece.getDescriptionPiece()));
        dimensionPiece.setText(String.valueOf(piece.getDimensionPiece()) + " mm");
        prixCoutantPiece.setText(String.valueOf(piece.getPrixCoutantPiece()) + " $");
        qtyPiece.setText(String.valueOf(piece.getQtyPiece()));
        typePiece.setText(String.valueOf(piece.getTypePiece()));

    }


    public void voirListeListener(View view) {

        final int result = 1;
        Intent pieceIntent = new Intent(this, InventairePiecesActivity.class).putExtra("pieceView", piece);
        startActivityForResult(pieceIntent, result);

        finish();
    }
}
