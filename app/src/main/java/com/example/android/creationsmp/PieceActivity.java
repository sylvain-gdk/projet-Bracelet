package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class PieceActivity extends AppCompatActivity {

    private PieceModel piece;

    private EditText codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece;
    private Spinner typePiece, categoriePiece;
    private String type, categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);

        this.piece = new PieceModel();

        codePiece = (EditText) findViewById(R.id.codePiece_edit);
        nomPiece = (EditText) findViewById(R.id.nomPiece_edit);
        descriptionPiece = (EditText) findViewById(R.id.descriptionPiece_edit);
        dimensionPiece = (EditText) findViewById(R.id.dimensionPiece_edit);
        prixCoutantPiece = (EditText) findViewById(R.id.prixCoutantPiece_edit);
        qtyPiece = (EditText) findViewById(R.id.qtyPiece_edit);

        addItemsToTypeSpinner();
        addListenerToTypeSpinner();
        addItemsToCategorieSpinner();
        addListenerToCategorieSpinner();

        Intent intent = this.getIntent();
        piece = (PieceModel) intent.getSerializableExtra("to PieceActivity");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //startActivity(new Intent(this, InventairePiecesActivity.class).putExtra(Intent.EXTRA_TEXT, inventairePieces));
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void addItemsToTypeSpinner() {

        typePiece = (Spinner) findViewById(R.id.typePiece_edit);
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.type_piece, android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typePiece.setAdapter(typeSpinnerAdapter);
    }

    public void addListenerToTypeSpinner() {

        typePiece = (Spinner) findViewById(R.id.typePiece_edit);

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

    public void addItemsToCategorieSpinner() {

        categoriePiece = (Spinner) findViewById(R.id.categoriePiece_edit);
        ArrayAdapter<CharSequence> categorieSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.categorie_piece, android.R.layout.simple_spinner_item);
        categorieSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriePiece.setAdapter(categorieSpinnerAdapter);
    }

    public void addListenerToCategorieSpinner() {

        categoriePiece = (Spinner) findViewById(R.id.categoriePiece_edit);

        categoriePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(pos).toString();
                categorie = itemSelectedInSpinner;
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

        PieceModel piece = new PieceModel();

        piece.setCodePiece(Integer.parseInt(codePiece.getText().toString()));
        piece.setNomPiece(nomPiece.getText().toString());
        piece.setDescriptionPiece(descriptionPiece.getText().toString());
        piece.setDimensionPiece(Integer.parseInt(dimensionPiece.getText().toString()));
        piece.setPrixCoutantPiece(Integer.parseInt(prixCoutantPiece.getText().toString()));
        piece.setQtyPiece(Integer.parseInt(qtyPiece.getText().toString()));
        piece.setTypePiece(type);
        piece.setCategoriePiece(categorie);

        Intent intent = new Intent(this, InventairePiecesActivity.class).putExtra("to InventairePieceFragment", piece);
        setResult(RESULT_OK, intent);

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
