package com.example.android.creationsmp.pieces;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-04-10.
 * This class is the Inventory of objects Pieces
 */

public class GestionPieces implements Serializable {

    // The collection of objects Pieces
    private ArrayList<Pieces> inventairePieces;

    /**
     * Builds the collection of objects Pieces
     * @param inventairePieces the inventory of objects Pieces
     */
    public GestionPieces(ArrayList<Pieces> inventairePieces) {
        this.inventairePieces = inventairePieces;
    }

    /**
     * Gets the collection of objects Pieces
     * @return the inventory list of objects "piece"
     */
    public ArrayList<Pieces> getInventairePieces() {
        return inventairePieces;
    }

    /**
     * Adds an object Pieces to the collection
     * @param piece the object to add
     */
    public void addToInventairePieces(Pieces piece) {
        inventairePieces.add(piece);
    }

    /**
     * Sets an object Pieces in the collection (replace)
     * @param position the position of the object
     * @param piece the object to replace
     */
    public  void setToInventairePieces(int position, Pieces piece) {
        inventairePieces.set(position, piece);
    }

    /**
     * Removes an object Pieces from the collection
     * @param piece the object to remove
     */
    public void removeFromInventairePieces(Pieces piece) {
        inventairePieces.remove(piece);
    }
}