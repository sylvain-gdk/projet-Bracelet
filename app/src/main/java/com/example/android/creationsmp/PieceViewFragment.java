package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by sylvain on 2017-06-22.
 * This is the fragment for the details of each items
 */

public class PieceViewFragment extends Fragment {

    private OnSwipeTouchListener onSwipeTouchListener;

    //Accesses the InventairePieces class
    private InventairePieces inventairePieces;
    //Accesses the PieceModel class
    private PieceModel piece;
    //The object's position in the inventory list
    private int positionClicked;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_piece_view, null, false);

        //Receives the intent for the inventory and item position
        Intent intent = getActivity().getIntent();
        inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");
        positionClicked = (int) intent.getSerializableExtra("posClicked");
        piece = inventairePieces.getInventairePieces().get(positionClicked);

        updateCard(rootView);

        /**
         * Implements the swipe gesture for left/right
         */
        rootView.setOnTouchListener(new OnSwipeTouchListener(this.getContext()){
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();

                Log.v("Fragment", "onSwipeRight - ");

                if (positionClicked > 0) {
                    positionClicked--;
                    piece = inventairePieces.getInventairePieces().get(positionClicked);
                    
                    getView().startAnimation(AnimationUtils.loadAnimation(
                            getContext(), R.anim.slide_from_left
                    ));

                    updateCard(rootView);

                }
            }
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();

                Log.v("Fragment", "onSwipeLeft + ");

                if(positionClicked < inventairePieces.getInventairePieces().size()-1) {
                    positionClicked++;
                    piece = inventairePieces.getInventairePieces().get(positionClicked);

                    getView().startAnimation(AnimationUtils.loadAnimation(
                            getContext(), R.anim.slide_from_right
                    ));

                    updateCard(rootView);

                }
            }
        });

        return rootView;
    }

    /**
     * Updates the details of the object
     * @param view the working view
     */
    public void updateCard(View view){
        //Sets the title in the action bar
        getActivity().setTitle(piece.getNomPiece() + "   " + (positionClicked + 1) + "/" + inventairePieces.getInventairePieces().size());

        //The object's details
        ((TextView) view.findViewById(R.id.codePiece_text)).setText(String.valueOf("# " + piece.getCodePiece()));
        ((TextView) view.findViewById(R.id.nomPiece_text)).setText(piece.getNomPiece());
        ((TextView) view.findViewById(R.id.descriptionPiece_text)).setText(piece.getDescriptionPiece());
        ((TextView) view.findViewById(R.id.dimensionPiece_text)).setText(String.valueOf(piece.getDimensionPiece()) + " mm");
        ((TextView) view.findViewById(R.id.prixCoutantPiece_text)).setText(String.valueOf(piece.getPrixCoutantPiece()) + " $");
        ((TextView) view.findViewById(R.id.qtyPiece_text)).setText(String.valueOf(piece.getQtyPiece()));
        ((TextView) view.findViewById(R.id.typePiece_text)).setText(piece.getTypePiece());
        ((TextView) view.findViewById(R.id.categoriePiece_text)).setText(piece.getCategoriePiece());

        Log.v("Fragment", "Pos Clicked: " + positionClicked);
        Log.v("Fragment" , "Piece: " + piece.getNomPiece());
        Log.v("Fragment", "Inventaire: " + inventairePieces.getInventairePieces().size());
    }

}
