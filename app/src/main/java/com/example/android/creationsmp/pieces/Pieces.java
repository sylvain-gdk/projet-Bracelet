package com.example.android.creationsmp.pieces;

import com.example.android.creationsmp.bracelets.Bracelets;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sylvain on 2017-04-10.
 * This is the Model for each "piece"
 */

public class Pieces implements Serializable {

    private int mCodePiece = 0;
    private String mNomPiece = "";
    private String mDescriptionPiece = "";
    private int mDimensionPiece = 0;
    private double mPrixCoutantPiece = 0.00;
    private int mQtyPiece = 0;
    private File mPhotoPiece = null;
    private GestionPieces mGestionPieces;
    private TypePieces mTypePiece = null;
    private Categories mCategoriePiece = null;
    private List<Bracelets> mCollectionBraceletsComp;


    public Pieces(){

    }

    /**
     * Constructor for default object
     *
     * @param mCodePiece
     * @param mNomPiece
     * @param mDescriptionPiece
     * @param mDimensionPiece
     * @param mPrixCoutantPiece
     * @param mQtyPiece
     * @param mTypePiece
     * @param mCategoriePiece
     */
    public Pieces(int mCodePiece, String mNomPiece, String mDescriptionPiece,
                  int mDimensionPiece, double mPrixCoutantPiece, int mQtyPiece,
                  TypePieces mTypePiece, Categories mCategoriePiece) {
        this.mCodePiece = mCodePiece;
        this.mNomPiece = mNomPiece;
        this.mDescriptionPiece = mDescriptionPiece;
        this.mDimensionPiece = mDimensionPiece;
        this.mPrixCoutantPiece = mPrixCoutantPiece;
        this.mQtyPiece = mQtyPiece;
        this.mTypePiece = mTypePiece;
        this.mCategoriePiece = mCategoriePiece;
        this.mCollectionBraceletsComp = new ArrayList<Bracelets>();
    }

    /**
     *
     * @param mCodePiece
     * @param mNomPiece
     * @param mDescriptionPiece
     * @param mDimensionPiece
     * @param mPrixCoutantPiece
     * @param mQtyPiece
     * @param mTypePiece
     * @param mCategoriePiece
     * @param mPhotoPiece
     * @param mCollectionBraceletsComp
     */
    /*public Pieces(int mCodePiece, String mNomPiece, String mDescriptionPiece,
                  int mDimensionPiece, double mPrixCoutantPiece, int mQtyPiece,
                  String mTypePiece, String mCategoriePiece, File mPhotoPiece,
                  List<Bracelets> mCollectionBraceletsComp) {
        this.mCodePiece = mCodePiece;
        this.mNomPiece = mNomPiece;
        this.mDescriptionPiece = mDescriptionPiece;
        this.mDimensionPiece = mDimensionPiece;
        this.mPrixCoutantPiece = mPrixCoutantPiece;
        this.mQtyPiece = mQtyPiece;
        this.mTypePiece = mTypePiece;
        this.mCategoriePiece = mCategoriePiece;
        this.mPhotoPiece = mPhotoPiece;
        this.mCollectionBraceletsComp = mCollectionBraceletsComp;
    }*/

    /**
     * Gets the code
     * @return the code
     */
    public int getCodePiece() {
        return mCodePiece;
    }

