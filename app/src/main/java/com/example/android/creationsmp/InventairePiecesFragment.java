package com.example.android.creationsmp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by sylvain on 2017-05-16.
 * This class is the fragment for the inventory of items PieceModel
 */

public class InventairePiecesFragment extends Fragment{

    // An adapter for the collection of objects PieceModel
    //private ArrayAdapter<PieceModel> inventairePiecesAdapter;
    private CustomListViewAdapter inventairePiecesAdapter;

    // Accesses the InventairePieces class
    private InventairePieces inventairePieces;
    // Accesses the PieceModel class
    private PieceModel piece;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Shows a floating plus sign to add a new object PieceModel to the collection
        FloatingActionButton fab = (FloatingActionButton) container.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PieceAddActivity.class);
                intent.putExtra("inventairePieces", inventairePieces);
                startActivity(intent);
            }
        });

        // Creates a collection of objects PieceModel
        inventairePieces = new InventairePieces(new ArrayList<PieceModel>());

        // Imports a file that contains the inventory of objects PieceModel
        this.readInventairePiece();

        // Creates a custom adapter for the collection of objects PieceModel
        inventairePiecesAdapter = new CustomListViewAdapter(getActivity(), R.layout.liste_pieces_inventaire, inventairePieces.getInventairePieces());

        // Creates a view to display the collection as a list
        View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

        // Creates a list form the collection and binds it to the adapter
        ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.listview_pieces_inventaire);
        inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

        // Shows the details of an object PieceModel when an item from the list is clicked
        inventairePiecesAdapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                piece = inventairePiecesAdapter.getItem(i);
                Intent intent = new Intent(getActivity(), PieceViewActivity.class);
                intent.putExtra("position", i);
                intent.putExtra("inventairePieces", inventairePieces);
                startActivity(intent);
            }
        });
        // Shows an alert dialog before removing an object PieceModel from the collection when an item from the list is clicked longer
        inventairePiecesAdapterView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int i, long id) {
                piece = inventairePiecesAdapter.getItem(i);
                ConfirmeSuppDialogFragment confirmeSuppDialogFragment = new ConfirmeSuppDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("piece",piece);
                bundle.putSerializable("inventairePieces",inventairePieces);
                confirmeSuppDialogFragment.setArguments(bundle);
                confirmeSuppDialogFragment.setTargetFragment(InventairePiecesFragment.this, EventManager.REQUEST_DELETE_PIECE);
                confirmeSuppDialogFragment.show(getFragmentManager(), "dialog");

                return true;
            }
        });

        return rootView;
    }


    /**
     * Returns to parent activity and saves the collection of objects PieceModel into a file
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.writeInventairePiece();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Saves the collection of objects PieceModel into a file
     */
    private void writeInventairePiece(){
        try {
            FileOutputStream outputFile = this.getContext().openFileOutput("InventairePiece.ser", Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(inventairePieces);
            outputStream.close();
            outputFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Reads a file containing the collection of objects PieceModel
     */
    private void readInventairePiece(){
        try {
            FileInputStream inputFile = this.getContext().openFileInput("InventairePiece.ser");
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            inventairePieces = (InventairePieces) inputStream.readObject();
            inputStream.close();
            inputFile.close();
            // Creates an object PieceModel as default if the file is empty
            if(inventairePieces.getInventairePieces().size() < 1) {
                Log.v("––> Tried Loading ", "but file is empty... writing default value");
                PieceModel piece = new PieceModel();
                piece.setCodePiece(3625);
                piece.setNomPiece("Pièce Temporaire");
                piece.setDescriptionPiece("Description temporaire");
                piece.setDimensionPiece(4);
                piece.setPrixCoutantPiece(0.95);
                piece.setQtyPiece(23);
                piece.setTypePiece("type");
                piece.setCategoriePiece("categorie");

                inventairePieces.addToInventairePieces(piece);
                inventairePiecesAdapter.notifyDataSetChanged();
                this.writeInventairePiece();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Returns result for an object PieceModel that was requested to be removed
     * also updates the collection information in the fragment
     * @param requestCode the request code
     * @param resultCode the result code that tells how to handle the object
     * @param data the intent that was returned
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == EventManager.REQUEST_DELETE_PIECE && resultCode == RESULT_OK){
            Toast.makeText(getContext(), inventairePieces.removeFromInventairePieces(piece), Toast.LENGTH_SHORT).show();
            inventairePiecesAdapter.notifyDataSetChanged();
            this.writeInventairePiece();
        }
    }

    /**
     * Updates the information in the fragment when the collection has changed
     * @param eventIntent the event intent
     */
    @Subscribe
    public void onEvent(EventManager.EventIntent eventIntent){
        int requestCode;
        PieceModel piece;
        if(eventIntent.getEventIntent() != null) {
            Intent intent = eventIntent.getEventIntent();
            requestCode = intent.getIntExtra("requestCode", -1);
            piece = (PieceModel) intent.getSerializableExtra("piece");
            if(requestCode == EventManager.REQUEST_NEW_PIECE) {
                Toast.makeText(getContext(), inventairePieces.addToInventairePieces(piece), Toast.LENGTH_SHORT).show();
            }
            else if (requestCode == EventManager.REQUEST_MODIFY_PIECE) {
                Toast.makeText(getContext(), inventairePieces.setToInventairePieces(intent.getIntExtra("position", -1), piece), Toast.LENGTH_SHORT).show();
            }
            inventairePiecesAdapter.notifyDataSetChanged();
            this.writeInventairePiece();
        }
    }
}