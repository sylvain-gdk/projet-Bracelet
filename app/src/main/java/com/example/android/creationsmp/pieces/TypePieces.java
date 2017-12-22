package com.example.android.creationsmp.pieces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylvain on 2017-12-11.
 */

public class TypePieces implements Serializable {

    private String mNomTypePieces;
    private List<Categories> mCollectionCategories;
    private List<Pieces> mCollectionPiecesComp;
    private GestionTypePieces mGestionTypePiece;

    /**
     * Default constructor that creates a new list of Categories
     */
    public TypePieces(String mNomTypePieces) {
        this.mNomTypePieces = mNomTypePieces;
        this.mCollectionCategories = new ArrayList<>();
    }

    /**
     * Constructor that creates a list of Categories and Pieces
     */
    public TypePieces(String mNomTypePieces, GestionTypePieces mGestionTypePiece) {
        this.mNomTypePieces = mNomTypePieces;
        this.mGestionTypePiece = mGestionTypePiece;
        this.mCollectionCategories = new ArrayList<>();
        this.mCollectionPiecesComp = new ArrayList<>();
    }

    /**
     * Constructor that has a list of Categories and creates a list of Pieces
     */
    public TypePieces(String mNomTypePieces, List<Categories> mCollectionCategories) {
        this.mNomTypePieces = mNomTypePieces;
        this.mCollectionCategories = mCollectionCategories;
        this.mCollectionPiecesComp = new ArrayList<>();
    }

    /**
     * Constructor that has a list of Categories and Pieces
     */
    public TypePieces(String mNomTypePieces, List<Categories> mCollectionCategories, List<Pieces> mCollectionPiecesComposed) {
        this.mNomTypePieces = mNomTypePieces;
        this.mCollectionCategories = mCollectionCategories;
        this.mCollectionPiecesComp = mCollectionPiecesComposed;
    }

    /**
     * Gets the name of a "type de piece"
     * @return the name for a "type de piece"
     */
    public String getNomTypePieces() {
        return mNomTypePieces;
    }

    /**
     * Sets the name for a "type de piece"
     * @param mNomTypePieces the name of a "type de piece"
     */
    public void setmNomTypePieces(String mNomTypePieces) {
        this.mNomTypePieces = mNomTypePieces;
    }

    /**
     * Gets the list of Categories contained in this instance
     * @return the list of Categories contained in this instance
     */
    public List<Categories> getCollectionCategories() {
        return mCollectionCategories;
    }

    /**
     * Adds an instance of Categories to the list
     * @param mCategorie the instance of Categories
     */
    public void addCategorie(Categories mCategorie) {
        mCollectionCategories.add(mCategorie);
    }

    /**
     * Removes an instance of Categories from the list
     * @param mCategorie the instance of Categories
     */
    public void removeCategorie(Categories mCategorie) {
        mCollectionCategories.remove(mCategorie);
    }


    /**
     * Get the list of Pieces that contains this instance
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


    @Override
    public String toString(){
        return mNomTypePieces;
    }

}