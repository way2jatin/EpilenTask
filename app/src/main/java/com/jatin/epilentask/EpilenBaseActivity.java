package com.jatin.epilentask;

import android.support.annotation.LayoutRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 31/5/17.
 */

public class EpilenBaseActivity extends AppCompatActivity {

    DrawerLayout androidDrawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        androidDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.epilen_base,null);

        FrameLayout activityContainer = (FrameLayout) androidDrawerLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);

        super.setContentView(androidDrawerLayout);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
    }
}
