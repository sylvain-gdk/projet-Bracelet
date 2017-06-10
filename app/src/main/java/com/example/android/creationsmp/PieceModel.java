package com.example.android.creationsmp;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by sylvain on 2017-04-10.
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

    public int getCodePiece() {
        return codePiece;
    }

    public void setCodePiece(int codePiece) {
        this.codePiece = codePiece;
    }

    public String getNomPiece() {
        return nomPiece;
    }

    public void setNomPiece(String nomPiece) {
        this.nomPiece = nomPiece;
    }

    public String getDescriptionPiece() {
        return descriptionPiece;
    }

    public void setDescriptionPiece(String descPiece) {
        this.descriptionPiece = descPiece;
    }

    public int getDimensionPiece() {
        return dimensionPiece;
    }

    public void setDimensionPiece(int dimensionPiece) {
        this.dimensionPiece = dimensionPiece;
    }

    public double getPrixCoutantPiece() {
        return prixCoutantPiece;
    }

    public void setPrixCoutantPiece(double prixCoutantPiece) {
        this.prixCoutantPiece = prixCoutantPiece;
    }

    public int getQtyPiece() {
        return qtyPiece;
    }

    public void setQtyPiece(int qtyPiece) {
        this.qtyPiece = qtyPiece;
    }

    public Image getPhotoPiece() {
        return photoPiece;
    }

    public void setPhotoPiece(Image photoPiece) {
        this.photoPiece = photoPiece;
    }

    public String getTypePiece() {
        return typePiece;
    }

    public void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    public String getCategoriePiece() {
        return categoriePiece;
    }

    public void setCategoriePiece(String categoriePiece) {
        this.categoriePiece = categoriePiece;
    }

    @Override
    public String toString(){
        return "#" + this.getCodePiece() + "  " + this.getNomPiece() + "  " + "[ " + this.getQtyPiece() + " ]";
    }
}