package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class PiecesActivity extends AppCompatActivity {

    private InventairePieces inventaireP;

    private EditText codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece;
    private Spinner typePiece;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieces);

        codePiece = (EditText) findViewById(R.id.codePiece);
        nomPiece = (EditText) findViewById(R.id.nomPiece);
        descriptionPiece = (EditText) findViewById(R.id.descriptionPiece);
        dimensionPiece = (EditText) findViewById(R.id.dimensionPiece);
        prixCoutantPiece = (EditText) findViewById(R.id.prixCoutantPiece);
        qtyPiece = (EditText) findViewById(R.id.qtyPiece);

        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();


    }

    public void addItemsToUnitTypeSpinner() {

        typePiece = (Spinner) findViewById(R.id.typePiece);

        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.categorie_pierre_spacers, android.R.layout.simple_spinner_item);

        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typePiece.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addListenerToUnitTypeSpinner() {

        typePiece = (Spinner) findViewById(R.id.typePiece);

        typePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(pos).toString();
                type = itemSelectedInSpinner;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });
    }

    /**
     * Ajoute la pièce à l’inventaire et retourne au menu principal
     * @param view
     */
    public void ajouterPieceListener(View view) {

        //Pieces piece = new Pieces(323, "Roche", "une roche", 3, 4, 5, "caillou");

        /*Pieces piece = new Pieces(Integer.parseInt(codePiece.getText().toString()), nomPiece.getText().toString(),
                descriptionPiece.getText().toString(), Integer.parseInt(dimensionPiece.getText().toString()),
                Integer.parseInt(prixCoutantPiece.getText().toString()), Integer.parseInt(qtyPiece.getText().toString()), type);*/

        Pieces piece = new Pieces();

        piece.setCodePiece(Integer.parseInt(codePiece.getText().toString()));
        piece.setNomPiece(nomPiece.getText().toString());
        piece.setDescriptionPiece(descriptionPiece.getText().toString());
        piece.setDimensionPiece(Integer.parseInt(dimensionPiece.getText().toString()));
        piece.setPrixCoutantPiece(Integer.parseInt(prixCoutantPiece.getText().toString()));
        piece.setQtyPiece(Integer.parseInt(qtyPiece.getText().toString()));
        piece.setTypePiece(type);

        //inventaireP.addToInventairePieces(piece);

        String confirm = ("La pièce '" + nomPiece.getText() + "' est ajouté à l'inventaire.");
        Toast.makeText(this, confirm, Toast.LENGTH_LONG).show();

        final int result = 1;
        Intent pieceIntent = new Intent(this, PiecesActivityView.class).putExtra("pieceView", piece);
        startActivityForResult(pieceIntent, result);

        finish();

    }

    /**
     * Retourne au menu principal
     * @param view
     */
    public void annulerAjouterPieceListener(View view) {

        finish();
    }
}
