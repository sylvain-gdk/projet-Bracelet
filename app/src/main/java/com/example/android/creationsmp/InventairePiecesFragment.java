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
 */

public class InventairePiecesFragment extends Fragment{ //implements ConfirmeSuppDialogFragment.ConfirmSuppressionObserver

    // Adapteur pour la liste de pièces
    private ArrayAdapter<PieceModel> inventairePiecesAdapter;
    // Accède à la classe de liste de pièces
    private InventairePieces inventairePieces;
    // Accède à la classe de pièces
    private PieceModel piece;

    // L'ensemble pièce et inventaire
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Affiche un bouton flotant pour ajouter des pièces
        FloatingActionButton fab = (FloatingActionButton) container.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PieceEditActivity.class);
                intent.putExtra("piece", piece);
                intent.putExtra("inventairePieces", inventairePieces);
                startActivityForResult(intent, 1);
            }
        });

        // Créé une liste de pièces
        inventairePieces = new InventairePieces(new ArrayList<PieceModel>());
        // Importe un fichier qui contient une liste de pièces sauvegardé dans la nouvelle liste "inventairePieces"
        this.readInventairePiece();

        // Créé un adapteur pour la liste de pièces
        inventairePiecesAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.liste_pieces_inventaire,
                R.id.liste_pieces_inventaire_textview,
                inventairePieces.getInventairePieces());

        // Créé la vue de la liste de pièces à partir d'un fragment et l'attache à l'adapteur
        View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

        ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.listview_pieces_inventaire);
        inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

        // Accède à l'activité pour voir une pièce en cliquant sur un item de la liste
        inventairePiecesAdapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                piece = inventairePiecesAdapter.getItem(i);
                Intent intent = new Intent(getActivity(), PieceViewActivity.class);
                intent.putExtra("piece", piece);
                intent.putExtra("inventairePieces", inventairePieces);
                intent.putExtra("position", i);
                startActivityForResult(intent, 1);

                /*piece = inventairePiecesAdapter.getItem(i);
                PieceViewFragment Frag = new PieceViewFragment();
                bundle = new Bundle();
                bundle.putSerializable("piece",piece);
                bundle.putSerializable("inventairePieces",inventairePieces);
                bundle.putInt("position", i);
                Frag.setArguments(bundle);
                Frag.setTargetFragment(InventairePiecesFragment.this, 1);*/

                Log.v("short clicked","pos: " + i);
            }
        });
        // Supprime une pièce en cliquant sur un item de la liste longuement
        inventairePiecesAdapterView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int i, long id) {
                piece = inventairePiecesAdapter.getItem(i);
                ConfirmeSuppDialogFragment dialogFrag = new ConfirmeSuppDialogFragment();
                bundle = new Bundle();
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
     * Option pour revenir au parent et sauvegarder la liste de pièces
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.writeInventairePiece();
        return super.onOptionsItemSelected(item);
    }

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

    private void readInventairePiece(){
        try {
            FileInputStream inputFile = this.getContext().openFileInput("InventairePiece.ser");
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            inventairePieces = (InventairePieces) inputStream.readObject();
            inputStream.close();
            inputFile.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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


    /*@Override
    public void updateResult(int result) {
        this.result = result;
    }*/
}
