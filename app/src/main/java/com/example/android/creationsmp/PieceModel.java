package com.example.android.creationsmp;

import android.media.Image;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sylvain on 2017-04-10.
 */

public class PieceModel implements Serializable {

    private int codePiece;
    private String nomPiece = "";
    private String descriptionPiece = "";
    private int dimensionPiece;
    private double prixCoutantPiece = 0.00;
    private int qtyPiece;
    private String typePiece;
    private String categoriePiece;
    private Image photoPiece;


    /*public PieceModel(int codePiece, String nomPiece, String descriptionPiece, int dimensionPiece, double prixCoutantPiece, int qtyPiece, String typePiece) {
        this.codePiece = codePiece;
        this.nomPiece = nomPiece;
        this.descriptionPiece = descriptionPiece;
        this.dimensionPiece = dimensionPiece;
        this.prixCoutantPiece = prixCoutantPiece;
        this.qtyPiece = qtyPiece;
        this.typePiece = typePiece;
    }*/

    public PieceModel() {

    }

    protected int getCodePiece() {
        return codePiece;
    }

    protected boolean setCodePiece(int codPiece) {
        boolean pass;
        if (codPiece > 0 && codPiece < 9999){
            this.codePiece = codPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    protected String getNomPiece() {
        return nomPiece;
    }

    protected boolean setNomPiece(String nomPiece) {
        boolean pass;
        if (!nomPiece.isEmpty()) {
            this.nomPiece = nomPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    protected String getDescriptionPiece() {
        return descriptionPiece;
    }

    protected boolean setDescriptionPiece(String descPiece) {
        boolean pass;
        if (!descPiece.isEmpty()) {
            this.descriptionPiece = descPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    protected int getDimensionPiece() {
        return dimensionPiece;
    }

    protected boolean setDimensionPiece(int dimPiece) {
        boolean pass;
        if (dimPiece > 3 && dimPiece < 16) {
            this.dimensionPiece = dimPiece;
            pass = true;
        }else
            pass = false;

        return pass;
    }

    protected double getPrixCoutantPiece() {
        return prixCoutantPiece;
    }

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

    protected int getQtyPiece() {
        return qtyPiece;
    }

    protected void setQtyPiece(int qtyPiece) {
        this.qtyPiece = qtyPiece;
    }

    protected Image getPhotoPiece() {
        return photoPiece;
    }

    protected void setPhotoPiece(Image photoPiece) {
        this.photoPiece = photoPiece;
    }

    protected String getTypePiece() {
        return typePiece;
    }

    protected void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    protected String getCategoriePiece() {
        return categoriePiece;
    }

    protected void setCategoriePiece(String categoriePiece) {
        this.categoriePiece = categoriePiece;
    }

    @Override
    public String toString(){
        return "#" + this.getCodePiece() + "  " + this.getNomPiece() + "  " + "[ " + this.getQtyPiece() + " ]";
    }
}