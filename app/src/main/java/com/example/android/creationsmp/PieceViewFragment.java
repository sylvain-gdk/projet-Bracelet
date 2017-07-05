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
 * This is the fragment for the details of each item
 */

public class PieceViewFragment extends Fragment {


    // Accède à la classe de liste de pièces
    private InventairePieces inventairePieces;
    // Accède à la classe de pièces
    private PieceModel piece;

    private int positionViewPager;

    private int positionClicked;


    protected static PieceViewFragment create(int posViewPager, int posClicked, InventairePieces inventairePieces) {
        PieceViewFragment fragment = new PieceViewFragment();
        Bundle args = new Bundle();
        args.putInt("posViewPager", posViewPager); //the position from the overriden method getItem() in PieceViewActivity$InventairePiecesPagerAdapter
        args.putInt("posClicked", posClicked); //the position when it was clicked in inventairePiecesFragment
        args.putSerializable("inventairePieces", inventairePieces); //the collection of items PieceModel (inventory)
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getArguments() != null) {
            positionClicked = getArguments().getInt("posClicked");
            positionViewPager = getArguments().getInt("posViewPager");
            inventairePieces = (InventairePieces) getArguments().getSerializable("inventairePieces");
            piece = inventairePieces.getInventairePieces().get(positionViewPager);
        }


        Log.v("Fragment", "Pos View: " + positionViewPager);
        Log.v("Fragment", "Pos Clicked: " + positionClicked);
        Log.v("Fragment" , "Piece: " + piece.getNomPiece());
        Log.v("Fragment", "Inventaire: " + inventairePieces.getInventairePieces().size());

        View rootView = inflater.inflate(R.layout.fragment_piece_view, container, false);

        getActivity().setTitle(piece.getNomPiece()); //the title in the action bar
        
        // the item card
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
