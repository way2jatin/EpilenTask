package com.jatin.epilentask;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jatin.epilentask.db.AppointmentDb;
import com.jatin.epilentask.model.Appointment;
import com.jatin.epilentask.model.Doctors;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by uw on 31/5/17.
 */

public class AppointmentCreateActivity extends AppCompatActivity implements View.OnClickListener {

    String specialityName;
    Doctors doctors;
    @BindView(R.id.patient_name)
    EditText patientName;
    @BindView(R.id.date_of_birth)
    EditText dateOfBirth;
    @BindView(R.id.male_radio_btn)
    RadioButton maleRadioBtn;
    @BindView(R.id.female_radio_btn)
    RadioButton femaleRadioBtn;
    @BindView(R.id.description)
    EditText description;
    @BindView(R.id.app_date)
    EditText appDate;
    @BindView(R.id.txt_doctor_name)
    AppCompatTextView txtDoctorName;
    @BindView(R.id.rel_doc_info)
    RelativeLayout relDocInfo;
    @BindView(R.id.txt_spec_name)
    AppCompatTextView txtSpecName;
    @BindView(R.id.btn_cancel)
    AppCompatButton btnCancel;
    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;

    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_view);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("specialityName")) {
            specialityName = getIntent().getStringExtra("specialityName");
        }

        if (getIntent().hasExtra("doctor")) {
            doctors = getIntent().getParcelableExtra("doctor");
        }

        appDate.setText(getCurrentDate());
        dateOfBirth.setOnClickListener(this);
        appDate.setOnClickListener(this);

        txtSpecName.setText(specialityName);
        txtDoctorName.setText(doctors.getName());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAppointment();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(c.getTime());
    }

    @Override
    public void onClick(View view) {
        if (view == dateOfBirth){
            setDateinEditText(dateOfBirth);
        }
        else if (view == appDate){
            setDateinEditText(appDate);
        }
    }

    private void setDateinEditText(final EditText edt_txt) {
        hideSoftKeyboard();
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edt_txt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void submitAppointment(){

        if (validate()){
            Appointment appointment = new Appointment();
            appointment.setDoctorName(doctors.getName());
            appointment.setPatientName(patientName.getText().toString());
            appointment.setDateOfBirth(dateOfBirth.getText().toString());
            appointment.setAppointmentDate(appDate.getText().toString());
            appointment.setSpeciality(specialityName);
            appointment.setDescription(description.getText().toString());
            appointment.setGender(maleRadioBtn.isChecked() ? 1 : 2);
            AppointmentDb db = AppointmentDb.getInstance(getApplicationContext());
            db.insertAppointmentDetail(appointment);
            Toast.makeText(getApplicationContext(), "Your Appointment has been saved successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),SplashActivity.class));
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
        else {
            Toast.makeText(getApplicationContext(), "Please enter all the details", Toast.LENGTH_SHORT).show();
        }


    }


    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public boolean hasData(String text) {
        return !(text == null || text.length() == 0);
    }

    private boolean validate(){
        boolean valid;

        String pName = patientName.getText().toString();
        String dob = dateOfBirth.getText().toString();
        String appointmentDate = appDate.getText().toString();

        valid = hasData(pName) && hasData(dob) && hasData(appointmentDate);
        return valid;
    }

}
