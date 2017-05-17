package com.example.android.creationsmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-04-10.
 */

public class InventairePieces implements Serializable {

    private ArrayList<PieceModel> inventairePieces;

    public InventairePieces() {
        inventairePieces = new ArrayList<>();
    }

    public ArrayList<PieceModel> getInventairePieces() {
        return inventairePieces;
    }

    public void addToInventairePieces(PieceModel piece) {
        inventairePieces.add(piece);
    }

    public void removeFromInventairePieces(PieceModel piece) {
        inventairePieces.remove(piece);
    }

}
