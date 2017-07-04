package com.example.android.creationsmp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sylvain on 2017-06-22.
 */

public class PieceViewFragment extends Fragment {


    // Accède à la classe de liste de pièces
    private InventairePieces inventairePieces;
    // Accède à la classe de pièces
    private PieceModel piece;

    private int position;

    protected static PieceViewFragment create(int pos, int position, PieceModel piece, InventairePieces inventairePieces) {
        PieceViewFragment fragment = new PieceViewFragment();
        Bundle args = new Bundle();
        args.putInt("position", pos);
        args.putSerializable("piece", inventairePieces.getInventairePieces().get(pos));
        args.putSerializable("inventairePieces", inventairePieces);
        fragment.setArguments(args);

        Log.v("Fragment", "Pos: " + pos);
        Log.v("Fragment", "Position: " + position);
        Log.v("Fragment" , "Piece: " + piece.getNomPiece());
        Log.v("Fragment", "Inventaire: " + inventairePieces.getInventairePieces().size());

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

        if(getArguments() != null) {
            position = getArguments().getInt("position");
            piece = (PieceModel) getArguments().getSerializable("piece");
            inventairePieces = (InventairePieces) getArguments().getSerializable("inventairePieces");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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
