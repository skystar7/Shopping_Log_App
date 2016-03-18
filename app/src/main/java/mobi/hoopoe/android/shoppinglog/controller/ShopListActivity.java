package mobi.hoopoe.android.shoppinglog.controller;

import android.support.v4.app.Fragment;

/**
 * Created by Ahmad on 3/16/2016.
 */
public class ShopListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new ShopListFragment();
    }


}
