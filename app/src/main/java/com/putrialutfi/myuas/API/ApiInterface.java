package com.putrialutfi.myuas.API;

import com.putrialutfi.myuas.Model.Pengaduans;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("select_pengaduan.php")
    Call<ArrayList<Pengaduans>> getHardwares();

    @FormUrlEncoded
    @POST("add_pengaduan.php")
    Call<Pengaduans> addPengaduan(
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("aduan") String aduan
    );

    @FormUrlEncoded
    @POST("update_pengaduan.php")
    Call<Pengaduans> updatePengaduan(
            @Field("id") int id,
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("aduan") String aduan
    );

    @FormUrlEncoded
    @POST("delete_pengaduan.php")
    Call<Pengaduans> deletePengaduan(
            @Field("id") int id
    );
}
