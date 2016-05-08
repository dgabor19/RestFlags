package com.rebtel.restflags.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gabordudas on 08/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */
public class ErrorResponse implements Serializable, Parcelable {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeString(this.message);
    }

    protected ErrorResponse(Parcel in) {
        this.status = in.readInt();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<ErrorResponse> CREATOR = new Parcelable.Creator<ErrorResponse>() {
        @Override
        public ErrorResponse createFromParcel(Parcel source) {
            return new ErrorResponse(source);
        }

        @Override
        public ErrorResponse[] newArray(int size) {
            return new ErrorResponse[size];
        }
    };
}
