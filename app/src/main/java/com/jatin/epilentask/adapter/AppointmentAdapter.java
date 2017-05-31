package com.jatin.epilentask.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jatin.epilentask.R;
import com.jatin.epilentask.model.Appointment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 31/5/17.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {

    List<Appointment> appointments = new ArrayList<>();

    public AppointmentAdapter(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Appointment appointment = appointments.get(position);
        holder.txtPatientName.setText("Patient Name :" + appointment.getPatientName());
        holder.txtDoctorName.setText("Doctor Name :" + appointment.getDoctorName());
        holder.txtApptime.setText("Appointment Date :" + appointment.getAppointmentDate());
        holder.txtDob.setText("Date of Birth :" + appointment.getDateOfBirth());
        if (hasData(appointment.getDescription())){
            holder.txtDescription.setText("Description :" + appointment.getDescription());
        }
        holder.txtSpeciality.setText("Speciality :" + appointment.getSpeciality());
        if (appointment.getGender() == 1){
            holder.txtGender.setText("Gender : Male");
        }else if (appointment.getGender() == 2){
            holder.txtGender.setText("Gender : Female");
        }
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_patient_name)
        AppCompatTextView txtPatientName;
        @BindView(R.id.txt_gender)
        AppCompatTextView txtGender;
        @BindView(R.id.txt_dob)
        AppCompatTextView txtDob;
        @BindView(R.id.txt_apptime)
        AppCompatTextView txtApptime;
        @BindView(R.id.txt_doctor_name)
        AppCompatTextView txtDoctorName;
        @BindView(R.id.txt_speciality)
        AppCompatTextView txtSpeciality;
        @BindView(R.id.txt_description)
        AppCompatTextView txtDescription;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public boolean hasData(String text) {
        return !(text == null || text.length() == 0);
    }

}
