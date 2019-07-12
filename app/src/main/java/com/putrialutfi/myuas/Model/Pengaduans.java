package com.putrialutfi.myuas.Model;

import com.google.gson.annotations.SerializedName;

public class Pengaduans {
    @SerializedName("id")
    private int id;

    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("aduan")
    private String aduan;

    @SerializedName("resp_code")
    private String resp_code;

    @SerializedName("message")
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAduan() {
        return aduan;
    }

    public void setAduan(String aduan) {
        this.aduan = aduan;
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
