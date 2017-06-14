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

public class PieceActivityEdit extends AppCompatActivity {

    private PieceModel piece;

    private EditText codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece;
    private Spinner typePiece, categoriePiece;
    private String type, categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_edit);

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
        piece = (PieceModel) intent.getSerializableExtra("to PieceActivityEdit");

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

    /*private void printErreurCapture(int code){
        String erreur = "";

        switch(code){
            case 1:
                erreur = ("Le code doit avoir entre 1 et 4 chiffres");
                break;
            case 2:
                erreur = ("Le nom de la pièce ne peut être vide");
                break;
            case 3:
                erreur = ("La description de la pièce ne peut être vide");
                break;
            case 4:
                erreur = ("La dimenssion doit être entre 4 et 15 mm");
                break;
            case 5:
                erreur = ("Le prix doit être au format 0.00");
                break;
            case 6:
                erreur = ("Vous avez oubliez de remplir une case");
                break;
        }
        Toast.makeText(this, erreur, Toast.LENGTH_LONG).show();

    }*/



    /**
     * Ajoute la pièce à l’inventaire et retourne au menu principal
     * @param view
     */
    public void ajouterPieceListener(View view) {
        PieceModel piece = new PieceModel();
        if(!piece.setCodePiece(Integer.parseInt(codePiece.getText().toString()))) {
            codePiece.setError("Le code doit avoir entre 1 et 4 chiffres");
            codePiece.requestFocus();
        }else if(!piece.setNomPiece(nomPiece.getText().toString())) {
            nomPiece.setError("Le nom de la pièce ne peut être vide");
            nomPiece.requestFocus();
        }else if(!piece.setDescriptionPiece(descriptionPiece.getText().toString())) {
            descriptionPiece.setError("La description de la pièce ne peut être vide");
            descriptionPiece.requestFocus();
        }else if(!piece.setDimensionPiece(Integer.parseInt(dimensionPiece.getText().toString()))) {
            dimensionPiece.setError("La dimension doit être entre 4 et 15 mm");
            dimensionPiece.requestFocus();
        }else if(!piece.setPrixCoutantPiece(Double.parseDouble(prixCoutantPiece.getText().toString()))) {
            prixCoutantPiece.setError("Le prix ne peut être 0 et doit être au format 0.00");
            prixCoutantPiece.requestFocus();
        }else if(piece.getCodePiece() > 0) {
            piece.setQtyPiece(Integer.parseInt(qtyPiece.getText().toString()));
            piece.setTypePiece(type);
            piece.setCategoriePiece(categorie);

            Intent intent = new Intent(this, InventairePiecesActivity.class).putExtra("to InventairePieceFragment", piece);
            setResult(RESULT_OK, intent);

            finish();
        }
    }



    /**
     * Retourne au menu principal
     * @param view
     */
    public void annulerAjouterPieceListener(View view) {

        finish();
    }

}
