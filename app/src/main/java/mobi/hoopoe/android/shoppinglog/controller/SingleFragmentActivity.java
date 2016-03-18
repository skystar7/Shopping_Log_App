package mobi.hoopoe.android.shoppinglog.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import mobi.hoopoe.android.shoppinglog.R;

/**
 * Created by Ahmad on 3/16/2016.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        /**
         * managing fragments
         */

        FragmentManager fragmentManager = getSupportFragmentManager();

        // try to find it
        Fragment shopFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        //else
        if (shopFragment == null) {
            shopFragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, shopFragment).commit();
        }


    }
}
