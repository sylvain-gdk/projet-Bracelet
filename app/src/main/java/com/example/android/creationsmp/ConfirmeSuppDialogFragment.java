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
 * This class is to provide an alert before removing an object from the inventory
 */

public class ConfirmeSuppDialogFragment extends DialogFragment {

    //Accesses the PieceModel class
    private PieceModel piece;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Gets the object "piece" from a bundle
        Bundle args = getArguments();
        piece = (PieceModel) args.get("piece");

        //Creates an alert dialog
        AlertDialog.Builder dialogue = new AlertDialog.Builder(getActivity());
        dialogue.setTitle("Attention!");
        dialogue.setMessage("Êtes-vous certain de vouloir supprimer '" + piece.getNomPiece() + "'?");

        //Adds text for a positive answer button
        dialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Returns result from the button
                Intent intent = new Intent();
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), 2, intent);
            }
        });

        //Adds text for a negative answer button
        dialogue.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Returns result from the button
                Intent intent = new Intent();
                getTargetFragment().onActivityResult(
                        getTargetRequestCode(), RESULT_CANCELED, intent);
            }

        });

        //Returns the created dialog
        return dialogue.create();

    }

}
