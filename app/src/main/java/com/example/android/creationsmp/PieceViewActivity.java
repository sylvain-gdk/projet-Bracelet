package com.example.android.creationsmp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class PieceViewActivity extends AppCompatActivity {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mViewPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private InventairePiecesPagerAdapter inventairePiecesPagerAdapter;

    private Bundle args = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_view);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_piece_view_container, new PieceViewFragment())
                    .commit();
        }
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
        public Fragment getItem(int position) {
            Fragment frag = new PieceViewFragment();
            Bundle args = new Bundle();
            args.putSerializable("position", position + 1 );
            //args.putSerializable("piece", piece);
            //args.putSerializable("inventairePieces", inventairePieces);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public int getCount() {

            return 4;
        }

    }

}
