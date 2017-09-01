package com.example.android.creationsmp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sylvain on 2017-09-01.
 */

public class CustomListViewAdapter extends ArrayAdapter<PieceModel> {

    private Context context;

    public CustomListViewAdapter(Context context, int resourceId, ArrayList<PieceModel> inventairePieces) {
        super(context, resourceId, inventairePieces);
        this.context = context;
    }

    private class ViewHolder {

        private ImageView imageView;
        private TextView textView;
    }

    /**
     * Gets a view and applies a ViewHolder to format differently
     * @param position the position of the PieceModel object in the collection
     * @param convertView the incoming view
     * @param parent the group
     * @return the reformated view
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        PieceModel piece = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.liste_pieces_inventaire, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.liste_pieces_inventaire_textview);
            holder.imageView = (ImageView) convertView.findViewById(R.id.pieceThumb);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        // Sets the text line of each row
        holder.textView.setText("#" + piece.getCodePiece() + "  " + piece.getNomPiece() + "  " + "( " + piece.getQtyPiece() + " )");
        if(piece.getPhotoPiece() != null) {
            // Sets the resized picture of each row
            holder.imageView.setImageBitmap(setResizedPhotoPiece(piece.getPhotoPiece().getAbsolutePath(), holder.imageView));
        }

        return convertView;
    }

    /**
     * Creates a scaled down version of the picture
     * @param mCurrentPhotoPath the path of the picture
     * @param mImageView the ImageView to place the picture
     * @return scaled down version of the picture
     */
    private Bitmap setResizedPhotoPiece(String mCurrentPhotoPath, ImageView mImageView) {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        // Determine how much to scale down the image
        int scaleFactor = 16;

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        mImageView.setImageBitmap(bitmap);

        return bitmap;
    }
}