package mobi.hoopoe.android.shoppinglog.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

import mobi.hoopoe.android.shoppinglog.R;
import mobi.hoopoe.android.shoppinglog.model.Shop;
import mobi.hoopoe.android.shoppinglog.model.ShopLab;

/**
 * Created by Ahmad on 3/13/2016.
 */
public class ShopFragment extends Fragment {

    private static String TAG = ShopFragment.class.getPackage().getName();
    /**
     * model
     */
    private Shop mShop;

    /**
     * Widgets
     */
    private EditText mTitleField;
    private Button mDateField;
    private CheckBox mCompletedCheckBox;

    private static final String SHOP_ID_ARG = ShopFragment.class.getPackage().getName() + "shop_id_arg";
    private ShopLab mShopLab;

    public static ShopFragment newInstance(UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(SHOP_ID_ARG, uuid);
        ShopFragment shopFragment = new ShopFragment();
        shopFragment.setArguments(args);
        return shopFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
         * update Selected Shop item
         */
        mShopLab = ShopLab.getInstance(getActivity());
        Bundle bundle = getArguments();
        mShop = mShopLab.getShop((UUID) bundle.getSerializable(SHOP_ID_ARG));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflate fragment view
        View view = inflater.inflate(R.layout.fragment_shop, container, false);






        // wiring up widgets
        mTitleField = (EditText) view.findViewById(R.id.shop_title);
        mTitleField.setText(mShop.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mShop.setTitle(s.toString());

                Log.d(TAG, mShop.getTitle());
                Log.d(TAG, mShop.getID().toString());
                Log.d(TAG, mShopLab.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mDateField = (Button) view.findViewById(R.id.shop_date_id);
        mDateField.setText(mShop.getFormattedDate());
        mDateField.setEnabled(false);


        mCompletedCheckBox = (CheckBox) view.findViewById(R.id.shop_completed);
        mCompletedCheckBox.setChecked(mShop.isCompleted());
        mCompletedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mShop.setCompleted(isChecked);
            }
        });

        return view;
    }

}
