package com.example.android.creationsmp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import org.greenrobot.eventbus.EventBus;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


/**
 * Created by sylvain on 2017-06-20.
 * This class is to provide an alert before removing an object from the inventory
 */

public class ConfirmeSuppDialogFragment extends DialogFragment {

    // Accesses the PieceModel class
    private PieceModel piece;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Gets the object "piece" from a bundle
        Bundle args = getArguments();
        piece = (PieceModel) args.get("piece");

        // Creates an alert dialog
        AlertDialog.Builder dialogue = new AlertDialog.Builder(getActivity());
        dialogue.setTitle("Attention!");
        dialogue.setMessage("Êtes-vous certain de vouloir supprimer #" + piece.getCodePiece() + " " + piece.getNomPiece() + "?");

        // Adds text for a positive answer button
        dialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Returns result from the button to an intent
                Intent intent = new Intent();
                intent.putExtra("requestCode", EventManager.REQUEST_DELETE_PIECE);
                intent.putExtra("resultCode", RESULT_OK);
                intent.putExtra("piece", piece);

                EventBus.getDefault().post(new EventManager.EventIntentController(intent));
            }
        });

        // Adds text for a negative answer button
        dialogue.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Returns result from the button to an intent
                Intent intent = new Intent();
                intent.putExtra("requestCode", EventManager.REQUEST_DELETE_PIECE);
                intent.putExtra("resultCode", RESULT_CANCELED);
                intent.putExtra("piece", piece);

                EventBus.getDefault().post(new EventManager.EventIntentController(intent));
            }
        });

        // Returns the created dialog
        return dialogue.create();
    }

}
