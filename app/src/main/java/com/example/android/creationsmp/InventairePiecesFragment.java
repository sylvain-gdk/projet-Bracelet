package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by sylvain on 2017-05-16.
 */

public class InventairePiecesFragment extends Fragment {

    private ArrayAdapter<PieceModel> inventairePiecesAdapter;
    private InventairePieces inventairePieces;

    public InventairePiecesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);

        Intent intent = getActivity().getIntent();
        inventairePieces = (InventairePieces) intent.getSerializableExtra(Intent.EXTRA_TEXT);
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*String[] Pieces = {"Pierre de Lune", "Perle Noire",
                "Os Rond", "Pierre de Lave ronde", "Pierre de Lave longue", "Bois d'Acajou"};*/

        //ArrayList<PieceModel> Pieces = new ArrayList();

        PieceModel piece = new PieceModel();
        piece.setCodePiece(3652);
        piece.setNomPiece("Pierre de lune");
        piece.setDescriptionPiece("une très belle pierre");
        piece.setDimensionPiece(3);
        piece.setPrixCoutantPiece(5);
        piece.setQtyPiece(32);
        piece.setTypePiece("Bille");
        piece.setCategoriePiece("Plastique");

        inventairePieces.addToInventairePieces(piece);

        inventairePiecesAdapter = new ArrayAdapter<PieceModel>(
                getActivity(),
                R.layout.liste_pieces_inventaire,
                R.id.liste_pieces_inventaire_textview, inventairePieces.getInventairePieces());

        View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

        ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.listview_pieces_inventaire);

        inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

        inventairePiecesAdapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*String item = "Vous avez choisi '" + inventairePiecesAdapter.getItem(i) + "'";
                Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show();*/

                PieceModel piece = inventairePiecesAdapter.getItem(i);

                Intent intent = new Intent(getActivity(), PieceActivityView.class);
                intent.putExtra(Intent.EXTRA_TEXT, piece);
                startActivity(intent);

            }
        });

        return rootView;

    }


}
