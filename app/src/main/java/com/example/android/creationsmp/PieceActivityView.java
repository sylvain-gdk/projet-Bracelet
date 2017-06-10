package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PieceActivityView extends AppCompatActivity {

    private TextView codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece, typePiece, categoriePiece;

    private InventairePieces inventairePieces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_piece_view_container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //startActivity(new Intent(this, InventairePiecesActivity.class));
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void setPiece(PieceModel piece) {

        codePiece.setText(String.valueOf(piece.getCodePiece()));
        nomPiece.setText(String.valueOf(piece.getNomPiece()));
        descriptionPiece.setText(String.valueOf(piece.getDescriptionPiece()));
        dimensionPiece.setText(String.valueOf(piece.getDimensionPiece()) + " mm");
        prixCoutantPiece.setText(String.valueOf(piece.getPrixCoutantPiece()) + " $");
        qtyPiece.setText(String.valueOf(piece.getQtyPiece()));
        typePiece.setText(String.valueOf(piece.getTypePiece()));
        categoriePiece.setText(String.valueOf(piece.getCategoriePiece()));


    }


    public void voirListeListener(View view) {

        /*Intent intent = new Intent(this, InventairePiecesActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, inventairePieces);
        startActivity(intent);*/

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

                PieceModel piece = (PieceModel) intent.getSerializableExtra(Intent.EXTRA_TEXT);

                ((TextView) rootView.findViewById(R.id.codePiece_text)).setText(String.valueOf(piece.getCodePiece()));
                ((TextView) rootView.findViewById(R.id.nomPiece_text)).setText(piece.getNomPiece());
                ((TextView) rootView.findViewById(R.id.descriptionPiece_text)).setText(piece.getDescriptionPiece());
                ((TextView) rootView.findViewById(R.id.dimensionPiece_text)).setText(String.valueOf(piece.getDimensionPiece()));
                ((TextView) rootView.findViewById(R.id.prixCoutantPiece_text)).setText(String.valueOf(piece.getPrixCoutantPiece()));
                ((TextView) rootView.findViewById(R.id.qtyPiece_text)).setText(String.valueOf(piece.getQtyPiece()));
                ((TextView) rootView.findViewById(R.id.typePiece_text)).setText(piece.getTypePiece());
                ((TextView) rootView.findViewById(R.id.categoriePiece_text)).setText(piece.getCategoriePiece());

            }
            return rootView;
        }
    }
}
