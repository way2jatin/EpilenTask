package com.jatin.epilentask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jatin.epilentask.adapter.DoctorsAdapter;
import com.jatin.epilentask.model.Doctors;
import com.jatin.epilentask.model.Speciality;
import com.jatin.epilentask.util.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 31/5/17.
 */

public class DoctorsListActivity extends EpilenBaseActivity {

    List<Doctors> doctorsList = new ArrayList<>();
    Speciality speciality;

    @BindView(R.id.doctors_view)
    RecyclerView doctorsView;

    DoctorsAdapter adapter;
    String specialityName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctors_view);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("speciality")){
            speciality = getIntent().getParcelableExtra("speciality");
        }

        doctorsList = speciality.getDoctorsList();
        specialityName = speciality.getName();

        adapter = new DoctorsAdapter(doctorsList);

        doctorsView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        doctorsView.setLayoutManager(mLayoutManager);
        doctorsView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        doctorsView.setItemAnimator(new DefaultItemAnimator());
        doctorsView.setAdapter(adapter);

        doctorsView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), doctorsView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //TODO: Appointment Activity to be started.
                final Doctors doctors = doctorsList.get(position);
                startActivity(new Intent(getApplicationContext(), AppointmentCreateActivity.class).putExtra("doctor",doctors).putExtra("specialityName",specialityName));
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }
}
