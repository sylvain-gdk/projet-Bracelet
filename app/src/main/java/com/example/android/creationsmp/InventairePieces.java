package com.example.android.creationsmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-04-10.
 */

public class InventairePieces implements Serializable {

    private ArrayList<Pieces> inventairePieces;
    private Pieces piece;

    public InventairePieces() {
        inventairePieces = new ArrayList<>();
    }

    public ArrayList<Pieces> getInventairePieces() {
        return inventairePieces;
    }

    public void addToInventairePieces(Pieces piece) {
        inventairePieces.add(piece);
    }

    public void removeFromInventairePieces(Pieces piece) {
        inventairePieces.remove(piece);
    }

}
