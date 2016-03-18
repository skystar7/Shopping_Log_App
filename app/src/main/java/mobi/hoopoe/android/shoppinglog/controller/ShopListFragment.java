package mobi.hoopoe.android.shoppinglog.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import mobi.hoopoe.android.shoppinglog.R;
import mobi.hoopoe.android.shoppinglog.model.MySingleton;
import mobi.hoopoe.android.shoppinglog.model.Shop;
import mobi.hoopoe.android.shoppinglog.model.ShopLab;

/**
 * Created by Ahmad on 3/16/2016.
 */
public class ShopListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<ShopHolder> myAdapter;
    private Button switchPosition;
    private MySingleton mMySingleton;

    // my model list
    private List<Shop> myShoppingList;
    private int currentPosition;

    private static String TAG = ShopFragment.class.getPackage().getName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myShoppingList = ShopLab.getInstance(getActivity()).getMyShoppingList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.shop_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        /**
         * for animation testing only
         */
        switchPosition = (Button) view.findViewById(R.id.delete_first_position);
        switchPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myShoppingList.remove(0);
                mRecyclerView.getAdapter().notifyItemRemoved(0);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        /**
         * testing Singleton
         */
        mMySingleton = MySingleton.getInstance();
        Log.d(TAG, "before: " + mMySingleton.getTestData());
        mMySingleton.setTestData("string changed");
        Log.d(TAG, "after: " + mMySingleton.getTestData());

        /**
         * update List for any change
         */
        updateUI();
        Log.d(TAG, "updateUI() from onResume()");
        Log.d(TAG, "currentPosition: " + currentPosition);
    }

    private void updateUI() {
        if (myAdapter == null) {
            myAdapter = new ShopAdapter(myShoppingList);
            mRecyclerView.setAdapter(myAdapter);
        } else {
            myAdapter.notifyItemChanged(currentPosition);
        }
    }

    private class ShopHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView mTitleView;
        private TextView mDateView;
        private CheckBox mCompletedView;
        private Shop mShop;


        // My View in the list
        public ShopHolder(View itemView) {
            super(itemView);

            /**
             * setting onClickListener on the View
             */
            itemView.setOnClickListener(this);

            /**
             * wire the widgets
             */

            mTitleView = (TextView) itemView.findViewById(R.id.list_item_title);
            mDateView = (TextView) itemView.findViewById(R.id.list_item_date);
            mCompletedView = (CheckBox) itemView.findViewById(R.id.list_item_completed);
        }

        public void bindShop(Shop shop) {
            /**
             * when you bind save data model instance with ViewHolder instance
             */
            mShop = shop;
            mTitleView.setText(shop.getTitle());
            mDateView.setText(shop.getTitle());
            mCompletedView.setChecked(shop.isCompleted());
        }

        @Override
        public void onClick(View v) {
            currentPosition = mRecyclerView.getChildAdapterPosition(v);
            Intent intent = ShopActivity.createIntent(getActivity(), mShop.getID());
            startActivity(intent);
        }
    }

    private class ShopAdapter extends RecyclerView.Adapter<ShopHolder> {

        private List<Shop> mMyShoppingList;

        public ShopAdapter(List<Shop> myShoppingList) {
            mMyShoppingList = myShoppingList;
        }

        @Override

        public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_shop, parent, false);

            return new ShopHolder(view);
        }

        @Override
        public void onBindViewHolder(ShopHolder holder, int position) {
            Shop shop = mMyShoppingList.get(position);
            holder.bindShop(shop);
        }

        @Override
        public int getItemCount() {
            return mMyShoppingList.size();
        }
    }


}