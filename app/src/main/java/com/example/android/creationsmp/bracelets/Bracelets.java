package com.example.android.creationsmp.bracelets;

import android.media.Image;

import com.example.android.creationsmp.pieces.Pieces;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sylvain on 2017-04-10.
 */

public class Bracelets implements Serializable {

    ArrayList<Pieces> bracelet;

    private int codeBracelet;
    private String nomBracelet;
    private String descriptionBracelet;
    private int dimensionBracelet;
    private double prixCoutantBracelet;
    private int qtyBracelet;
    private Image photoBracelet;
    private String typeBracelet;

    public Bracelets() {}

    public ArrayList<Pieces> getBracelet() {
        return bracelet;
    }

    public void addBracelet(Pieces piece) {
        bracelet = new ArrayList();
        bracelet.add(piece);
    }

    public void removeBracelet(Pieces piece) {
        bracelet.remove(piece);
    }

    public int getCodeBracelet() {
        return codeBracelet;
    }

    public void setCodeBracelet(int codeBracelet) {
        this.codeBracelet = codeBracelet;
    }

    public String getNomBracelet() {
        return nomBracelet;
    }

    public void setNomBracelet(String nomBracelet) {
        this.nomBracelet = nomBracelet;
    }

    public String getDescriptionBracelet() {
        return descriptionBracelet;
    }

    public void setDescriptionBracelet(String descriptionBracelet) {
        this.descriptionBracelet = descriptionBracelet;
    }

    public int getDimBracelet() {
        return dimensionBracelet;
    }

    public void setDimBracelet(int dimBracelet) {
        this.dimensionBracelet = dimBracelet;
    }

    public double getPrixCoutantBracelet() {
        return prixCoutantBracelet;
    }

    public void setPrixCoutantBracelet(double prixCoutantBracelet) {
        this.prixCoutantBracelet = prixCoutantBracelet;
    }

    public int getQtyBracelet() {
        return qtyBracelet;
    }

    public void setQtyBracelet(int qtyBracelet) {
        this.qtyBracelet = qtyBracelet;
    }

    public Image getPhotoBracelet() {
        return photoBracelet;
    }

    public void setPhotoBracelet(Image photoBracelet) {
        this.photoBracelet = photoBracelet;
    }

    public String getTypeBracelet() {
        return typeBracelet;
    }

    public void setTypeBracelet(String typeBracelet) {
        this.typeBracelet = typeBracelet;
    }
}
