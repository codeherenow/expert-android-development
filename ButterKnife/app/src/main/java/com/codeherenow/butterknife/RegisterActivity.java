package com.codeherenow.butterknife;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * This is the Hello World style application for using Butter Knife's
 * {@link butterknife.InjectView} annotation to perform view injection.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class RegisterActivity extends Activity implements OnClickListener {

    /*
     * When you are using the @InjectView annotation, make sure that
     * your view field does not have a `private` or a `static` modifier.
     */
    @InjectView(R.id.nameEditText)
    EditText mNameEditText;

    @InjectView(R.id.genderRadioGroup)
    RadioGroup mGenderRadioGroup;

    @InjectView(R.id.emailEditText)
    EditText mEmailEditText;

    @InjectView(R.id.cityEditText)
    EditText mCityEditText;

    @InjectView(R.id.continentsSpinner)
    Spinner mContinentsSpinner;

    @InjectView(R.id.submitButton)
    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // UI References
        /*
        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mGenderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mCityEditText = (EditText) findViewById(R.id.cityEditText);
        mContinentsSpinner = (Spinner) findViewById(R.id.continentsSpinner);
        mSubmitButton = (Button) findViewById(R.id.submitButton);
        */

        /*
         * All the above `findViewById()` boilerplate code can be replaced
         * with a single `ButterKnife.inject()` call.
         */
        ButterKnife.inject(this);

        // Event Listener
        mSubmitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int checkedRadioButtonId = mGenderRadioGroup.getCheckedRadioButtonId();
        int genderRes = checkedRadioButtonId == R.id.maleRadioButton
            ? R.string.male : R.string.female;
        int pronounRes = checkedRadioButtonId == R.id.maleRadioButton
            ? R.string.him : R.string.her;

        // Get field values
        String name = mNameEditText.getText().toString();
        String gender = getString(genderRes);
        String email = mEmailEditText.getText().toString();
        String city = mCityEditText.getText().toString();
        String continent = (String) mContinentsSpinner.getSelectedItem();
        String pronoun = getString(pronounRes);

        // Show Dialog
        String message = getString(R.string.message,
            name, gender.toLowerCase(), city,
            continent, pronoun, email);

        showDialog(message);
    }

    private void showDialog(String message) {
        Builder builder = new Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton(getString(android.R.string.ok),
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        builder.create().show();
    }
}
