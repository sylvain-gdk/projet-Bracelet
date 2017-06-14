package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sylvain on 2017-05-16.
 */

public class InventairePiecesFragment extends Fragment {

    private ArrayAdapter<PieceModel> inventairePiecesAdapter;
    private InventairePieces inventairePieces;
    private PieceModel piece;

    public InventairePiecesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FloatingActionButton fab = (FloatingActionButton) container.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                final int result = 1;
                Intent intent = new Intent(getActivity(), PieceActivityEdit.class).putExtra("to PieceActivityEdit", piece);
                startActivityForResult(intent, result);
            }
        });

        inventairePieces = new InventairePieces(new ArrayList<PieceModel>());

        //ajouterDebug();

        inventairePiecesAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.liste_pieces_inventaire,
                R.id.liste_pieces_inventaire_textview,
                inventairePieces.getInventairePieces());

        View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

        ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.listview_pieces_inventaire);

        inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

        inventairePiecesAdapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PieceModel piece = inventairePiecesAdapter.getItem(i);
                startActivity(new Intent(getActivity(), PieceActivityView.class).putExtra(Intent.EXTRA_TEXT, piece));
            }
        });

        return rootView;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    
    protected void ajouterPiece(PieceModel piece){
        inventairePieces.addToInventairePieces(piece);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        piece = (PieceModel) data.getSerializableExtra("to InventairePieceFragment");
        inventairePieces.addToInventairePieces(piece);
        inventairePiecesAdapter.notifyDataSetChanged();
        printConfirmerAjout(piece);
    }


    private void printConfirmerAjout(PieceModel piece){
        for(int i = 0; i < inventairePieces.getInventairePieces().size(); i++) {
            if(i == inventairePieces.getInventairePieces().indexOf(piece)) {
                String confirm = ("La pièce '" + piece.getNomPiece() + "' est ajouté.");
                Toast.makeText(getContext(), confirm, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ajouterDebug(){
        piece = new PieceModel();
        piece.setCodePiece(3652);
        piece.setNomPiece("Pierre de lune");
        piece.setDescriptionPiece("une très belle pierre");
        piece.setDimensionPiece(3);
        piece.setPrixCoutantPiece(5);
        piece.setQtyPiece(32);
        piece.setTypePiece("Bille");
        piece.setCategoriePiece("Plastique");

        inventairePieces.addToInventairePieces(piece);
    }

    private void printListDebug(){
        for(int i = 0; i < inventairePieces.getInventairePieces().size(); i++){
            String confirm = ("La pièce '" + inventairePieces.getInventairePieces().get(i).getNomPiece() + "' est dans l'inventaire.");
            Toast.makeText(getContext(), confirm, Toast.LENGTH_LONG).show();
        }
    }


}
