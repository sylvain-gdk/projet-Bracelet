package com.example.android.creationsmp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sylvain on 2017-05-16.
 */

public class InventairePieceFragment extends Fragment {

    private ArrayAdapter<String> inventairePiecesAdapter;

    public InventairePieceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String[] Pieces = {"Pierre de Lune", "Perle Noire",
                "Os Rond", "Pierre de Lave ronde", "Pierre de Lave longue", "Bois d'Acajou"};

        ArrayList<String> piece = new ArrayList<>(Arrays.asList(Pieces));

        inventairePiecesAdapter = new ArrayAdapter<String>(
                getActivity(),
                R.layout.list_item_pieces_inventaire,
                R.id.list_item_pieces_inventaire_textview,
                piece);

        View rootView = inflater.inflate(R.layout.fragment_pieces_inventaire, container, false);

        ListView inventairePiecesAdapterView = (ListView) rootView.findViewById(R.id.fragment_pieces_inventaire_listview);

        inventairePiecesAdapterView.setAdapter(inventairePiecesAdapter);

        return rootView;

    }

}
