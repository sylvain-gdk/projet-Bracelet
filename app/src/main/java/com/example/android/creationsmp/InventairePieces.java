package com.example.android.creationsmp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-04-10.
 */

public class InventairePieces implements Serializable {

    //liste de pièce en inventaire
    private ArrayList<PieceModel> inventairePieces;

    /**
     * Construit une liste de pièce
     * @param inventairePieces liste de pièce en inventaire
     */
    public InventairePieces(ArrayList<PieceModel> inventairePieces) {
        this.inventairePieces = inventairePieces;
    }

    /**
     * Accède à la liste de pièce en inventaire
     * @return la liste de pièce en inventaire
     */
    protected ArrayList<PieceModel> getInventairePieces() {
        return inventairePieces;
    }

    /**
     * Ajoute une pièce à l'inventaire
     * @param piece la pièce à ajouter
     */
    protected void addToInventairePieces(PieceModel piece) {
        inventairePieces.add(piece);
    }

    /**
     * Retire une pièce de l'inventaire
     * @param piece la pièce à retirer
     */
    protected void removeFromInventairePieces(PieceModel piece) {
        inventairePieces.remove(piece);
    }

}
