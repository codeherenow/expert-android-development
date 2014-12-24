package com.codeherenow.butterknife;

import android.app.ListActivity;
import android.os.Bundle;

public class GuestListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);
    }

}
