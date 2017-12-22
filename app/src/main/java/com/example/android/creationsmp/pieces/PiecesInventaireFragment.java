package com.example.android.creationsmp.pieces;

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

import com.example.android.creationsmp.R;
import com.example.android.creationsmp.main.CustomListViewAdapter;

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
 * This class is the fragment for the inventory of items Pieces
 */

public class PiecesInventaireFragment extends Fragment{

    // An adapter for the collection of objects Pieces
    //private ArrayAdapter<Pieces> inventairePiecesAdapter;
    private CustomListViewAdapter inventairePiecesAdapter;

    // Accesses the GestionPieces class
    private GestionPieces mGestionPieces;

    // Accesses the GestionTypePieces class
    private GestionTypePieces mGestionTypePieces;

    // Accesses the Pieces class
    private Pieces mPiece;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

        // Registers to the EventBus
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Creates a collection of objects Pieces
        mGestionPieces = new GestionPieces(new ArrayList<Pieces>());

        // Creates an instance of GestionTypePieces
        mGestionTypePieces = new GestionTypePieces();

        // Imports a file that contains the inventory of objects Pieces or creates a default object
        this.readInventairePiece();

        // Creates a custom adapter for the collection of objects Pieces
        inventairePiecesAdapter = new CustomListViewAdapter(getActivity(), R.layout.liste_pieces_inventaire, mGestionPieces.getInventairePieces());

        // Shows a floating plus sign to add a new object Pieces to the collection
        FloatingActionButton fab = (FloatingActionButton) container.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PieceAddDetailActivity.class);
                intent.putExtra("requestCode", EventManager.REQUEST_NEW_PIECE);
                intent.putExtra("mGestionPieces", mGestionPieces);
                intent.putExtra("mGestionTypePieces", mGestionTypePieces);
                startActivity(intent);
            }
        });


        // Creates a view to display the collection as a list
        View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

        // Creates a list form the collection and binds it to the adapter
        ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.listview_pieces_inventaire);
        inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

        // Shows the details of an object Pieces when an item from the list is clicked
        inventairePiecesAdapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), PieceViewDetailActivity.class);
                intent.putExtra("position", i);
                intent.putExtra("mGestionPieces", mGestionPieces);
                intent.putExtra("mGestionTypePieces", mGestionTypePieces);
                startActivity(intent);
            }
        });
        // Shows an alert dialog before removing an object Pieces from the collection when an item from the list is clicked longer
        inventairePiecesAdapterView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int i, long id) {
                mPiece = inventairePiecesAdapter.getItem(i);
                ConfirmeSuppDialogFragment confirmeSuppDialogFragment = new ConfirmeSuppDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("piece", mPiece);
                confirmeSuppDialogFragment.setArguments(bundle);
                confirmeSuppDialogFragment.setTargetFragment(PiecesInventaireFragment.this, EventManager.REQUEST_DELETE_PIECE);
                confirmeSuppDialogFragment.show(getFragmentManager(), "dialog");

                return true;
            }
        });

        return rootView;
    }

    /**
     * Returns to parent activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    /**
     * Reads a file containing the collection of objects Pieces previously saved or creates a default object
     */
    private void readInventairePiece(){
        // Creates a collection of objects Pieces
        try {
            FileInputStream inputFile = this.getContext().openFileInput("InventairePiece.ser");
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            mGestionPieces = (GestionPieces) inputStream.readObject();
            inputStream.close();
            inputFile.close();
            Log.v("––> Reading File", "ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.v("––> Reading File", "did not succeed!");
        }
        if(mGestionPieces.getInventairePieces().isEmpty()) {
            // Creates an object Pieces as default if the file is empty or not found
            mGestionTypePieces.addCategorie(new Categories("Perle"));
            mGestionTypePieces.addTypePiece(new TypePieces("Billes"));
            mGestionTypePieces.getCollectionTypePieces().get(0).addCategorie(mGestionTypePieces.getCollectionCategories().get(0));
            Pieces piece = new Pieces(3625, "Pièce Temporaire", "Description temporaire",
                    4, 0.95, 23, mGestionTypePieces.getCollectionTypePieces().get(0),
                    mGestionTypePieces.getCollectionTypePieces().get(0).getCollectionCategories().get(0));
            mGestionPieces.addToInventairePieces(piece);

            Toast.makeText(getActivity(), "#" + piece.getCodePiece() + " " + piece.getNomPiece() + " est ajouté", Toast.LENGTH_SHORT).show();
            this.writeInventairePiece();
        }
    }

    /**
     * Saves the collection of objects Pieces into a file
     */
    private void writeInventairePiece(){
        try {
            FileOutputStream outputFile = this.getContext().openFileOutput("InventairePiece.ser", Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(mGestionPieces);
            outputStream.close();
            outputFile.close();
            Log.v("––> Writng File", "ok");
            if(!mGestionPieces.getInventairePieces().isEmpty()) Log.v("––> File", "has " + mGestionPieces.getInventairePieces().size() + " member(s)");
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.v("––> Writing File", "did not succeed!");
        }
    }

    /**
     * Updates the information when something changes in the collection
     * @param eventIntentController the event intent passed back to the controller
     */
    @Subscribe
    public void onEvent(EventManager.EventIntentController eventIntentController){
        String state = "Annulé";
        int requestCode;
        int resultCode;
        Pieces piece;
        if(eventIntentController.getEventIntentController() != null) {
            Intent intent = eventIntentController.getEventIntentController();
            requestCode = intent.getIntExtra("requestCode", -1);
            resultCode = intent.getIntExtra("resultCode", -1);
            piece = (Pieces) intent.getSerializableExtra("piece");
            if(requestCode == EventManager.REQUEST_NEW_PIECE && resultCode == RESULT_OK) {
                mGestionPieces.addToInventairePieces(piece);
                state = "#" + piece.getCodePiece() + " " + piece.getNomPiece() + " est ajouté";
                Log.v("--> onEvent", "updating controller from add" );
            }
            else if (requestCode == EventManager.REQUEST_MODIFY_PIECE && resultCode == RESULT_OK) {
                int position = intent.getIntExtra("position", -1);
                mGestionPieces.setToInventairePieces(intent.getIntExtra("position", -1), piece);
                state = "#" + piece.getCodePiece() + " " + piece.getNomPiece() + " est modifié";
                Intent detailIntent = new Intent(getActivity(), PieceViewDetailActivity.class);
                detailIntent.putExtra("position", position);
                detailIntent.putExtra("mGestionPieces", mGestionPieces);
                detailIntent.putExtra("mGestionTypePieces", mGestionTypePieces);
                startActivity(detailIntent);
                //EventBus.getDefault().post(new EventManager.EventIntentDetail(detailIntent));
                Log.v("--> onEvent", "updating controller from mod" );
            }
            else if (requestCode == EventManager.REQUEST_DELETE_PIECE && resultCode == RESULT_OK) {
                mGestionPieces.removeFromInventairePieces(piece);
                state = "#" + piece.getCodePiece() + " " + piece.getNomPiece() + " est supprimé";
                Log.v("--> onEvent", "updating controller from mod" );
            }
            inventairePiecesAdapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), state, Toast.LENGTH_SHORT).show();
            this.writeInventairePiece();
        }
    }
}
