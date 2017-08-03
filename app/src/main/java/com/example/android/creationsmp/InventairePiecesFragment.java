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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-05-16.
 * This class is the fragment for the inventory of items PieceModel
 */

public class InventairePiecesFragment extends Fragment{

    //An adapter for the inventory of objects "piece"
    private ArrayAdapter<PieceModel> inventairePiecesAdapter;
    //Accesses the InventairePieces class
    private InventairePieces inventairePieces;
    //Accesses the PieceModel class
    private PieceModel piece;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Shows a floating plus sign to add a new object "piece" to the inventory
        FloatingActionButton fab = (FloatingActionButton) container.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Shows the activity to add a new object "piece" to the inventory
                Intent intent = new Intent(getActivity(), PieceEditActivity.class);
                intent.putExtra("piece", piece);
                intent.putExtra("inventairePieces", inventairePieces);
                startActivityForResult(intent, 1);
            }
        });

        //Creates an inventory list of objects "piece"
        inventairePieces = new InventairePieces(new ArrayList<PieceModel>());

        //Imports a file that contains the inventory of objects "piece"
        this.readInventairePiece();

        //Creates an adapter for the inventory of objects "piece"
        inventairePiecesAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.liste_pieces_inventaire,
                R.id.liste_pieces_inventaire_textview,
                inventairePieces.getInventairePieces());

        //Creates a view for the inventory
        View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

        //Creates a list form the inventory and binds it to the adapter
        ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.listview_pieces_inventaire);
        inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

        //Shows the detail activity of an object "piece" when an item from the list is clicked rapidly
        inventairePiecesAdapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                piece = inventairePiecesAdapter.getItem(i);
                Intent intent = new Intent(getActivity(), PieceViewActivity.class);
                intent.putExtra("inventairePieces", inventairePieces);
                intent.putExtra("posClicked", i);
                startActivityForResult(intent, 1);

                Log.v("short clicked","pos: " + i);
            }
        });
        //Shows an alert dialog before removing an object "piece" from the inventory when an item from the list is clicked longer
        inventairePiecesAdapterView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int i, long id) {
                piece = inventairePiecesAdapter.getItem(i);
                ConfirmeSuppDialogFragment dialogFrag = new ConfirmeSuppDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("piece",piece);
                bundle.putSerializable("inventairePieces",inventairePieces);
                dialogFrag.setArguments(bundle);
                dialogFrag.setTargetFragment(InventairePiecesFragment.this, 2);
                dialogFrag.show(getFragmentManager(), "dialog");

                Log.v("long clicked","pos: " + i);

                return true;
            }
        });


        return rootView;
    }


    /**
     * Option to return to parent activity and save the inventory of objects "piece" in a file
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.writeInventairePiece();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Return result for an object "piece" that was handled in another activity: added or removed depending on a code
     * @param requestCode the request code
     * @param resultCode the returning code that tells how to handle the object
     * @param data the intent that was returned
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 1) {
            piece = (PieceModel) data.getSerializableExtra("piece");
            inventairePieces.addToInventairePieces(piece);
            this.writeInventairePiece();
            inventairePiecesAdapter.notifyDataSetChanged();
            printConfirmerState(piece, "ajoutée");
        }
        else if(resultCode == 2){
            inventairePieces.removeFromInventairePieces(piece);
            this.writeInventairePiece();
            inventairePiecesAdapter.notifyDataSetChanged();
            printConfirmerState(piece, "supprimée");
        }
    }

    /**
     * Saves the inventory of objects "piece" in a file
     */
    private void writeInventairePiece(){
        try {
            FileOutputStream outputFile = this.getContext().openFileOutput("InventairePiece.ser", Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(inventairePieces);
            outputStream.close();
            outputFile.close();
            Log.v("––> Wrote to ", "InventairePiece.ser");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.v("––> Cannot write to ", "InventairePiece.ser");
        }
    }

    /**
     * Reads the inventory of objects "piece" from a file
     */
    private void readInventairePiece(){
        try {
            FileInputStream inputFile = this.getContext().openFileInput("InventairePiece.ser");
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            inventairePieces = (InventairePieces) inputStream.readObject();
            inputStream.close();
            inputFile.close();
            Log.v("––> Loaded ", "InventairePiece.ser");
            //Creates an object "piece" as default if the file is empty
            if(inventairePieces.getInventairePieces().size() < 1) {
                Log.v("––> Loaded ", "but file is empty... writing default value");
                PieceModel piece = new PieceModel();
                piece.setCodePiece(3625);
                piece.setNomPiece("Pièce Temporaire");
                piece.setDescriptionPiece("Description temporaire");
                piece.setDimensionPiece(3);
                piece.setPrixCoutantPiece(0.95);
                piece.setQtyPiece(23);
                piece.setTypePiece("type");
                piece.setCategoriePiece("categorie");
                inventairePieces.addToInventairePieces(piece);
                this.writeInventairePiece();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.v("––> Cannot read ", "InventairePiece.ser");
        }
    }

    /**
     * Prints the status of an object "piece" in a Toast style
     * @param piece the object from the inventory
     * @param state the status of the object
     */
    private void printConfirmerState(PieceModel piece, String state){
        String confirm = state;
        for(int i = 0; i < inventairePieces.getInventairePieces().size(); i++) {
            if(i == inventairePieces.getInventairePieces().indexOf(piece) && state.equals("ajoutée")) {
                confirm = ("La pièce '" + piece.getNomPiece() + "' est " + state + ".");
            }else if(i == inventairePieces.getInventairePieces().indexOf(piece) && state.equals("supprimée")) {
                confirm = ("La pièce '" + piece.getNomPiece() + "' est " + state + ".");
            }
        }
        Toast.makeText(getContext(), confirm, Toast.LENGTH_SHORT).show();
    }

}
