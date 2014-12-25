package com.codeherenow.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnTextChanged;

/**
 * A demonstration of using listener injections that have multiple
 * callback methods. This example injects a {@link android.text.TextWatcher}
 * using the {@link butterknife.OnTextChanged} annotation. The callback methods
 * are identified using the {@link butterknife.OnTextChanged#callback()}
 * attribute.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class PaymentMethodActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        new CreditCardFormatter(findViewById(android.R.id.content));
    }

    static class CreditCardFormatter {
        List<Integer> SPACES = Collections.unmodifiableList(
            Arrays.asList(4, 9, 14)
        );
        int mLengthBeforeModification;

        public CreditCardFormatter(View contentView) {
            ButterKnife.inject(this, contentView);
        }

        @OnTextChanged(value = R.id.creditCardEditText,
                callback = OnTextChanged.Callback.BEFORE_TEXT_CHANGED)
        void beforeTextChanged(CharSequence oldText) {
            mLengthBeforeModification = oldText.length();
        }

        @OnTextChanged(value = R.id.creditCardEditText,
                callback = OnTextChanged.Callback.TEXT_CHANGED)
        void afterTextChanged(Editable editable) {
            int length = editable.length();
            boolean deleting = length < mLengthBeforeModification;

            if (SPACES.contains(length) && !deleting) {
                editable.append(" ");
            }
        }
    }
}
