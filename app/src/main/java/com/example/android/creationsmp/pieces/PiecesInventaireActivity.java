package com.example.android.creationsmp.pieces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.creationsmp.R;

/**
 * Created by sylvain on 2017-05-16.
 * This class is the activity for the inventory of items PieceModel
 */

public class PiecesInventaireActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pieces_inventaire);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_pieces_inventaire_container, new PiecesInventaireFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    /**
     * Returns to parent activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_inventaire) {
            startActivity(new Intent(this, SettingsPiecesInventaireActivity.class));
            return true;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }
}
