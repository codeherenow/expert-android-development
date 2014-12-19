package com.codeherenow.butterknife;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * This is the {@link android.support.v4.app.FragmentActivity} version of
 * {@link com.codeherenow.butterknife.RegisterActivity}. Both examples
 * are functionally equivalent. However, the view injections are performed
 * inside the {@link com.codeherenow.butterknife.RegisterFragment} in this
 * example.
 *
 * @author Ragunath Jawahar <www.codeherenow.com>
 */
public class RegisterFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_register);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, new RegisterFragment())
                .commit();
        }
    }

}
