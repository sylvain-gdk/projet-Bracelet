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

    // Accède à la classe de liste de pièces
    private InventairePieces inventairePieces;
    // Accède à la classe de pièces
    private PieceModel piece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_piece_view_container, new PlaceholderFragment())
                    .commit();
        }
        Intent intent = this.getIntent();
        piece = (PieceModel) intent.getSerializableExtra("piece");
        inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");
    }

    public void voirListeListener(View view) {
        finish();
    }

    /**
     * Option pour revenir au parent
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, InventairePiecesActivity.class)
                .putExtra("inventairePieces", inventairePieces);
        setResult(RESULT_OK, intent);
        finish();
        return super.onOptionsItemSelected(item);
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
            PieceModel piece = (PieceModel) intent.getSerializableExtra("piece");

            View rootView = inflater.inflate(R.layout.fragment_piece_view, container, false);

            getActivity().setTitle(piece.getNomPiece());

            ((TextView) rootView.findViewById(R.id.codePiece_text)).setText(String.valueOf("# " + piece.getCodePiece()));
            ((TextView) rootView.findViewById(R.id.nomPiece_text)).setText(piece.getNomPiece());
            ((TextView) rootView.findViewById(R.id.descriptionPiece_text)).setText(piece.getDescriptionPiece());
            ((TextView) rootView.findViewById(R.id.dimensionPiece_text)).setText(String.valueOf(piece.getDimensionPiece()) + " mm");
            ((TextView) rootView.findViewById(R.id.prixCoutantPiece_text)).setText(String.valueOf(piece.getPrixCoutantPiece()) + " $");
            ((TextView) rootView.findViewById(R.id.qtyPiece_text)).setText(String.valueOf(piece.getQtyPiece()));
            ((TextView) rootView.findViewById(R.id.typePiece_text)).setText(piece.getTypePiece());
            ((TextView) rootView.findViewById(R.id.categoriePiece_text)).setText(piece.getCategoriePiece());

            return rootView;
        }
    }
}
