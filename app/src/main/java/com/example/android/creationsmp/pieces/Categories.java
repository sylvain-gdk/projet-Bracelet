package com.example.android.creationsmp.pieces;

/**
 * Created by sylvain on 2017-04-10.
 * This is the Model for each "categorie"
 */

import java.io.Serializable;
import java.util.List;

public class Categories implements Serializable {

    private String mNomCategorie;
    private TypePieces mTypePieceComp;
    private List<Pieces> mCollectionPiecesComp;
    private GestionTypePieces mGestionTypePiece;


    /**
     * Default constructor
     */
    public Categories(String mNomCategorie) {
        this.mNomCategorie = mNomCategorie;
    }

    /**
     * Gets the name of a category
     * @return the name of a category
     */
    public String getNomCategorie() {
        return mNomCategorie;
    }

    /**
     * Sets the name of a category
     * @param mNomCategorie the name of a category
     */
    public void setNomCategorie(String mNomCategorie) {
        this.mNomCategorie = mNomCategorie;
    }

    /**
     * Gets the instance of TypePieces that contains this instance
     * @return the instance of TypePieces that contains this instance
     */
    public TypePieces getTypePieceComp() {
        return mTypePieceComp;
    }

    /**
     * Sets the instance of TypePieces that contains this instance
     * @param mTypePiece the instance of TypePieces that contains this instance
     */
    public void setTypePieceComp(TypePieces mTypePiece) {
        this.mTypePieceComp = mTypePiece;
    }

    /**
     * Gets the list of Pieces that contains this instance
     * @return the list of Pieces that contains this instance
     */
    public List<Pieces> getCollectionPiecesComp() {
        return mCollectionPiecesComp;
    }

    /**
     * Adds an instance of Pieces that contains this instance
     * @param mPiece the instance of Pieces that contains this instance
     */
    public void addPieceComp(Pieces mPiece) {
        mCollectionPiecesComp.add(mPiece);
    }

    /**
     * Removes an instance of Pieces that contains this instance
     * @param mPiece the instance of Pieces that contains this instance
     */
    public void removePieceComp(Pieces mPiece) {
        mCollectionPiecesComp.remove(mPiece);
    }

}