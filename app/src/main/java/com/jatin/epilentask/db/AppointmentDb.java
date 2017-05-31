package com.jatin.epilentask.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jatin.epilentask.model.Appointment;

/**
 * Created by uw on 31/5/17.
 */

public class AppointmentDb {

    private Context con;
    private final String db_name = "app.db";
    private final int db_version = 1;
    private DatabaseHelper dbhelp;
    private SQLiteDatabase db;

    public static final String TBL_APP_HISTORY = "app_history";

    String db_create = "create table "+TBL_APP_HISTORY+ " (patient_name TEXT, date_of_birth TEXT, gender INTEGER, appointment_date TEXT, doctor_name TEXT, description TEXT, speciality TEXT)";


    private AppointmentDb(Context con) {
        this.con = con;
        dbhelp = new AppointmentDb.DatabaseHelper(con,db_name,null,db_version);
        db = dbhelp.getWritableDatabase();
    }

    private static AppointmentDb appointmentDb;

    public static synchronized AppointmentDb getInstance(Context context){
        if (appointmentDb == null){
            appointmentDb = new AppointmentDb(context.getApplicationContext());
        }
        return appointmentDb;
    }

    public class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            createDb(sqLiteDatabase);
        }

        private void createDb(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(db_create);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            dropDb(sqLiteDatabase);
            createDb(sqLiteDatabase);
        }

        private void dropDb(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_APP_HISTORY);
        }
    }

    public void insertAppointmentDetail(Appointment appointment){
        ContentValues contentValues = new ContentValues();
        contentValues.put("patient_name",appointment.getPatientName());
        contentValues.put("date_of_birth",appointment.getDateOfBirth());
        contentValues.put("gender",appointment.getGender());
        contentValues.put("doctor_name",appointment.getDoctorName());
        contentValues.put("description",appointment.getDescription());
        contentValues.put("speciality",appointment.getSpeciality());
        contentValues.put("appointment_date",appointment.getAppointmentDate());
        db.insert(TBL_APP_HISTORY,null,contentValues);
    }

    public Cursor retrieveAppointmentHistory(){
        Cursor cursor = db.rawQuery("Select * from " + TBL_APP_HISTORY,null);
        return cursor;
    }

    public void deleteAppointmentHistory(){
        db.execSQL("Delete From " + TBL_APP_HISTORY);
    }

}
