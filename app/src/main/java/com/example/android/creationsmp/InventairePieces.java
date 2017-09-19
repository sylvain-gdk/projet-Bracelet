package com.example.android.creationsmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-04-10.
 * This class is the Inventory of objects PieceModel
 */

public class InventairePieces implements Serializable {

    // The collection of objects PieceModel
    private ArrayList<PieceModel> inventairePieces;

    /**
     * Builds the collection of objects PieceModel
     * @param inventairePieces the inventory of objects PieceModel
     */
    public InventairePieces(ArrayList<PieceModel> inventairePieces) {
        this.inventairePieces = inventairePieces;
    }

    /**
     * Gets the collection of objects PieceModel
     * @return the inventory list of objects "piece"
     */
    protected ArrayList<PieceModel> getInventairePieces() {
        return inventairePieces;
    }

    /**
     * Adds an object PieceModel to the collection
     * @param piece the object to add
     */
    protected void addToInventairePieces(PieceModel piece) {
        inventairePieces.add(piece);
    }

    /**
     * Sets an object PieceModel in the collection (replace)
     * @param position the position of the object
     * @param piece the object to replace
     */
    protected  void setToInventairePieces(int position, PieceModel piece) {
        inventairePieces.set(position, piece);
    }

    /**
     * Removes an object PieceModel from the collection
     * @param piece the object to remove
     */
    protected void removeFromInventairePieces(PieceModel piece) {
        inventairePieces.remove(piece);
    }
}