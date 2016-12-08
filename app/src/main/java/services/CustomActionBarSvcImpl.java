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
