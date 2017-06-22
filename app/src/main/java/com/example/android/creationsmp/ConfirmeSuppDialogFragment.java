package com.example.android.creationsmp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import static android.app.Activity.RESULT_CANCELED;


/**
 * Created by sylvain on 2017-06-20.
 */

public class ConfirmeSuppDialogFragment extends DialogFragment {

    // Accède à la classe de pièces
    private PieceModel piece;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle args = getArguments();
        piece = (PieceModel) args.get("piece");

        AlertDialog.Builder dialogue = new AlertDialog.Builder(getActivity());

        dialogue.setTitle("Attention!");
        dialogue.setMessage("Êtes-vous certain de vouloir supprimer '" + piece.getNomPiece() + "'?");

        // Add text for a positive button
        dialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                //intent.putExtra("dialog", 2);
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), 2, intent);
                /*ConfirmSuppressionObserver observer = (ConfirmSuppressionObserver) getActivity();
                observer.updateResult(2);*/
            }
        });

        // Add text for a negative button
        dialogue.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                //intent.putExtra("dialog", RESULT_CANCELED);
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), RESULT_CANCELED, intent);
                /*ConfirmSuppressionObserver observer = (ConfirmSuppressionObserver) getActivity();
                observer.updateResult(RESULT_CANCELED);*/
            }

        });

        // Returns the created dialog
        return dialogue.create();

    }

    /*public interface ConfirmSuppressionObserver {
        void updateResult(int result);
    }*/

}
