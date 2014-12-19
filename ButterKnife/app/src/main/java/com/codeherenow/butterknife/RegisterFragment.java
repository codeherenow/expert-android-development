package com.codeherenow.butterknife;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Example for performing view injections on {@link android.support.v4.app.Fragment}s.
 * This works with both all Fragments, native ones and the one that is offered by the
 * compatibility package.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class RegisterFragment extends Fragment implements OnClickListener {

    @InjectView(R.id.nameEditText)
    EditText mNameEditText;

    @InjectView(R.id.genderRadioGroup)
    RadioGroup mGenderRadioGroup;

    @InjectView(R.id.emailEditText)
    EditText mEmailEditText;

    @InjectView(R.id.continentsSpinner)
    Spinner mContinentsSpinner;

    @InjectView(R.id.cityEditText)
    EditText mCityEditText;

    @InjectView(R.id.submitButton)
    Button mSubmitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_register,
                container, false);
        /*
         * There is no method to perform injections on Fragments as you do in
         * Activities. Therefore you should use the method that accepts
         * 2 arguments, the `target` and the root `view` to inject into
         * Fragments.
         */
        ButterKnife.inject(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Event listeners
        mSubmitButton.setOnClickListener(this);
    }

    /*
     * Nullify all references to your view instances inside the
     * `onDestroyView()` method. Thereby marking them for garbage collection.
     * ButterKnife offers you the 'reset()' method to do it in a single line
     * of code.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
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
        Builder builder = new Builder(getActivity());
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
