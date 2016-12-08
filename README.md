# Android - Custom Action Bar
This is an example on how to create a custom action bar with custom images, buttons and styling. Feel free to download/reuse/edit code uploaded here.

# Steps to create custom action bar
1) Obviously need an activity to which a custom action bar is to be added.
2) In AndroidManifest.xml, under the activity in focus, add theme - anroid:theme="@style/AppTheme" or any other theme that holds an action bar.
3) Custom Action Bar XML - add all the custom components of the action bar in this xml
4) A java class (take context of the activity, ActionBar and a string/title as parameters to its constructor.)

# Example XML code
Note - To use Constraint Layout if you do not find it in the design - create a relative layout and right click on it, click on convert relative layout to constraint layout option at the bottom. 

<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="56dp"
    android:id="@+id/actionbar_layout"
    android:shadowColor="@color/shadow"
    android:shadowRadius="5"
    android:shadowDx="5"
    android:shadowDy="5">

    <RelativeLayout
        android:layout_width="56dp"
        android:layout_height="0dp"
        android:id="@+id/custom_ab_rl_back"
        tools:layout_constraintTop_creator="1"
        tools:layout_constraintBottom_creator="1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent">

        <ImageView
            android:layout_width="56dp"
            android:layout_height="match_parent"
            android:id="@+id/custom_ab_b_back"
            android:background="@drawable/back_arrow_nine"
            android:layout_marginTop="18dp"
            android:layout_marginBottom="18dp"
            android:layout_marginRight="18dp"
            android:layout_marginLeft="18dp"
            android:contentDescription="@string/backbutton" />

    </RelativeLayout>

    <TextView
        android:textColor="@color/white"
        android:textSize="20sp"
        android:textStyle="bold"
        android:paddingLeft="8dp"
        android:paddingRight="8dp"
        android:layout_width="0dp"
        android:gravity="center_vertical"
        android:layout_height="0dp"
        android:id="@+id/custom_ab_title"
        tools:layout_constraintTop_creator="1"
        tools:layout_constraintBottom_creator="1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.0"
        app:layout_constraintLeft_toRightOf="@+id/custom_ab_rl_back"
        app:layout_constraintRight_toLeftOf="@+id/custom_ab_rl_menu" />

    <RelativeLayout
        android:layout_width="56dp"
        android:layout_height="0dp"
        android:id="@+id/custom_ab_rl_menu"
        tools:layout_constraintTop_creator="1"
        tools:layout_constraintBottom_creator="1"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintRight_toRightOf="parent">

        <ImageView
            android:layout_width="56dp"
            android:layout_height="match_parent"
            android:id="@+id/custom_ab_b_menu"
            android:layout_marginTop="18dp"
            android:layout_marginBottom="18dp"
            android:layout_marginRight="18dp"
            android:layout_marginLeft="18dp"
            android:background="@drawable/menu_logo_nine"
            android:contentDescription="@string/menubutton" />

    </RelativeLayout>

</android.support.constraint.ConstraintLayout>

# Custom Action Bar Activity code -

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

# Custom Action Bar Java Class to write customized action bar code.

package services;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.purushotham.arvind.customactionbar.R;

public class CustomActionBarSvcImpl {

    private Context context;
    private android.support.v7.app.ActionBar actionBar;
    private String title_text;

    public CustomActionBarSvcImpl(Context context, android.support.v7.app.ActionBar actionBar, String title) {
        this.context = context;
        this.actionBar = actionBar;
        this.title_text = title;
        Log.d("action bar", actionBar.toString());
    }

    public void setting ()
    {
        Log.d("Action Bar", "settings");
        LayoutInflater inflate = LayoutInflater.from(context);
        View view = inflate.inflate(R.layout.action_bar, null);

        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT);

        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setCustomView(view, layoutParams);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setElevation(5);
            Toolbar parent = (Toolbar) view.getParent();
            parent.setContentInsetsAbsolute(0, 0);
            TextView title = (TextView) ((Activity) context).findViewById(R.id.custom_ab_title);
            title.setText(title_text);
            ConstraintLayout action = (ConstraintLayout) ((Activity)context).findViewById(R.id.actionbar_layout);
            action.setBackgroundColor(ContextCompat.getColor(context, R.color.orange_dark));
            RelativeLayout custom_ab_rl_back = (RelativeLayout) ((Activity)context).findViewById(R.id.custom_ab_rl_back);
            custom_ab_rl_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((Activity)context).finish();
                }
            });

            final RelativeLayout custom_ab_rl_menu = (RelativeLayout) ((Activity)context).findViewById(R.id.custom_ab_rl_menu);
            custom_ab_rl_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Menu clicked but empty", Toast.LENGTH_LONG).show();
                }
            });
        }
        else
            Log.d("CustomActionBarSvcImpl", "Null pointer for actionBar");

    }

}

#Author - Arvind Purushotham
#Email ID - arvip.dev@gmail.com and arvind.purushotham@gmail.com
