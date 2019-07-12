package com.putrialutfi.myuas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView ivGambarBerita;
    TextView tvTglTerbit, tvPenulis;
    WebView wvKontenBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi

        ivGambarBerita = (ImageView) findViewById(R.id.ivGambarBerita);
        tvTglTerbit = (TextView) findViewById(R.id.tvTglTerbit);
        tvPenulis = (TextView) findViewById(R.id.tvPenulis);

        wvKontenBerita = (WebView) findViewById(R.id.wvKontenBerita);

        // Jalankan method tampil detail berita
        showDetailBerita();
    }

    private void showDetailBerita() {
        // Tangkap data dari intent

        String judul_berita = getIntent().getStringExtra("JDL_BERITA");
        String tanggal_berita = getIntent().getStringExtra(
                "TGL_BERITA");
        String penulis_berita = getIntent().getStringExtra(
                "PNS_BERITA");
        String isi_berita = getIntent().getStringExtra(
                "ISI_BERITA");
        String foto_berita = getIntent().getStringExtra(
                "FTO_BERITA");

        // Set judul actionbar / toolbar
        getSupportActionBar().setTitle(judul_berita);

        // Set ke widget
        tvPenulis.setText("Oleh : " + penulis_berita);
        tvTglTerbit.setText(tanggal_berita);
        // Untuk gambar berita
        Picasso.with(this).load(foto_berita).into(ivGambarBerita);
        // Set isi berita sebagai html ke WebView

        wvKontenBerita.getSettings().setJavaScriptEnabled(true);
        wvKontenBerita.loadData(isi_berita,
                "text/html; charset=utf-8", "UTF-8");
    }
}