    /**
     * Sets the code
     * Checks if the code has between 1 and 4 digits
     * @param codPiece the code
     * @return true if valid
     */
    public boolean setCodePiece(int codPiece) {
        boolean pass;
        if (codPiece > 0 && codPiece < 10000){
            this.mCodePiece = codPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the name
     * @return the name
     */
    public String getNomPiece() {
        return mNomPiece;
    }

    /**
     * Sets the name
     * Checks if name is not empty
     * @param nomPiece the name
     * @return true if valid
     */
    public boolean setNomPiece(String nomPiece) {
        boolean pass;
        if (nomPiece.length() < 21) {
            this.mNomPiece = nomPiece.substring(0, 1).toUpperCase() + nomPiece.substring(1);
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the description
     * @return the description
     */
    public String getDescriptionPiece() {
        return mDescriptionPiece;
    }

    /**
     * Sets the description
     * Checks if description is not empty
     * @param descPiece the description
     * @return true if valid
     */
    public boolean setDescriptionPiece(String descPiece) {
        boolean pass;
        if (descPiece.length() < 65) {
            this.mDescriptionPiece = descPiece.substring(0, 1).toUpperCase() + descPiece.substring(1);
            pass = true;
        }else pass = false;

        return pass;
    }

    /**
     * Gets the dimension
     * @return the dimension
     */
    public int getDimensionPiece() {
        return mDimensionPiece;
    }

    /**
     * Sets the dimension
     * Checks if dimension is not empty
     * @param dimPiece the dimension
     * @return true if valid
     */
    public boolean setDimensionPiece(int dimPiece) {
        boolean pass;
        if (dimPiece > 3 && dimPiece < 16) {
            this.mDimensionPiece = dimPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }


    /**
     * Gets the price
     * @return the price
     */
    public double getPrixCoutantPiece() {
        return mPrixCoutantPiece;
    }


    /**
     * Sets the price
     * Checks if price is in format 0.00
     * @param prixCoutantPiece the price
     * @return true if valid
     */
    public boolean setPrixCoutantPiece(double prixCoutantPiece) {
        boolean pass;
        Pattern p = Pattern.compile("^(?:0|[1-9]\\d{0,1})(?:\\.\\d{1,2})");
        Matcher m = p.matcher(String.valueOf(prixCoutantPiece));
        if (prixCoutantPiece > 0) {
            this.mPrixCoutantPiece = prixCoutantPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the quantity in inventory
     * @return the quantity in inventory
     */
    public int getQtyPiece() {
        return mQtyPiece;
    }

    /**
     * Sets the quantity in inventory
     * @param mQtyPiece the quantity in inventory
     */
    public void setQtyPiece(int mQtyPiece) {
        this.mQtyPiece = mQtyPiece;
    }

    /**
     * Gets the picture
     * @return the picture
     */
    public File getPhotoPiece() {
        return mPhotoPiece;
    }

    /**
     * Sets the picture
     * @param mPhotoPiece the picture
     */
    public void setPhotoPiece(File mPhotoPiece) {
        this.mPhotoPiece = mPhotoPiece;
    }

    /**
     * Gets the type
     * @return the type
     */
    public TypePieces getTypePiece() {
        return mTypePiece;
    }

    /**
     * Sets the type
     * @param typePiece the type
     */
    public boolean setTypePiece(TypePieces typePiece) {
        boolean pass;
        if (!typePiece.equals(null)) {
            this.mTypePiece = typePiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the category
     * @return the category
     */
    public Categories getCategoriePiece() {
        return mCategoriePiece;
    }

    /**
     * Sets the category
     * @param categoriePiece the category
     */
    public boolean setCategoriePiece(Categories categoriePiece) {
        boolean pass;
        if (!categoriePiece.equals(null)) {
            this.mCategoriePiece = categoriePiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    public GestionPieces getGestionPiece() {
        return mGestionPieces;
    }


    /**
     * Gets a list of Bracelets that contains this instance
     * @return the list of Bracelets that contains this instance
     */
    public List<Bracelets> getCollectionBraceletsComp() {
        return mCollectionBraceletsComp;
    }

    /**
     * Adds an instance of Bracelets that contains this instance
     * @param mBracelet the instance of Bracelets that contains this instance
     */
    public void addBraceletComp(Bracelets mBracelet) {
        mCollectionBraceletsComp.add(mBracelet);
    }

    /**
     * Removes an instance of Bracelets that contains this instance
     * @param mBracelet the instance of Bracelets that contains this instance
     */
    public void removeBraceletComp(Bracelets mBracelet) {
        mCollectionBraceletsComp.remove(mBracelet);
    }

    /**
     * Formats a row in the collection's list
     * @return the formatted row
     */
    @Override
    public String toString(){
        return "#" + this.getCodePiece() + "  " + this.getNomPiece() + "  " + "( " + this.getQtyPiece() + " )";
    }

    /**
     * Compares the code
     * @param piece the object
     * @return not sure about that one
     */
    public int compareTo(Pieces piece) {

        return Integer.compare(mCodePiece, piece.mCodePiece);
    }
}