package com.example.android.creationsmp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sylvain on 2017-05-16.
 * This class is the Activity for the details of each item
 */

public class PieceViewActivity extends AppCompatActivity {

    // Accède à la classe de liste de pièces
    private InventairePieces inventairePieces;
    // Accède à la classe de pièces
    private PieceModel piece;

    private int position;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mViewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private InventairePiecesPagerAdapter inventairePiecesPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);

        // receives the intent
        Intent intent = this.getIntent();
        inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");
        position = (int) intent.getSerializableExtra("posClicked");

        // sends a bundle to the fragment
        /*Fragment frag = new PieceViewFragment();
        Bundle args = new Bundle();
        args.putSerializable("piece", piece);
        args.putSerializable("inventairePieces", inventairePieces);
        args.putInt("position", position + 1 );
        frag.setArguments(args);*/

        // Instantiate a ViewPager and a PagerAdapter.
        inventairePiecesPagerAdapter = new InventairePiecesPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.activity_piece_view_container);
        mViewPager.setAdapter(inventairePiecesPagerAdapter);
    }

    public class InventairePiecesPagerAdapter extends FragmentStatePagerAdapter {

        public InventairePiecesPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int posView) {
            return PieceViewFragment.create(posView, position, inventairePieces);
        }

        @Override
        //the amount of items in the inventory
        public int getCount() {
            return inventairePieces.getInventairePieces().size();
        }

    }

}
