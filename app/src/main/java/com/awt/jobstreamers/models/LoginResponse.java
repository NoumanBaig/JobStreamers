package com.awt.jobstreamers.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("UID")
    @Expose
    private Integer uID;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status_login")
    @Expose
    private String statusLogin;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getUID() {
        return uID;
    }

    public void setUID(Integer uID) {
        this.uID = uID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(String statusLogin) {
        this.statusLogin = statusLogin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "uID=" + uID +
                ", message='" + message + '\'' +
                ", statusLogin='" + statusLogin + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
