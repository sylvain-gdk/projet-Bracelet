package com.example.android.creationsmp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class InventairePiecesActivity extends AppCompatActivity {

    private InventairePieces inventairePieces;
    private Pieces piece;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieces_inventaire);


        //Intent activityThatCalled = getIntent();
        //piece = (Pieces) activityThatCalled.getSerializableExtra("pieceView");

        /*String[] favoriteShows = {"Pierre de Lune", "Perle Noire",
                "Os Rond", "Pierre de Lave ronde", "Pierre de Lave longue", "Bois d'Acajou"};


        ListAdapter inventairePiecesAdapter = new ArrayAdapter<Pieces>(this, android.R.layout.simple_list_item_1, inventairePieces.getInventairePieces());

        ListView inventairePiecesView = (ListView) findViewById(R.id.inventairePiecesView);

        inventairePiecesView.setAdapter(inventairePiecesAdapter);

        inventairePiecesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String nomPiece = "You selected " + String.valueOf(adapterView.getItemAtPosition(i));
                //Toast.makeText(InventairePiecesActivity.this, tvShowPicked, Toast.LENGTH_SHORT).show();

                montrerPieceListener(String.valueOf(adapterView.getItemAtPosition(i)));

            }
        });*/

    }

    /**
     * Montre la pièce
     */
    public void montrerPieceListener(String item) {

        Pieces piece = new Pieces();

        piece.setCodePiece(3245);
        piece.setNomPiece(item);
        piece.setDescriptionPiece("N/A");
        piece.setDimensionPiece(3);
        piece.setPrixCoutantPiece(15);
        piece.setQtyPiece(32);
        piece.setTypePiece("N/A");

        //inventaireP.addToInventairePieces(piece);

        final int result = 1;
        Intent pieceIntent = new Intent(this, PiecesActivityView.class).putExtra("pieceView", piece);
        startActivityForResult(pieceIntent, result);

        finish();

    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

            String[] Piece = {"Pierre de Lune", "Perle Noire",
                    "Os Rond", "Pierre de Lave ronde", "Pierre de Lave longue", "Bois d'Acajou"};

            ArrayList<String> piece = new ArrayList<>(Arrays.asList(Piece));

            ListAdapter inventairePiecesAdapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.list_item_pieces_inventaire, R.id.list_item_pieces_inventaire_textview, piece);

            ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.fragment_pieces_inventaire_listview);

            inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

            return rootView;
        }
    }

}
