package com.codeherenow.butterknife;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

/**
 * Example to demonstrate the use of {@link butterknife.InjectViews},
 * {@link butterknife.ButterKnife.Setter} and {@link butterknife.ButterKnife.Action}
 * features.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class BillingShippingActivity extends Activity {
    // Typefaces
    private Typeface mAlexBrushTypeface;
    private Typeface mQuicksandTypeface;

    /*
     * {@link butterknife.InjectViews} annotation accepts an array of view IDs.
     * @InjectViews should be used only on {@link java.util.List} type or on
     * an array of view objects. Other implementations are restricted.
     */
    @InjectViews({
        R.id.billingAddressTextView,
        R.id.shippingAddressTextView
    })
    List<TextView> mHeaderTextViews;

    @InjectViews({
        R.id.billingFullNameEditText,
        R.id.billingAddressEditText,
        R.id.billingPhoneEditText
    })
    List<EditText> mBillingEditTexts;

    @InjectViews({
        R.id.shippingFullNameEditText,
        R.id.shippingAddressEditText,
        R.id.shippingPhoneEditText
    })
    List<EditText> mShippingEditTexts;

    @InjectView(R.id.sameAddressCheckBox)
    CheckBox mSameAddressCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_shipping);

        // Create typefaces
        createTypefaces();

        // View injection
        ButterKnife.inject(this);

        /*
         * {@link butterknife.ButterKnife.Setter}s allow you to pass values. Following
         * method calls illustrate the usage of {@link butterknife.ButterKnife.Setter}s
         * using the `ButterKnife.apply(List, Setter, Object)` method.
         */
        ButterKnife.apply(mHeaderTextViews, TYPEFACE, mAlexBrushTypeface);
        ButterKnife.apply(mBillingEditTexts, TYPEFACE, mQuicksandTypeface);
        ButterKnife.apply(mShippingEditTexts, TYPEFACE, mQuicksandTypeface);

        // Event Listener
        mSameAddressCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ButterKnife.apply(mShippingEditTexts, ENABLED, !isChecked);

                if (isChecked) {
                    /*
                     * {@link android.app.Notification.Action}s does not allow you
                     * to pass values to your views.
                     */
                    ButterKnife.apply(mShippingEditTexts, CLEAR);
                }
            }
        });
    }

    private void createTypefaces() {
        AssetManager assetManager = getAssets();

        // Create typefaces
        mAlexBrushTypeface = Typeface.createFromAsset(assetManager,
            getString(R.string.typeface_alex_brush));
        mQuicksandTypeface = Typeface.createFromAsset(assetManager,
            getString(R.string.typeface_quicksand));
    }

    /*
     * {@link butterknife.ButterKnife.Setter}s allow you to set a value
     * for multiple {@link android.view.View} instances.
     */
    static final ButterKnife.Setter<TextView, Typeface> TYPEFACE =
            new ButterKnife.Setter<TextView, Typeface>() {
                @Override
                public void set(TextView view, Typeface value, int index) {
                    view.setTypeface(value);
                }
            };

    /*
     * {@link butterknife.ButterKnife.Action}s allow you to perform a set of
     * actions on multiple {@link android.view.View} instances. The difference
     * between a {@link butterknife.ButterKnife.Setter} and an
     * {@link butterknife.ButterKnife.Action} is that, the
     * {@link butterknife.ButterKnife.Setter} allows values to be passed, however
     * an {@link butterknife.ButterKnife.Action} cannot allow values.
     */
    static final ButterKnife.Setter<EditText, Boolean> ENABLED =
            new ButterKnife.Setter<EditText, Boolean>() {
                @Override
                public void set(EditText view, Boolean value, int index) {
                    view.setEnabled(value);
                }
            };

    static final ButterKnife.Action<EditText> CLEAR =
            new ButterKnife.Action<EditText>() {
                @Override
                public void apply(EditText view, int index) {
                    view.setText("");
                }
            };
}
