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
    // The state of an object PieceModel
    private String state = "";

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
    protected String addToInventairePieces(PieceModel piece) {
        state = "#" + piece.getCodePiece() + " " + piece.getNomPiece() + " est ajouté";
        try{
            inventairePieces.add(piece);
        }catch (IndexOutOfBoundsException ex){
            state = "Une erreur s'est produite";
            ex.printStackTrace();
        }
        return state;
    }

    /**
     * Sets an object PieceModel in the collection (replace)
     * @param position the position of the object
     * @param piece the object to replace
     */
    protected  String setToInventairePieces(int position, PieceModel piece) {
        state = "#" + piece.getCodePiece() + " " + piece.getNomPiece() + " est modifié";
        try{
            inventairePieces.set(position, piece);
        }catch (IndexOutOfBoundsException ex){
            state = "Une erreur s'est produite";
            ex.printStackTrace();
        }
        return state;
    }

    /**
     * Removes an object PieceModel from the collection
     * @param piece the object to remove
     */
    protected String removeFromInventairePieces(PieceModel piece) {
        state = "#" + piece.getCodePiece() + " " + piece.getNomPiece() + " est supprimé";
        try{
            inventairePieces.remove(piece);
        }catch (IndexOutOfBoundsException ex){
            state = "Une erreur s'est produite";
            ex.printStackTrace();
        }
        return state;
    }
}