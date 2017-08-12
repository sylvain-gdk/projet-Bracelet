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

    //Accesses the InventairePieces class
    private InventairePieces inventairePieces;

    private File photoPiece = null;

    private EditText codePiece, nomPiece, descriptionPiece, dimensionPiece, prixCoutantPiece, qtyPiece;
    private Spinner typePiece, categoriePiece;
    private String type, categorie;

    static final int REQUEST_TAKE_PHOTO = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_edit);

        //Creates an inventory list of objects "piece"
        this.inventairePieces = new InventairePieces(new ArrayList<PieceModel>());

        //Gets the inventory list from an intent
        Intent intent = this.getIntent();
        inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");

        //Sets the details of the object "piece"
        codePiece = (EditText) findViewById(R.id.codePiece_edit);
        nomPiece = (EditText) findViewById(R.id.nomPiece_edit);
        descriptionPiece = (EditText) findViewById(R.id.descriptionPiece_edit);
        dimensionPiece = (EditText) findViewById(R.id.dimensionPiece_edit);
        prixCoutantPiece = (EditText) findViewById(R.id.prixCoutantPiece_edit);
        qtyPiece = (EditText) findViewById(R.id.qtyPiece_edit);

        addItemsToTypeSpinner();
        addListenerToTypeSpinner();
        addItemsToCategorieSpinner();
        addListenerToCategorieSpinner();
    }

    /**
     * Adds items to the Type spinner
     */
    public void addItemsToTypeSpinner() {
        typePiece = (Spinner) findViewById(R.id.typePiece_edit);
        ArrayAdapter<CharSequence> typeSpinnerAdapter = ArrayAdapter
                .createFromResource(this, R.array.type_piece, android.R.layout.simple_spinner_item);
        typeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typePiece.setAdapter(typeSpinnerAdapter);
    }

    /**
     * Adds a listener to the Type spinner
     */
    public void addListenerToTypeSpinner() {
        typePiece = (Spinner) findViewById(R.id.typePiece_edit);

        //Sets the type on item selection
        typePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(pos).toString();
                type = itemSelectedInSpinner;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });
    }

    /**
     * Adds items to the Categorie spinner
     */
    public void addItemsToCategorieSpinner() {
        categoriePiece = (Spinner) findViewById(R.id.categoriePiece_edit);
        ArrayAdapter<CharSequence> categorieSpinnerAdapter = ArrayAdapter
                .createFromResource(this, R.array.categorie_piece, android.R.layout.simple_spinner_item);
        categorieSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriePiece.setAdapter(categorieSpinnerAdapter);
    }

    /**
     * Adds a listener to the Categorie spinner
     */
    public void addListenerToCategorieSpinner() {
        categoriePiece = (Spinner) findViewById(R.id.categoriePiece_edit);

        //Sets the categorie on item selection
        categoriePiece.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String itemSelectedInSpinner = adapterView.getItemAtPosition(pos).toString();
                categorie = itemSelectedInSpinner;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //TODO
            }
        });
    }

    private void takePictureIntent(){
        //Creates the intent of taking a picture
        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (photoIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoPiece = createImageFile();
            } catch (IOException ex) {
                Log.v("Error taking picture", String.valueOf(ex));
            }
            // Continue only if the File was successfully created
            if (photoPiece != null) {
                Uri photoUri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoPiece);
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
        //The path to store pictures taken from the camera
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

        // Save a file: path for use with ACTION_VIEW intents
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
     * Sets a thumbnail of the picture taken by the camera from an intent
     * @param requestCode the requested code
     * @param resultCode the result code
     * @param data the intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView photoPieceThumb = (ImageView) this.findViewById(R.id.cameraIcon);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bitmap thumbNail = BitmapFactory.decodeFile(photoPiece.getAbsolutePath());
            photoPieceThumb.setImageBitmap(thumbNail);
        }
    }

    /**
     * Button to add the new object to the inventory
     * Returns an object "piece" to InventairePieceActivity from an intent if all fields are valid
     * @param view the view
     */
    public void ajouterPieceListener(View view) {
        boolean pass = true;
        PieceModel piece = new PieceModel();

        //Field validation
        try{
            for(int i = 0; i < inventairePieces.getInventairePieces().size(); i++) {
                if (inventairePieces.getInventairePieces().get(i)
                        .getCodePiece() == Integer.parseInt(codePiece.getText().toString())) {
                    showError("Ce code existe déja", codePiece);
                    pass = false;
                } else if(pass){
                    if (!piece.setCodePiece(Integer.parseInt(codePiece.getText().toString()))) {
                        showError("Le code doit avoir entre 1 et 4 chiffres", codePiece);
                    } else if (!piece.setNomPiece(nomPiece.getText().toString())) {
                        showError("Le nom de la pièce ne peut être vide", nomPiece);
                    } else if (!piece.setDescriptionPiece(descriptionPiece.getText().toString())) {
                        showError("La description de la pièce ne peut être vide", descriptionPiece);
                    } else if (!piece.setDimensionPiece(Integer.parseInt(dimensionPiece.getText().toString()))) {
                        showError("La dimension doit être entre 4 et 15 mm", dimensionPiece);
                    } else if (!piece.setPrixCoutantPiece(Double.parseDouble(prixCoutantPiece.getText().toString()))) {
                        showError("Le prix ne peut être 0 et doit être au format 0.00", prixCoutantPiece);
                    } else if (piece.getCodePiece() > 0) {
                        piece.setQtyPiece(Integer.parseInt(qtyPiece.getText().toString()));
                        piece.setTypePiece(type);
                        piece.setCategoriePiece(categorie);
                        if(photoPiece != null)
                            piece.setPhotoPiece(photoPiece);

                        Intent intent = new Intent(this, InventairePiecesActivity.class)
                                .putExtra("piece", piece);
                        setResult(1, intent);

                        finish();
                    }
                }
            }
        }catch(NumberFormatException e){
            //Shows the error on screen with Snackbar
            Snackbar.make(view, "Vous devez remplir tous les champs", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }


    }


    /**
     * Selects the field where an error happened and makes the keyboard visible
     * @param error the error that was triggered
     * @param textField the field where the error happened
     */
    private void showError(String error, EditText textField){
        textField.requestFocus();
        textField.setError(error);
        InputMethodManager im = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            //Will only trigger if no physical keyboard is open
            im.showSoftInput(textField, 0);
        }
    }

    /**
     * Button to cancel
     * Returns to the main activity on cancel
     * @param view the view
     */
    public void annulerAjouterPieceListener(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

}
