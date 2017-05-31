package com.jatin.epilentask.model;

import android.database.Cursor;

/**
 * Created by uw on 31/5/17.
 */

public class Appointment {

    String patientName;
    String dateOfBirth;
    int gender;
    String appointmentDate;
    String description;
    String doctorName;
    String speciality;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public static Appointment appointment(Cursor cursor){
        Appointment appointment = new Appointment();
        appointment.setPatientName(cursor.getString(cursor.getColumnIndex("patient_name")));
        appointment.setGender(cursor.getInt(cursor.getColumnIndex("gender")));
        appointment.setDoctorName(cursor.getString(cursor.getColumnIndex("doctor_name")));
        appointment.setDateOfBirth(cursor.getString(cursor.getColumnIndex("date_of_birth")));
        appointment.setAppointmentDate(cursor.getString(cursor.getColumnIndex("appointment_date")));
        appointment.setSpeciality(cursor.getString(cursor.getColumnIndex("speciality")));
        appointment.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        return appointment;
    }
}
