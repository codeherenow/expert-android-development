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
public class CreditCardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);

        new CreditCardFormatter(findViewById(android.R.id.content));
    }

    /**
     * This is by no means a complete solution for formatting Credit Card numbers.
     * Here are some of the caveats,
     *      1. The characters should be limited to 19 (16 for the CC and 3 for the
     *         spaces.)
     *      2. Input characters should be restricted to digits and spaces. This can be
     *         done inside the
     *         {@link CreditCardFormatter(android.view.View)#afterCreditCardModified(android.text.Editable)}
     *         method, or you can use an {@link android.text.InputFilter}.
     *      3. Spaces should be restricted to the specific indices, now you can enter a
     *         space character where ever you want it.
     *      4. Modifying the text by changing the cursor position will break the code.
     */
    static class CreditCardFormatter {
        static final List<Integer> SPACES = Collections.unmodifiableList(
            Arrays.asList(4, 9, 14)
        );
        int mLengthBeforeModification;

        CreditCardFormatter(View view) {
            ButterKnife.inject(this, view);
        }

        @OnTextChanged(value = R.id.creditCardEditText,
                callback = OnTextChanged.Callback.BEFORE_TEXT_CHANGED)
        void beforeCreditCardModified(CharSequence oldText) {
            mLengthBeforeModification = oldText.length();
        }

        @OnTextChanged(value = R.id.creditCardEditText,
                callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
        void afterCreditCardModified(Editable editable) {
            int length = editable.length();
            boolean deleting = mLengthBeforeModification > length;

            if (SPACES.contains(length) && !deleting) {
                editable.append(' ');
            }
        }
    }
}
