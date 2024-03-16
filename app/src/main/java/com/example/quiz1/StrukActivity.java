package com.example.quiz1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class StrukActivity extends AppCompatActivity {

    TextView textViewNamaPembeli, textViewTipeMember, textViewKodeBarang, textViewNamaBarang,
            textViewJumlahBarang, textViewHarga, textViewTotalHarga, textViewDiscountHarga,
            textViewDiscountMember, textViewJumlahBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.struk);

        textViewNamaPembeli = findViewById(R.id.textViewNamaPembeli);
        textViewTipeMember = findViewById(R.id.textViewTipeMember);
        textViewKodeBarang = findViewById(R.id.textViewKodeBarang);
        textViewNamaBarang = findViewById(R.id.textViewNamaBarang);
        textViewJumlahBarang = findViewById(R.id.textViewJumlahBarang);
        textViewHarga = findViewById(R.id.textViewHarga);
        textViewTotalHarga = findViewById(R.id.textViewTotalHarga);
        textViewDiscountHarga = findViewById(R.id.textViewDiscountHarga);
        textViewDiscountMember = findViewById(R.id.textViewDiscountMember);
        textViewJumlahBayar = findViewById(R.id.textViewJumlahBayar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nama = extras.getString("Nama");
            String kodeBarang = extras.getString("KodeBarang");
            String namaBarang = extras.getString("NamaBarang");
            String tipePelanggan = extras.getString("TipePelanggan");
            int jumlahBarang = extras.getInt("JumlahBarang");
            double totalHarga = extras.getDouble("TotalHarga");
            double diskon = extras.getDouble("Diskon");
            double totalHargaSetelahDiskon = extras.getDouble("TotalHargaSetelahDiskon");
            double jumlahBayar = extras.getDouble("JumlahBayar");

            DecimalFormat formatter = new DecimalFormat("#,###");

            textViewNamaPembeli.setText("Nama Pembeli: " + nama);
            textViewTipeMember.setText("Tipe Pelanggan: " + tipePelanggan);
            textViewKodeBarang.setText("Kode Barang: " + kodeBarang);
            textViewNamaBarang.setText("Nama Barang: " + namaBarang);
            textViewJumlahBarang.setText("Jumlah Barang: " + jumlahBarang);
            textViewHarga.setText("Harga: Rp " + formatter.format(totalHarga));
            textViewTotalHarga.setText("Total Harga: Rp " + formatter.format(totalHargaSetelahDiskon));
            textViewDiscountHarga.setText("Diskon: " + (diskon * 100) + "%");
            textViewDiscountMember.setText("Diskon Member: Rp " + formatter.format(totalHarga - totalHargaSetelahDiskon));
            textViewJumlahBayar.setText("Jumlah Bayar: Rp " + formatter.format(jumlahBayar));
        }
    }
}
