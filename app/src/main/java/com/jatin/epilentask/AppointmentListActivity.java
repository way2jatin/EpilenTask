package com.jatin.epilentask;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jatin.epilentask.adapter.AppointmentAdapter;
import com.jatin.epilentask.db.AppointmentDb;
import com.jatin.epilentask.model.Appointment;
import com.jatin.epilentask.util.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 31/5/17.
 */

public class AppointmentListActivity extends EpilenBaseActivity {

    @BindView(R.id.appointment_view)
    RecyclerView appointmentView;

    AppointmentAdapter adapter;

    List<Appointment> appointments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_list_view);
        ButterKnife.bind(this);

        retrieveAppointmentHistory();

        adapter = new AppointmentAdapter(appointments);
        appointmentView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        appointmentView.setLayoutManager(mLayoutManager);
        appointmentView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayoutManager.VERTICAL));
        appointmentView.setItemAnimator(new DefaultItemAnimator());
        appointmentView.setAdapter(adapter);

        appointmentView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), appointmentView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void retrieveAppointmentHistory() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = AppointmentDb.getInstance(getApplicationContext()).retrieveAppointmentHistory();
                if (cursor.moveToFirst()){
                    do{
                        Appointment detail = Appointment.appointment(cursor);
                        appointments.add(detail);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        }).start();
    }
}
