package com.example.android.creationsmp.pieces;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sylvain on 2017-12-19.
 */

public class GestionTypePieces implements Serializable {

    private List<Categories> mCollectionCategories;
    private List<TypePieces> mCollectionTypePieces;

    /**
     * Default constructor
     */
    public GestionTypePieces() {
        this.mCollectionCategories = new ArrayList<>();
        this.mCollectionTypePieces = new ArrayList<>();

        // JUST POUR FIN DE TESTS
        this.addCategorie(new Categories("Perle"));
        this.addTypePiece(new TypePieces("Billes"));
        this.getCollectionTypePieces().get(0).addCategorie(this.getCollectionCategories().get(0));

        this.addCategorie(new Categories("Crystal"));
        this.getCollectionTypePieces().get(0).addCategorie(this.getCollectionCategories().get(1));

        this.addCategorie(new Categories("Metal"));
        this.addTypePiece(new TypePieces("Spacers"));
        this.getCollectionTypePieces().get(1).addCategorie(this.getCollectionCategories().get(2));
    }

    /**
     * @param mTypePiece
     */
    public void addTypePiece(TypePieces mTypePiece) {
        this.mCollectionTypePieces.add(mTypePiece);
    }

    /**
     * @param mTypePiece
     */
    public void removeTypePiece(TypePieces mTypePiece) {
        this.mCollectionTypePieces.remove(mTypePiece);
    }

    /**
     *
     */
    public void getTypePiece() {
        // TODO implement here
    }

    /**
     * @param mTypePiece
     */
    public void setTypePiece(TypePieces mTypePiece) {
        // TODO implement here
    }

    /**
     *
     */
    public void saveCollectionTypePieces() {
        // TODO implement here
    }

    /**
     *
     */
    public List<TypePieces> getCollectionTypePieces() {
        return mCollectionTypePieces;
    }

    /**
     * @param mCategorie
     */
    public void addCategorie(Categories mCategorie) {
        this.mCollectionCategories.add(mCategorie);
    }

    /**
     * @param mCategorie
     */
    public void removeCategorie(Categories mCategorie) {
        this.mCollectionCategories.remove(mCategorie);
    }

    /**
     *
     */
    public void getCategorie() {
        // TODO implement here
    }

    /**
     * @param mCategorie
     */
    public void setCategorie(Categories mCategorie) {
        // TODO implement here
    }

    /**
     *
     */
    public void saveCollectionCategories() {
        // TODO implement here
    }

    /**
     *
     */
    public List<Categories> getCollectionCategories() {
        return mCollectionCategories;
    }
}

