package com.example.android.creationsmp;

import android.content.Context;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

/**
 * Created by sylvain on 2017-05-16.
 */

public class InventairePiecesFragment extends Fragment {

    // Adapteur pour la liste de pièces
    private ArrayAdapter<PieceModel> inventairePiecesAdapter;
    // Accède à la classe de liste de pièces
    private InventairePieces inventairePieces;
    // Accède à la classe de pièces
    private PieceModel piece;

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
            final int RESULT = 1;
            Intent intent = new Intent(getActivity(), PieceActivityEdit.class);
            intent.putExtra("ajouterPiece", piece);
            startActivityForResult(intent, RESULT);
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
                PieceModel piece = inventairePiecesAdapter.getItem(i);
                Intent intent = new Intent(getActivity(), PieceActivityView.class).putExtra("voirPiece", piece);
                startActivity(intent);
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
        if(resultCode == RESULT_OK) {
            piece = (PieceModel) data.getSerializableExtra("nouvellePiece");
            inventairePieces.addToInventairePieces(piece);
            this.writeInventairePiece();
            inventairePiecesAdapter.notifyDataSetChanged();
            printConfirmerAjout(piece);
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

    private void printConfirmerAjout(PieceModel piece){
        for(int i = 0; i < inventairePieces.getInventairePieces().size(); i++) {
            if(i == inventairePieces.getInventairePieces().indexOf(piece)) {
                String confirm = ("La pièce '" + piece.getNomPiece() + "' est ajouté.");
                Toast.makeText(getContext(), confirm, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
