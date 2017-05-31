package com.jatin.epilentask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by uw on 31/5/17.
 */

public class SplashActivity extends EpilenBaseActivity {


    @BindView(R.id.btn_create)
    AppCompatButton btnCreate;
    @BindView(R.id.btn_view)
    AppCompatButton btnView;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_create, R.id.btn_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_create:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.btn_view:
                startActivity(new Intent(getApplicationContext(),AppointmentListActivity.class));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
