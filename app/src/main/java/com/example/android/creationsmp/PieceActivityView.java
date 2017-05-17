package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PieceActivityView extends AppCompatActivity {

    //private PieceModel piece;
    private TextView codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece, typePiece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_piece_view_container, new PlaceholderFragment())
                    .commit();
        }

        /*Intent intent = getIntent();
        piece = (PieceModel) intent.getSerializableExtra("pieceView");

        codePiece = (TextView) findViewById(R.id.codePiece_text);
        nomPiece = (TextView) findViewById(R.id.nomPiece_text);
        descriptionPiece = (TextView) findViewById(R.id.descriptionPiece_text);
        dimensionPiece = (TextView) findViewById(R.id.dimensionPiece_text);
        prixCoutantPiece = (TextView) findViewById(R.id.prixCoutantPiece_text);
        qtyPiece = (TextView) findViewById(R.id.qtyPiece_text);
        typePiece = (TextView) findViewById(R.id.typePiece_text);

        setPiece(piece);*/

    }

    private void setPiece(PieceModel piece) {

        codePiece.setText(String.valueOf(piece.getCodePiece()));
        nomPiece.setText(String.valueOf(piece.getNomPiece()));
        descriptionPiece.setText(String.valueOf(piece.getDescriptionPiece()));
        dimensionPiece.setText(String.valueOf(piece.getDimensionPiece()) + " mm");
        prixCoutantPiece.setText(String.valueOf(piece.getPrixCoutantPiece()) + " $");
        qtyPiece.setText(String.valueOf(piece.getQtyPiece()));
        typePiece.setText(String.valueOf(piece.getTypePiece()));

    }


    public void voirListeListener(View view) {

        Intent intent = new Intent(this, InventairePiecesActivity.class);
        startActivity(intent);

        finish();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_piece_view, container, false);

            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){
                String piece = intent.getStringExtra(Intent.EXTRA_TEXT);
                ((TextView) rootView.findViewById(R.id.nomPiece_text)).setText(piece);
            }
            return rootView;
        }
    }
}