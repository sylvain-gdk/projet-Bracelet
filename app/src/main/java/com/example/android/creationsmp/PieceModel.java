package com.example.android.creationsmp;

import android.media.Image;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sylvain on 2017-04-10.
 * This is the Model
 */

public class PieceModel implements Serializable {

    private int codePiece;
    private String nomPiece;
    private String descriptionPiece;
    private int dimensionPiece;
    private double prixCoutantPiece;
    private int qtyPiece;
    private String typePiece;
    private String categoriePiece;
    private Image photoPiece;


    /**
     * Gets the code
     * @return the code
     */
    protected int getCodePiece() {
        return codePiece;
    }

    /**
     * Sets the code
     * Checks if the code has between 1 and 4 digits
     * @param codPiece the code
     * @return true if valid
     */
    protected boolean setCodePiece(int codPiece) {
        boolean pass;
        if (codPiece > 0 && codPiece < 9999){
            this.codePiece = codPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the name
     * @return the name
     */
    protected String getNomPiece() {
        return nomPiece;
    }

    /**
     * Sets the name
     * Checks if name is not empty
     * @param nomPiece the name
     * @return true if valid
     */
    protected boolean setNomPiece(String nomPiece) {
        boolean pass;
        if (!nomPiece.isEmpty()) {
            this.nomPiece = nomPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the description
     * @return the description
     */
    protected String getDescriptionPiece() {
        return descriptionPiece;
    }

    /**
     * Sets the description
     * Checks if description is not empty
     * @param descPiece the description
     * @return true if valid
     */
    protected boolean setDescriptionPiece(String descPiece) {
        boolean pass;
        if (!descPiece.isEmpty()) {
            this.descriptionPiece = descPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the dimension
     * @return the dimension
     */
    protected int getDimensionPiece() {
        return dimensionPiece;
    }

    /**
     * Sets the dimension
     * Checks if dimension is not empty
     * @param dimPiece the dimension
     * @return true if valid
     */
    protected boolean setDimensionPiece(int dimPiece) {
        boolean pass;
        if (dimPiece > 3 && dimPiece < 16) {
            this.dimensionPiece = dimPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }


    /**
     * Gets the price
     * @return the price
     */
    protected double getPrixCoutantPiece() {
        return prixCoutantPiece;
    }


    /**
     * Sets the price
     * Checks if price is in format 0.00
     * @param prixCoutantPiece the price
     * @return true if valid
     */
    protected boolean setPrixCoutantPiece(double prixCoutantPiece) {
        boolean pass;
        Pattern p = Pattern.compile("^(?:0|[1-9]\\d{0,1})(?:\\.\\d{1,2})");
        Matcher m = p.matcher(String.valueOf(prixCoutantPiece));
        if (prixCoutantPiece > 0) { //m.matches()
            this.prixCoutantPiece = prixCoutantPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    /**
     * Gets the quantity in inventory
     * @return the quantity in inventory
     */
    protected int getQtyPiece() {
        return qtyPiece;
    }

    /**
     * Sets the quantity in inventory
     * @param qtyPiece the quantity in inventory
     */
    protected void setQtyPiece(int qtyPiece) {
        this.qtyPiece = qtyPiece;
    }

    /**
     * Gets the picture
     * @return the picture
     */
    protected Image getPhotoPiece() {
        return photoPiece;
    }

    /**
     * Sets the picture
     * @param photoPiece the picture
     */
    protected void setPhotoPiece(Image photoPiece) {
        this.photoPiece = photoPiece;
    }

    /**
     * Gets the type
     * @return the type
     */
    protected String getTypePiece() {
        return typePiece;
    }

    /**
     * Sets the type
     * @param typePiece the type
     */
    protected void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    /**
     * Gets the category
     * @return the category
     */
    protected String getCategoriePiece() {
        return categoriePiece;
    }

    /**
     * Sets the category
     * @param categoriePiece the category
     */
    protected void setCategoriePiece(String categoriePiece) {
        this.categoriePiece = categoriePiece;
    }

    /**
     * Formats a row in the inventory list
     * @return the row format
     */
    @Override
    public String toString(){
        return "#" + this.getCodePiece() + "  " + this.getNomPiece() + "  " + "[ " + this.getQtyPiece() + " ]";
    }
}