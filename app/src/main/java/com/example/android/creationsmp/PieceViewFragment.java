package com.example.android.creationsmp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sylvain on 2017-06-22.
 * This is the fragment for the details of each items
 */

public class PieceViewFragment extends Fragment {

    //Accesses the InventairePieces class
    private InventairePieces inventairePieces;
    //Accesses the PieceModel class
    private PieceModel piece;
    //The object's position in the inventory list
    private int positionClicked;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        //Gets the inventory from an intent
        Intent intent = getActivity().getIntent();
        positionClicked = intent.getIntExtra("posClicked", -1);
        inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_piece_view, container, false);

        //Gets the item position from a bundle
        Bundle args = getArguments();

        if(args != null) {
            positionClicked = args.getInt("position");
            piece = inventairePieces.getInventairePieces().get(positionClicked);

            //Sets the object's details
            ((TextView) rootView.findViewById(R.id.invCount_text)).setText(String.valueOf("(" + (positionClicked + 1) + "/" + inventairePieces.getInventairePieces().size()) + ")");
            ((TextView) rootView.findViewById(R.id.codePiece_text)).setText(String.valueOf("# " + piece.getCodePiece()));
            ((TextView) rootView.findViewById(R.id.nomPiece_text)).setText(piece.getNomPiece());
            ((TextView) rootView.findViewById(R.id.descriptionPiece_text)).setText(piece.getDescriptionPiece());
            ((TextView) rootView.findViewById(R.id.dimensionPiece_text)).setText(String.valueOf(piece.getDimensionPiece()) + " mm");
            ((TextView) rootView.findViewById(R.id.prixCoutantPiece_text)).setText(String.valueOf(piece.getPrixCoutantPiece()) + " $");
            ((TextView) rootView.findViewById(R.id.qtyPiece_text)).setText(String.valueOf(piece.getQtyPiece()));
            ((TextView) rootView.findViewById(R.id.typePiece_text)).setText(piece.getTypePiece());
            ((TextView) rootView.findViewById(R.id.categoriePiece_text)).setText(piece.getCategoriePiece());

            //If there's an image, use it, otherwise use default image
            if(piece.getPhotoPiece() != null){
                ImageView imageView = (ImageView) rootView.findViewById(R.id.photoPiece_image);
                imageView.setImageBitmap(getPhotoPieceSize(piece.getPhotoPiece().getAbsolutePath()));
            }
        }
        return rootView;
    }

    /**
     * Creates a scaled down version of the picture
     * @param mCurrentPhotoPath the path of the picture
     * @return scaled down version of the picture
     */
    private Bitmap getPhotoPieceSize(String mCurrentPhotoPath) {
        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // How much to scale down the image
        int scaleFactor = 4;

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        return bitmap;
    }
}
