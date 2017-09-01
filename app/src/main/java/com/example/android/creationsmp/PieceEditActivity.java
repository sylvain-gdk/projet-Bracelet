package com.example.android.creationsmp;

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
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sylvain on 2017-04-10.
 * This class is where the objects PieceModel are created/modified
 */

public class PieceEditActivity extends AppCompatActivity {

    // The request code for the picture of the object PieceModel
    static final int REQUEST_TAKE_PHOTO = 5;

    // Accesses the InventairePieces class
    private InventairePieces inventairePieces;
    // Accesses the PieceModel class
    private PieceModel piece;
    // The object's position in the collection
    private int positionClicked;
    // The picture of an object PieceModel
    private File photoPieceFile = null;

    private EditText codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece;
    private Spinner typePiece, categoriePiece;
    private String type, categorie;

    // The position in the spinners
    private int typePiecePos;
    private int catPiecePos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_edit);

        // Creates a collection of objects PieceModel
        this.inventairePieces = new InventairePieces(new ArrayList<PieceModel>());

        // Sets the details of the object PieceModel
        codePiece = (EditText) findViewById(R.id.codePiece_edit);
        nomPiece = (EditText) findViewById(R.id.nomPiece_edit);
        descriptionPiece = (EditText) findViewById(R.id.descriptionPiece_edit);
        dimensionPiece = (EditText) findViewById(R.id.dimensionPiece_edit);
        prixCoutantPiece = (EditText) findViewById(R.id.prixCoutantPiece_edit);
        qtyPiece = (EditText) findViewById(R.id.qtyPiece_edit);
        typePiece = (Spinner) findViewById(R.id.typePiece_edit);
        categoriePiece = (Spinner) findViewById(R.id.categoriePiece_edit);

        // Gets the collection and object's position from an intent
        Intent intent = getIntent();
        positionClicked = intent.getIntExtra("position", -1);
        inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");

        // Sets text on EditText fields from an intent (PieceViewFragment)
        piece = inventairePieces.getInventairePieces().get(positionClicked);

        codePiece.setText(String.valueOf(piece.getCodePiece()), TextView.BufferType.EDITABLE);
        nomPiece.setText(piece.getNomPiece(), TextView.BufferType.EDITABLE);
        descriptionPiece.setText(piece.getDescriptionPiece(), TextView.BufferType.EDITABLE);
        dimensionPiece.setText(String.valueOf(piece.getDimensionPiece()), TextView.BufferType.EDITABLE);
        prixCoutantPiece.setText(String.valueOf(String.valueOf(piece.getPrixCoutantPiece())), TextView.BufferType.EDITABLE);
        qtyPiece.setText(String.valueOf(piece.getQtyPiece()), TextView.BufferType.EDITABLE);
        photoPieceFile = piece.getPhotoPiece();

        if(photoPieceFile != null){
            onActivityResult(5, RESULT_OK, intent);
        }

        addItemsToTypeSpinner();
        addListenerToTypeSpinner();

        addItemsToCategorieSpinner();
        addListenerToCategorieSpinner();
    }

    /**
     * Adds items to the Type spinner
     */
    public void addItemsToTypeSpinner() {
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter
                .createFromResource(this, R.array.type_piece, android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typePiece.setAdapter(typeSpinnerAdapter);
        if(!typePiece.equals(null)){
            typePiecePos = typeSpinnerAdapter.getPosition(piece.getTypePiece());
            typePiece.setSelection(typePiecePos);
        }
    }

    /**
     * Adds a listener to the Type spinner
     */
    public void addListenerToTypeSpinner() {
        typePiece = (Spinner) findViewById(R.id.typePiece_edit);

        // Sets the type on item selection
        typePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(pos).toString();
                type = itemSelectedInSpinner;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type = piece.getTypePiece();
            }
        });
    }

    /**
     * Adds items to the Categorie spinner
     */
    public void addItemsToCategorieSpinner() {
        ArrayAdapter<CharSequence> categorieSpinnerAdapter = ArrayAdapter
                .createFromResource(this, R.array.categorie_piece, android.R.layout.simple_spinner_item);
        categorieSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriePiece.setAdapter(categorieSpinnerAdapter);
        if(!categoriePiece.equals(null)) {
            catPiecePos = categorieSpinnerAdapter.getPosition(piece.getCategoriePiece());
            categoriePiece.setSelection(catPiecePos);
        }
    }

    /**
     * Adds a listener to the Categorie spinner
     */
    public void addListenerToCategorieSpinner() {
        categoriePiece = (Spinner) findViewById(R.id.categoriePiece_edit);

        // Sets the categorie on item selection
        categoriePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(pos).toString();
                categorie = itemSelectedInSpinner;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                categorie = piece.getCategoriePiece();
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
                photoPieceFile = createImageFile();
            } catch (IOException ex) {
                Log.v("Error taking picture", String.valueOf(ex));
            }
            // Continue only if the File was successfully created
            if (photoPieceFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoPieceFile);
                photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(photoIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * Formats the image taken by the camera
     * @return the image taken by the camera
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        //T he path to store pictures taken from the camera
        String photoPath;
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Saves a file: path for use with ACTION_VIEW intents
        photoPath = image.getAbsolutePath();
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
            setResizedPhotoPiece(photoPieceFile.getAbsolutePath(), photoPieceThumb);
        }
    }

    /**
     * Button to add the new object PieceModel to the collection
     * tests if each entries are valid
     * @param view the view
     */
    public void modifierPieceListener(View view) {
        PieceModel piece = new PieceModel();
        // Field validation
        try {
            if (!piece.setCodePiece(Integer.parseInt(codePiece.getText().toString()))) {
                showErrorHighlightField("Le code doit avoir entre 1 et 4 chiffres", codePiece);
            } else if (!piece.setNomPiece(nomPiece.getText().toString())) {
                showErrorHighlightField("Le nom de la pièce ne peut être vide", nomPiece);
            } else if (!piece.setDescriptionPiece(descriptionPiece.getText().toString())) {
                showErrorHighlightField("La description de la pièce ne peut être vide", descriptionPiece);
            } else if (!piece.setDimensionPiece(Integer.parseInt(dimensionPiece.getText().toString()))) {
                showErrorHighlightField("La dimension doit être entre 4 et 15 mm", dimensionPiece);
            } else if (!piece.setPrixCoutantPiece(Double.parseDouble(prixCoutantPiece.getText().toString()))) {
                showErrorHighlightField("Le prix ne peut être 0 et doit être au format 0.00", prixCoutantPiece);
            } else if (piece.getCodePiece() > 0) {
                piece.setQtyPiece(Integer.parseInt(qtyPiece.getText().toString()));
                piece.setTypePiece(type);
                piece.setCategoriePiece(categorie);
                if (photoPieceFile != null)
                    piece.setPhotoPiece(photoPieceFile);

                Intent intent = new Intent();
                intent.putExtra("requestCode", EventManager.REQUEST_MODIFY_PIECE);
                intent.putExtra("piece", piece);
                intent.putExtra("position", positionClicked);
                intent.putExtra("inventairePieces", inventairePieces);

                EventBus.getDefault().post(new EventManager.EventIntent(intent));

                finish();
            }
        } catch (NumberFormatException e) {
            // Shows the error on screen with Snackbar
            Snackbar.make(view, "Vous devez remplir tous les champs", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }


    /**
     * Selects the field where an error happened and makes the keyboard visible
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
     * Button to cancel
     * Returns to the main activity on cancel
     * @param view the view
     */
    public void annulerAjouterPieceListener(View view) {
        finish();
    }

}