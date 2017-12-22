package com.example.android.creationsmp.pieces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.creationsmp.R;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by sylvain on 2017-05-16.
 * This class is the Activity for the details of each item
 */

public class PieceViewDetailActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments representing
     * each object in a collection. We use a {@link android.support.v4.app.FragmentStatePagerAdapter}
     * derivative, which will destroy and re-create fragments as needed, saving and restoring their
     * state in the process. This is important to conserve memory and is a best practice when
     * allowing navigation between objects in a potentially large collection.
     */
    PieceViewPagerAdapter pieceViewPagerAdapter;

    // Accesses the GestionPieces class
    private GestionPieces mGestionPieces;

    // Accesses the GestionTypePieces class
    private GestionTypePieces mGestionTypePieces;

    /**
     * The {@link android.support.v4.view.ViewPager} that will display the object collection.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);

        // Registers to the EventBus
        //EventBus.getDefault().register(this);

        // Receives the position of the object when peviously clicked in the collection's list PiecesInventaireFragment
        Intent intent = getIntent();
        mGestionPieces = (GestionPieces) intent.getSerializableExtra("mGestionPieces");
        mGestionTypePieces = (GestionTypePieces) intent.getSerializableExtra("mGestionTypePieces");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_piece_view_container, new PieceViewDetailFragment())
                    .commit();
            // Create an adapter that when requested, will return a fragment of an object PieceModel in the collection
            pieceViewPagerAdapter = new PieceViewPagerAdapter(getSupportFragmentManager(), mGestionPieces, mGestionTypePieces);

            // Setup the ViewPager, attaching the adapter.
            mViewPager = (ViewPager) findViewById(R.id.activity_piece_view_container);
            mViewPager.setAdapter(pieceViewPagerAdapter);
            mViewPager.setCurrentItem(intent.getIntExtra("position", -1));        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Opens PieceEditDetailActivity to modify the object
        if (id == R.id.action_piece) {
            Intent intent = new Intent(this, PieceEditDetailActivity.class);
            intent.putExtra("position", mViewPager.getCurrentItem());
            intent.putExtra("mGestionPieces", mGestionPieces);
            intent.putExtra("mGestionTypePieces", mGestionTypePieces);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Updates the information when something changes in the collection
     * @param eventIntentDetail the event intent passed back to the detail activity
     */
    @Subscribe
    public void onEvent(EventManager.EventIntentDetail eventIntentDetail) {
        if (eventIntentDetail.getEventIntentDetail() != null) {
            Intent intent = eventIntentDetail.getEventIntentDetail();
            mGestionPieces = (GestionPieces) intent.getSerializableExtra("mGestionPieces");
            mGestionTypePieces = (GestionTypePieces) intent.getSerializableExtra("mGestionTypePieces");
            Log.v("––> onEvent", "updating details activity from controller");
        }
    }

    /**
     * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a fragment
     * representing an object in the collection.
     */
    public class PieceViewPagerAdapter extends FragmentStatePagerAdapter {

        // Accesses the GestionPieces class
        private GestionPieces mGestionPieces;

        // Accesses the GestionTypePieces class
        private GestionTypePieces mGestionTypePieces;

        public PieceViewPagerAdapter(FragmentManager fm, GestionPieces mGestionPieces, GestionTypePieces mGestionTypePieces) {
            super(fm);
            this.mGestionPieces = mGestionPieces;
            this.mGestionTypePieces = mGestionTypePieces;
        }

        /**
         * Gets the item position on swipe gestures
         * sends the detail fragment in a bundle
         *
         * @param position the item position from swipe gesture
         * @return the new fragment details
         */
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new PieceViewDetailFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putSerializable("mGestionPieces", mGestionPieces);
            args.putSerializable("mGestionTypePieces", mGestionTypePieces);
            fragment.setArguments(args);
            return fragment;
        }

        /**
         * Gets the size of the collection
         *
         * @return the size of the collection
         */
        @Override
        public int getCount() {
            return mGestionPieces.getInventairePieces().size();
        }
    }
}