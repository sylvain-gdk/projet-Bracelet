package com.example.android.creationsmp.pieces;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.android.creationsmp.R;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sylvain on 2017-04-10.
 * This class is where the objects Pieces are created/modified
 */

public class PieceAddDetailActivity extends AppCompatActivity {

    // The request code for the picture of the object Pieces
    private static final int REQUEST_TAKE_PHOTO = 5;

    // Accesses the GestionPieces class
    private GestionPieces mGestionPieces;

    // Accesses the GestionTypePieces class
    private GestionTypePieces mGestionTypePieces;

    // The picture of the object Pieces
    private File mPhotoPieceFile = null;

    private EditText mCodePiece, mNomPiece, mDescriptionPiece, mDimensionPiece, mPrixCoutantPiece, mQtyPiece;
    private Spinner mTypePieceSpinner, mCategorieSpinner;
    private TypePieces mTypePiece = null;
    private Categories mCategorie = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_add);

        // Sets the details of the object Pieces
        mCodePiece = (EditText) findViewById(R.id.codePiece_edit);
        mNomPiece = (EditText) findViewById(R.id.nomPiece_edit);
        mDescriptionPiece = (EditText) findViewById(R.id.descriptionPiece_edit);
        mDimensionPiece = (EditText) findViewById(R.id.dimensionPiece_edit);
        mPrixCoutantPiece = (EditText) findViewById(R.id.prixCoutantPiece_edit);
        mQtyPiece = (EditText) findViewById(R.id.qtyPiece_edit);
        mTypePieceSpinner = (Spinner) findViewById(R.id.typePiece_edit);
        mCategorieSpinner = (Spinner) findViewById(R.id.categoriePiece_edit);

        // Gets the collection from an intent
        Intent intent = getIntent();
        mGestionPieces = (GestionPieces) intent.getSerializableExtra("mGestionPieces");
        mGestionTypePieces = (GestionTypePieces) intent.getSerializableExtra("mGestionTypePieces");

        addItemsToTypeSpinner();
        addListenerToTypeSpinner();
    }

    /**
     * Adds items to the TypePieceSpinner
     */
    private void addItemsToTypeSpinner() {
        ArrayAdapter<TypePieces> typeSpinnerAdapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, mGestionTypePieces.getCollectionTypePieces());
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypePieceSpinner.setAdapter(typeSpinnerAdapter);
    }

    /**
     * Adds a listener to the TypePieceSpinner
     */
    private void addListenerToTypeSpinner() {
        // Sets the TypePieces on item selection
        mTypePieceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                mTypePiece = (TypePieces) adapterView.getItemAtPosition(pos);
                addItemsToCategorieSpinner();
                addListenerToCategorieSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mTypePiece = null;
            }
        });
    }

    /**
     * Adds items to the CategorieSpinner
     */
    private void addItemsToCategorieSpinner() {
        ArrayAdapter<Categories> categorieSpinnerAdapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,
                        mGestionTypePieces.getCollectionTypePieces().get(mGestionTypePieces.getCollectionTypePieces().indexOf(mTypePiece)).getCollectionCategories());
        categorieSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategorieSpinner.setAdapter(categorieSpinnerAdapter);
    }

    /**
     * Adds a listener to the CategorieSpinner
     */
    private void addListenerToCategorieSpinner() {
        // Sets the Categorie on item selection
        mCategorieSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                mCategorie = (Categories) adapterView.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mCategorie = null;
            }
        });
    }

    /**
     * Creates the intent of taking a picture
     */
    private void takePictureIntent(){
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (photoIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                mPhotoPieceFile = createImageFile();
            } catch (IOException ex) {
                Log.v("Error taking picture", String.valueOf(ex));
            }
            // Continue only if the File was successfully created
            if (mPhotoPieceFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", mPhotoPieceFile);
                photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(photoIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * Formats the image taken by the camera
     * @return the formatted image
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    /**
     * Button to take a picture
     * @param view the view
     */
    public void prendrePhotoListener(View view){
        takePictureIntent();
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

    /**
     * Sets a thumbnail of the picture taken by the camera from an intent
     * @param requestCode the requested code
     * @param resultCode the result code
     * @param data the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView photoPieceThumb = (ImageView) this.findViewById(R.id.cameraIcon);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setResizedPhotoPiece(mPhotoPieceFile.getAbsolutePath(), photoPieceThumb);
        }
    }

    /**
     * Button to add the new object Pieces to the collection
     * tests for entry validity
     * @param view the view
     */
    public void ajouterPieceListener(View view) {
        Pieces piece = new Pieces();
        // Field validation
        boolean sameCode = false;
        for (int i = 0; i < mGestionPieces.getInventairePieces().size(); i++) {
            if (Integer.compare(Integer.parseInt(String.valueOf(mCodePiece.getText())),
                    mGestionPieces.getInventairePieces().get(i).getCodePiece()) == 0)
                sameCode = true;
        }
        if(!sameCode) {
            try {
                if (!piece.setCodePiece(Integer.parseInt(mCodePiece.getText().toString()))) {
                    showErrorHighlightField("Le code doit avoir entre 1 et 4 chiffres", mCodePiece);
                } else if (!piece.setNomPiece(mNomPiece.getText().toString())) {
                    showErrorHighlightField("La nom est trop long (max 20 char.)", mNomPiece);
                } else if (!piece.setDescriptionPiece(mDescriptionPiece.getText().toString())) {
                    showErrorHighlightField("La description est trop longue (max 64 char.)", mDescriptionPiece);
                } else if (!piece.setDimensionPiece(Integer.parseInt(mDimensionPiece.getText().toString()))) {
                    showErrorHighlightField("La dimension doit être entre 4 et 15 mm", mDimensionPiece);
                } else if (!piece.setPrixCoutantPiece(Double.parseDouble(mPrixCoutantPiece.getText().toString()))) {
                    showErrorHighlightField("Le prix ne peut être 0 et doit être au format 0.00", mPrixCoutantPiece);
                } else if (piece.getCodePiece() > 0) {
                    piece.setQtyPiece(Integer.parseInt(mQtyPiece.getText().toString()));
                    piece.setTypePiece(mTypePiece);
                    piece.setCategoriePiece(mCategorie);
                    if (mPhotoPieceFile != null) {
                        piece.setPhotoPiece(mPhotoPieceFile);
                    }

                    Intent intent = new Intent();
                    intent.putExtra("requestCode", EventManager.REQUEST_NEW_PIECE);
                    intent.putExtra("resultCode", RESULT_OK);
                    intent.putExtra("piece", piece);

                    EventBus.getDefault().post(new EventManager.EventIntentController(intent));

                    finish();
                }
            } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                // Shows the error on screen with Snackbar
                Snackbar.make(view, "Vous devez remplir tous les champs", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }else
            showErrorHighlightField("Ce code existe déjà", mCodePiece);
    }


    /**
     * Selects the EditText field where an error happened and makes the keyboard visible
     * @param error the error that was triggered
     * @param textField the field where the error happened
     */
    private void showErrorHighlightField(String error, EditText textField){
        textField.requestFocus();
        textField.setError(error);
        InputMethodManager im = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            // Will only trigger if no physical keyboard is open
            im.showSoftInput(textField, 0);
        }
    }

    /**
     * Button to cancel and return to the previous activity
     * @param view the view
     */
    public void annulerAjouterPieceListener(View view) {
        finish();
    }

}