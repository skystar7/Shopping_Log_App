package mobi.hoopoe.android.shoppinglog.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ShopActivity extends SingleFragmentActivity {

    private static String TAG = ShopActivity.class.getPackage().getName();
    private static final String SHOP_ID = ShopActivity.class.getPackage().getName() + "shop_id";

    private UUID mSelectedUUID;

    @Override
    protected Fragment createFragment() {
        /**
         * retrieve the intent's extra, and use it to stash it in Fragment bundle
         */
        return  ShopFragment.newInstance((UUID)getIntent().getSerializableExtra(SHOP_ID));
    }

    public static Intent createIntent(Context packageContext, UUID shopID) {
        Intent intent = new Intent(packageContext, ShopActivity.class);
        intent.putExtra(SHOP_ID, shopID);

        return intent;
    }



}
