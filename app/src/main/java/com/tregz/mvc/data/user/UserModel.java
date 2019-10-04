package com.tregz.mvc.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class UserModel implements Parcelable {
    public static final String TAG = UserModel.class.getSimpleName();

    public UserModel() {}

    private Date birthDate;
    private String email;
    private String firstName;
    private String lastName;
    private Long phoneNumber;


    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(birthDate);
        parcel.writeString(email);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeLong(phoneNumber);
    }

    private UserModel(Parcel parcel) {
        birthDate = (Date) parcel.readSerializable();
        email = parcel.readString();
        firstName = parcel.readString();
        lastName = parcel.readString();
        phoneNumber = parcel.readLong();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel parcel) {
            return new UserModel(parcel);
        }

        @Override
        public UserModel[] newArray(int i) {
            return new UserModel[i];
        }
    };
}
