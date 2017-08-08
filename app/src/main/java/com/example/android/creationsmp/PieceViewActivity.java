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
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    PieceViewPagerAdapter pieceViewPagerAdapter;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_piece_view_container, new PieceViewFragment())
                    .commit();

            // Create an adapter that when requested, will return a fragment representing an object in
            // the collection.
            pieceViewPagerAdapter = new PieceViewPagerAdapter(getSupportFragmentManager());
            // Set up the ViewPager, attaching the adapter.
            mViewPager = (ViewPager) findViewById(R.id.activity_piece_view_container);
            mViewPager.setAdapter(pieceViewPagerAdapter);

            //Receives the position of the item peviously clicked in the inventory list
            Intent intent = getIntent();
            mViewPager.setCurrentItem(intent.getIntExtra("posClicked", -1));
        }
    }

    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
    public class PieceViewPagerAdapter extends FragmentStatePagerAdapter {

        //Accesses the InventairePieces class
        private InventairePieces inventairePieces;

        public PieceViewPagerAdapter(FragmentManager fm) {
            super(fm);

            Intent intent = getIntent();
            inventairePieces = (InventairePieces) intent.getSerializableExtra("inventairePieces");
        }

        /**
         * Gets the item position on swipe gestures
         * sends the detail fragment in a bundle
         * @param position the item position from swipe gesture
         * @return the new fragment details
         */
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new PieceViewFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            fragment.setArguments(args);
            return fragment;
        }

        /**
         * Gets the size of the inventory from an intent
         * @return the size of the inventory
         */
        @Override
        public int getCount() {
            return inventairePieces.getInventairePieces().size();
        }
    }
}
