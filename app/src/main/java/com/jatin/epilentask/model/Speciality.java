package com.jatin.epilentask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uw on 31/5/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Speciality implements Parcelable {

    int id;
    String name;
    @JsonProperty("doctors")
    List<Doctors> doctorsList = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctors> getDoctorsList() {
        return doctorsList;
    }

    public void setDoctorsList(List<Doctors> doctorsList) {
        this.doctorsList = doctorsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeList(this.doctorsList);
    }

    public Speciality() {
    }

    protected Speciality(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.doctorsList = new ArrayList<Doctors>();
        in.readList(this.doctorsList, Doctors.class.getClassLoader());
    }

    public static final Parcelable.Creator<Speciality> CREATOR = new Parcelable.Creator<Speciality>() {
        @Override
        public Speciality createFromParcel(Parcel source) {
            return new Speciality(source);
        }

        @Override
        public Speciality[] newArray(int size) {
            return new Speciality[size];
        }
    };
}
