package com.purushotham.arvind.customactionbar;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import services.CustomActionBarSvcImpl;

public class CustomActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_action_bar);

        ActionBar actionBar = getSupportActionBar();
        CustomActionBarSvcImpl service = new CustomActionBarSvcImpl(this, actionBar, "Custom Action Bar");
        service.setting();

    }
}
