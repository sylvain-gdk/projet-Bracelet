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
    private String nomPiece;
    private String descriptionPiece;
    private int dimensionPiece;
    private double prixCoutantPiece;
    private int qtyPiece;
    private String typePiece;
    private String categoriePiece;
    private Image photoPiece;


    /**
     * Accède au le code pièce
     * @return le code de la pièce
     */
    protected int getCodePiece() {
        return codePiece;
    }

    /**
     * Setter pour le code pièce
     * Vérifie si le code est entre 1 et 4 chiffres
     * @param codPiece le code de la pièce
     * @return true si valide
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
     * Accède au nom de la pièce
     * @return le nom de la pièce
     */
    protected String getNomPiece() {
        return nomPiece;
    }

    /**
     * Setter pour le nom de la pièce
     * Vérifie si le nom n'est pas vide
     * @param nomPiece le nom de la pièce
     * @return true si valide
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
     * Accède à la description de la pièce
     * @return la description de la pièce
     */
    protected String getDescriptionPiece() {
        return descriptionPiece;
    }

    /**
     * Setter pour la description de la pièce
     * Vérifie si la description n'est pas vide
     * @param descPiece la description de la pièce
     * @return true si valide
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
     * Accède à la dimension de la pièce
     * @return la dimension de la pièce
     */
    protected int getDimensionPiece() {
        return dimensionPiece;
    }

    /**
     * Setter pour la dimension de la pièce
     * Vérifie si la dimension n'est pas vide
     * @param dimPiece la dimension de la pièce
     * @return true si valide
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
     * Accède au prix coutant de la pièce
     * @return le prix coutant de la pièce
     */
    protected double getPrixCoutantPiece() {
        return prixCoutantPiece;
    }


    /**
     * Setter pour le prix coutant de la pièce
     * Vérifie si le prix coutant respecte un format 0.00
     * @param prixCoutantPiece le prix coutant de la pièce
     * @return true si valide
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
     * Accède à la quantité de pièces en inventaire
     * @return la quantité de pièces en inventaire
     */
    protected int getQtyPiece() {
        return qtyPiece;
    }

    /**
     * Setter pour la quantité de pièces en inventaire
     * @param qtyPiece la quantité de pièces en inventaire
     */
    protected void setQtyPiece(int qtyPiece) {
        this.qtyPiece = qtyPiece;
    }

    /**
     * Accède à la photo de la pièce
     * @return la photo de la pièce
     */
    protected Image getPhotoPiece() {
        return photoPiece;
    }

    /**
     * Setter pour la photo de la pièce
     * @param photoPiece la photo de la pièce
     */
    protected void setPhotoPiece(Image photoPiece) {
        this.photoPiece = photoPiece;
    }

    /**
     * Accède au type de pièce
     * @return le type de pièce
     */
    protected String getTypePiece() {
        return typePiece;
    }

    /**
     * Setter pour le type de pièce
     * @param typePiece le type de pièce
     */
    protected void setTypePiece(String typePiece) {
        this.typePiece = typePiece;
    }

    /**
     * Accède à la catégorie de la pièce
     * @return la catégorie de la pièce
     */
    protected String getCategoriePiece() {
        return categoriePiece;
    }

    /**
     * Setter pour la catégorie de la pièce
     * @param categoriePiece la catégorie de la pièce
     */
    protected void setCategoriePiece(String categoriePiece) {
        this.categoriePiece = categoriePiece;
    }

    /**
     * formatage d'une rangée dans la liste de pièce
     * @return le format de la rangée
     */
    @Override
    public String toString(){
        return "#" + this.getCodePiece() + "  " + this.getNomPiece() + "  " + "[ " + this.getQtyPiece() + " ]";
    }
}