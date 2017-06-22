package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sylvain on 2017-06-22.
 */

public class PieceViewFragment extends Fragment {

    private TextView codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece, typePiece, categoriePiece;

    // Accède à la classe de liste de pièces
    private InventairePieces inventairePieces;
    // Accède à la classe de pièces
    private PieceModel piece;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    /**
     * Option pour revenir au parent
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*Intent intent = new Intent();
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_CANCELED, intent);*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        piece = (PieceModel) intent.getSerializableExtra("piece");
        inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");

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
