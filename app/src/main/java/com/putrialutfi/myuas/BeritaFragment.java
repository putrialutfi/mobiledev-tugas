package com.putrialutfi.myuas;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.putrialutfi.myuas.Adapter.AdapterBerita;
import com.putrialutfi.myuas.network.ApiServices;
import com.putrialutfi.myuas.network.InitRetrofit;
import com.putrialutfi.myuas.response.BeritaItem;
import com.putrialutfi.myuas.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment {

    private RecyclerView recyclerView;

    public BeritaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_berita, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvListBerita);
        // RecyclerView harus pakai Layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Eksekusi method
        tampilBerita();
        return view;
    }

    private void tampilBerita() {
            ApiServices api = InitRetrofit.getInstance();

             // Siapkan request
            Call<ResponseBerita> beritaCall = api.request_show_all_berita();
            // Kirim request
            beritaCall.enqueue(new Callback<ResponseBerita>() {
                @Override
                public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                // Pasikan response Sukses
                    if (response.isSuccessful()){
                        Log.d("response api", response.body().toString());

                        // tampung data response body ke variable
                        List<BeritaItem> data_berita = response.body().getBerita();
                        boolean status = response.body().isStatus();

                        // Kalau response status nya = true
                        if (status){
                            // Buat Adapter untuk recycler view
                            AdapterBerita adapter = new AdapterBerita(getActivity(), data_berita);
                            recyclerView.setAdapter(adapter);
                        }
                        else {
                            // kalau tidak true
                            Toast.makeText(getContext(), "Tidak ada berita untuk saat ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override

                public void onFailure(Call<ResponseBerita> call, Throwable t) {
                    // print ke log jika Error
                    t.printStackTrace();
                }
            });
        }

}
