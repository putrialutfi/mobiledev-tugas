package com.putrialutfi.myuas;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.putrialutfi.myuas.API.ApiClient;
import com.putrialutfi.myuas.API.ApiInterface;
import com.putrialutfi.myuas.Model.Pengaduans;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FormulirFragment extends Fragment {

    EditText edNik, edNama, edAduan;
    Button btnSubmit;
    ProgressDialog progressDialog;
    private int id;
    private ApiInterface mApiInterface;

    public FormulirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_formulir, container, false);
        edNik = view.findViewById(R.id.ed_nik);
        edNama = view.findViewById(R.id.ed_nama);
        edAduan = view.findViewById(R.id.ed_aduan);
        btnSubmit = view.findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == 0) {
                    if (edNik.getText().toString().isEmpty() || edNama.getText().toString().isEmpty() || edAduan.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Isian Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        addAduan();
                        goToPengaduanFragment();
                    }
                }
                else {
                    if (edNik.getText().toString().isEmpty() || edNama.getText().toString().isEmpty() || edAduan.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Isian Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
                    }
                    else {
//                        updateAduan(id);
                        goToPengaduanFragment();
                    }
                }
            }
        });
        return view;
    }

    private void addAduan() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait a second..");
        progressDialog.show();

        String nik = edNik.getText().toString();
        String nama = edNama.getText().toString();
        String aduan = edAduan.getText().toString();

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Pengaduans> call =  mApiInterface.addPengaduan(nik, nama, aduan);
        call.enqueue(new Callback<Pengaduans>() {
            @Override
            public void onResponse(Call<Pengaduans> call, Response<Pengaduans> response) {
                Log.v("reports : ", "adding data");

                String resp_code = response.body().getResp_code();
                String message = response.body().getMessage();

                if(resp_code.equals("1")) {
                    Toast.makeText(getActivity(), "Data is added succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Pengaduans> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                Log.v("reports :", t.getMessage() +"because"+ t.getCause());
                progressDialog.dismiss();
            }
        });
    }

    private void goToPengaduanFragment() {
        PengaduanFragment pengaduanFragment = new PengaduanFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_for_fragment, pengaduanFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
