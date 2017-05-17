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

import java.util.ArrayList;

public class PieceActivity extends AppCompatActivity {

    //private InventairePieces inventairePieces;

    private ArrayList<PieceModel> inventairePieces;

    private EditText codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece;
    private Spinner typePiece;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);

        inventairePieces = new ArrayList<>();

        codePiece = (EditText) findViewById(R.id.codePiece_edit);
        nomPiece = (EditText) findViewById(R.id.nomPiece_edit);
        descriptionPiece = (EditText) findViewById(R.id.descriptionPiece_edit);
        dimensionPiece = (EditText) findViewById(R.id.dimensionPiece_edit);
        prixCoutantPiece = (EditText) findViewById(R.id.prixCoutantPiece_edit);
        qtyPiece = (EditText) findViewById(R.id.qtyPiece_edit);

        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();



    }

    public void addItemsToUnitTypeSpinner() {

        typePiece = (Spinner) findViewById(R.id.typePiece_edit);

        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.categorie_pierre_spacers, android.R.layout.simple_spinner_item);

        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typePiece.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addListenerToUnitTypeSpinner() {

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

    /**
     * Ajoute la pièce à l’inventaire et retourne au menu principal
     * @param view
     */
    public void ajouterPieceListener(View view) {

        //PieceModel piece = new PieceModel(323, "Roche", "une roche", 3, 4, 5, "caillou");

        /*PieceModel piece = new PieceModel(Integer.parseInt(codePiece.getText().toString()), nomPiece.getText().toString(),
                descriptionPiece.getText().toString(), Integer.parseInt(dimensionPiece.getText().toString()),
                Integer.parseInt(prixCoutantPiece.getText().toString()), Integer.parseInt(qtyPiece.getText().toString()), type);*/

        PieceModel piece = new PieceModel();

        piece.setCodePiece(Integer.parseInt(codePiece.getText().toString()));
        piece.setNomPiece(nomPiece.getText().toString());
        piece.setDescriptionPiece(descriptionPiece.getText().toString());
        piece.setDimensionPiece(Integer.parseInt(dimensionPiece.getText().toString()));
        piece.setPrixCoutantPiece(Integer.parseInt(prixCoutantPiece.getText().toString()));
        piece.setQtyPiece(Integer.parseInt(qtyPiece.getText().toString()));
        piece.setTypePiece(type);

        inventairePieces.add(piece);

        String confirm = ("La pièce '" + nomPiece.getText() + "' est ajouté à l'inventaire.");
        Toast.makeText(this, confirm, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, PieceActivityView.class).putExtra(Intent.EXTRA_TEXT, piece.getNomPiece());
        startActivity(intent);

        finish();

    }

    /**
     * Retourne au menu principal
     * @param view
     */
    public void annulerAjouterPieceListener(View view) {

        finish();
    }


    /*private class Inventaire extends ArrayAdapter<PieceModel>{

        public Inventaire(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            TextView codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece, typePiece;

            if(convertView == null)
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);

            PieceModel piece = inventairePieces.get(position);

            codePiece = (EditText) view.findViewById(R.id.codePiece_edit);
            nomPiece = (EditText) findViewById(R.id.nomPiece_edit);
            descriptionPiece = (EditText) findViewById(R.id.descriptionPiece_edit);
            dimensionPiece = (EditText) findViewById(R.id.dimensionPiece_edit);
            prixCoutantPiece = (EditText) findViewById(R.id.prixCoutantPiece_edit);
            qtyPiece = (EditText) findViewById(R.id.qtyPiece_edit);
            typePiece = (EditText) findViewById(R.id.typePiece_edit);



            return convertView;
        }
    }*/

    public ArrayList<PieceModel> getInventairePieces() {
        return inventairePieces;
    }

}
