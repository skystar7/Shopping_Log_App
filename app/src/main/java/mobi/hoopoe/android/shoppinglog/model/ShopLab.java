package mobi.hoopoe.android.shoppinglog.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ahmad on 3/16/2016.
 */

/**
 * Singleton
 */
public class ShopLab {

    private static ShopLab sShopLab;
    private List<Shop> mMyShoppingList;

    /**
     * Context will be used with databases
     */
    private ShopLab(Context context) {
        mMyShoppingList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Shop shopObject = new Shop();
            shopObject.setTitle("Shopping Item#: " + i);
            shopObject.setCompleted(i % 2 == 0);
            mMyShoppingList.add(i, shopObject);
        }
    }

    public List<Shop> getMyShoppingList() {
        return mMyShoppingList;
    }

    public static ShopLab getInstance(Context context) {
        if (sShopLab == null) {
            sShopLab = new ShopLab(context);
        }
        return sShopLab;
    }

    public Shop getShop(UUID uuid) {

        for (Shop shop : mMyShoppingList) {

            if (shop.getID().equals(uuid)) {
                return shop;
            }

        }
        return null;
    }


}
