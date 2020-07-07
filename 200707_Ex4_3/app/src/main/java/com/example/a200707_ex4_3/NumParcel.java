package com.example.a200707_ex4_3;

import android.os.Parcel;
import android.os.Parcelable;

public class NumParcel implements Parcelable {

    private int num1;
    private int num2;
    private String operator;

    private int result;

    public NumParcel(int num1, int num2, String operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
    }

    public NumParcel(int num1, int num2, String operator, int result) {
        this.num1 = num1;
        this.num2 = num2;
        this.operator = operator;
        this.result = result;
    }


    protected NumParcel(Parcel in) {
        num1 = in.readInt();
        num2 = in.readInt();
        operator = in.readString();
        result = in.readInt();
    }

    public static final Creator<NumParcel> CREATOR = new Creator<NumParcel>() {
        @Override
        public NumParcel createFromParcel(Parcel in) {
            return new NumParcel(in);
        }

        @Override
        public NumParcel[] newArray(int size) {
            return new NumParcel[size];
        }
    };

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public float getResult() {
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
        parcel.writeInt(num1);
        parcel.writeInt(num2);
        parcel.writeString(operator);
        parcel.writeInt(result);
    }
}
