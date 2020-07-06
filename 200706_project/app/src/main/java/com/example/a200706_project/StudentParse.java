package com.example.a200706_project;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentParse implements Parcelable {

    private int number1;
    private int number2;
    private String calculate;
    private int result;

    public StudentParse(int number1, int number2, String calculate) {
        this.number1 = number1;
        this.number2 = number2;
        this.calculate = calculate;
    }

    public StudentParse(int number1, int number2, String calculate, int result) {
        this.number1 = number1;
        this.number2 = number2;
        this.calculate = calculate;
        this.result = result;
    }

    protected StudentParse(Parcel in) {
        number1 = in.readInt();
        number2 = in.readInt();
        calculate = in.readString();
        result = in.readInt();
    }

    public static final Creator<StudentParse> CREATOR = new Creator<StudentParse>() {
        @Override
        public StudentParse createFromParcel(Parcel in) {
            return new StudentParse(in);
        }

        @Override
        public StudentParse[] newArray(int size) {
            return new StudentParse[size];
        }
    };

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public String getCalculate() {
        return calculate;
    }

    public void setCalculate(String calculate) {
        this.calculate = calculate;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number1);
        parcel.writeInt(number2);
        parcel.writeString(calculate);
        parcel.writeInt(result);
    }
}
