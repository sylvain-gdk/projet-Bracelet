package com.example.android.creationsmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-04-10.
 * This class is the Inventory of objects PieceModel
 */

public class InventairePieces implements Serializable {

    //Inventory list of objects "piece"
    private ArrayList<PieceModel> inventairePieces;

    /**
     * Builds the inventory of objects "piece"
     * @param inventairePieces the inventory of objects "piece"
     */
    public InventairePieces(ArrayList<PieceModel> inventairePieces) {
        this.inventairePieces = inventairePieces;
    }

    /**
     * Gets the inventory of objects "piece"
     * @return the inventory list of objects "piece"
     */
    protected ArrayList<PieceModel> getInventairePieces() {
        return inventairePieces;
    }

    /**
     * Adds an object "piece" to the inventory
     * @param piece the object to add
     */
    protected void addToInventairePieces(PieceModel piece) {
        inventairePieces.add(piece);
    }

    /**
     * Removes an object "piece" from the inventory
     * @param piece the object to remove
     */
    protected void removeFromInventairePieces(PieceModel piece) {
        inventairePieces.remove(piece);
    }

}
