package com.jatin.epilentask.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by uw on 31/5/17.
 */

public class Doctors implements Parcelable {

    int id;
    String name;

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    public Doctors() {
    }

    protected Doctors(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Doctors> CREATOR = new Parcelable.Creator<Doctors>() {
        @Override
        public Doctors createFromParcel(Parcel source) {
            return new Doctors(source);
        }

        @Override
        public Doctors[] newArray(int size) {
            return new Doctors[size];
        }
    };
}
