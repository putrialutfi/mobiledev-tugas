package com.putrialutfi.myuas;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.putrialutfi.myuas.API.ApiClient;
import com.putrialutfi.myuas.API.ApiInterface;
import com.putrialutfi.myuas.Adapter.PengaduanAdapter;
import com.putrialutfi.myuas.Model.Pengaduans;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PengaduanFragment extends Fragment {

    int id;

    private RecyclerView.LayoutManager mLayoutManager;
    private PengaduanAdapter mPengaduanAdapter;
    private ArrayList<Pengaduans> listHardware;

    ApiInterface mApiInterface;
    RecyclerView mRecyclerView;

    public PengaduanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_pengaduan, container, false);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Fragment fragment = new FormulirFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.layout_for_fragment, fragment).commit();
            }
        });

        mRecyclerView = view.findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
//        swipeRefreshLayout   = view.findViewById(R.id.swipe_refresh_layout);
//        swipeRefreshLayout.setOnRefreshListener(this);

        mApiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPengaduan();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPengaduan();
    }

    @Override
    public void onStart() {
        super.onStart();
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            id = bundle.getInt("id");
//            gambar = bundle.getString("gambar");
//            String deleting = bundle.getString("deleting");
//            if (deleting.equals("yes")){
//                deleteHardware(id, gambar);
//            }
//        }
        getPengaduan();
    }

    private void getPengaduan() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Wait a second..");
        progressDialog.show();
        Log.v("Main Activity reports ", "getting hardwares");
        Call<ArrayList<Pengaduans>> call = mApiInterface.getHardwares();
        call.enqueue(new Callback<ArrayList<Pengaduans>>() {
            @Override
            public void onResponse(Call<ArrayList<Pengaduans>> call, Response<ArrayList<Pengaduans>> response) {
                try {
                    listHardware = response.body();
                    Log.v("reports : ", response.body().toString());
                    progressDialog.dismiss();
                }
                catch (Exception e) {
                    Log.v("Main Activity reports ", e.getMessage() +" : "+ e.getCause());
                    progressDialog.dismiss();
                }
                mPengaduanAdapter = new PengaduanAdapter(listHardware, getActivity());
                mRecyclerView.setAdapter(mPengaduanAdapter);
                mPengaduanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<Pengaduans>> call, Throwable t) {
                Log.v("reports : ", "ERROR! " +t.getCause() + " : " +t.getMessage().toString());
            }
        });
    }

}
