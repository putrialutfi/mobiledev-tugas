package com.putrialutfi.myuas.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.putrialutfi.myuas.API.ApiInterface;
import com.putrialutfi.myuas.Model.Pengaduans;
import com.putrialutfi.myuas.R;

import java.util.List;

public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.ViewHolder> {
    List<Pengaduans> listPengaduan;
    ApiInterface mApiInterface;
    private Context context;

    public PengaduanAdapter(List<Pengaduans> listPengaduan, Context context) {
        this.listPengaduan = listPengaduan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tvNik.setText(listPengaduan.get(i).getNik());
        viewHolder.tvNama.setText(listPengaduan.get(i).getNama());
        viewHolder.tvAduan.setText(listPengaduan.get(i).getAduan());

//        viewHolder.listItem.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                final String actions[] = {"Update", "Delete"};
//                FragmentManager fragmentManager = ((FragmentActivity)context).getSupportFragmentManager();
//                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
//                alertDialog.setItems(actions, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                EntryFragment entryFragment = new EntryFragment();
//                                Bundle bundleEntry = new Bundle();
//                                bundleEntry.putInt("id", listHardware.get(i).getId());
//                                bundleEntry.putString("nama", listHardware.get(i).getNama());
//                                bundleEntry.putString("harga", listHardware.get(i).getHarga());
//                                bundleEntry.putString("gambar", listHardware.get(i).getGambar());
//                                entryFragment.setArguments(bundleEntry);
//
//                                fragmentTransaction.replace(R.id.fragment_content, entryFragment);
//                                fragmentTransaction.isAddToBackStackAllowed();
//                                fragmentTransaction.addToBackStack(null);
//                                fragmentTransaction.commit();
//                                break;
//                            case 1:
//                                new android.support.v7.app.AlertDialog.Builder(context)
//                                        .setMessage("Hapus Data?")
//                                        .setCancelable(false)
//                                        .setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                HomeFragment homeFragment = new HomeFragment();
//                                                Bundle bundleHome = new Bundle();
//                                                bundleHome.putString("deleting", "yes");
//                                                bundleHome.putInt("id", listHardware.get(i).getId());
//                                                bundleHome.putString("gambar", listHardware.get(i).getGambar());
//                                                homeFragment.setArguments(bundleHome);
//
//                                                fragmentTransaction.replace(R.id.fragment_content, homeFragment);
//                                                fragmentTransaction.isAddToBackStackAllowed();
//                                                fragmentTransaction.addToBackStack(null);
//                                                fragmentTransaction.commit();
//                                            }
//                                        })
//                                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.cancel();
//                                            }
//                                        }).create().show();
////                                deleteHardware(listHardware.get(i).getId(), listHardware.get(i).getGambar());
//                                break;
//                        }
//                    }
//                });
//                alertDialog.create();
//                alertDialog.show();
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listPengaduan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNama, tvNik, tvAduan;
        LinearLayout listItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNik = itemView.findViewById(R.id.tv_nik);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvAduan = itemView.findViewById(R.id.tv_aduan);
            listItem = itemView.findViewById(R.id.list_item);
        }
    }
}
