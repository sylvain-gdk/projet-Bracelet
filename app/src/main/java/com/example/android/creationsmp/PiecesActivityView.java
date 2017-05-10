package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        codePiece.append(String.valueOf(piece.getCodePiece()));
        nomPiece.append(piece.getNomPiece().toString());
        descriptionPiece.append(piece.getNomPiece().toString());
        dimensionPiece.append(String.valueOf(piece.getDimensionPiece()) + "mm");
        prixCoutantPiece.append(String.valueOf(piece.getPrixCoutantPiece()));
        qtyPiece.append(String.valueOf(piece.getQtyPiece()));
        typePiece.append(piece.getTypePiece().toString());

        /*codePiece.setText(piece.getCodePiece());
        nomPiece.setText(piece.getNomPiece());
        descriptionPiece.setText(piece.getDescriptionPiece());
        dimensionPiece.setText(piece.getDimensionPiece() + "mm");
        prixCoutantPiece.setText((int) piece.getPrixCoutantPiece());
        qtyPiece.setText(piece.getQtyPiece());
        typePiece.setText(piece.getTypePiece());*/
    }

}
