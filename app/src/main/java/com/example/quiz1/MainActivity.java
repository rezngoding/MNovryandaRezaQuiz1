package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etNama, etKodeBarang, etJumlahBarang;
    RadioGroup radioGroup;
    Button btnProses;

    private final Map<String, String> daftarBarang = new HashMap<String, String>() {{
        put("SGS", "Samsung Galaxy S");
        put("IPX", "Iphone X");
        put("PCO", "POCO M3");
        put("O17", "Oppo A17");
        put("OAS", "Oppo a5s");
        put("AAE", "Acer Aspire E14");
        put("AV4", "Asus Vivobook 14");
        put("LV3", "Lenovo V14 Gen 3");
        put("AA5", "Acer Aspire 5");
        put("MP3", "Macbook Pro M3");
    }};

    private final Map<String, Double> hargaBarang = new HashMap<String, Double>() {{
        put("SGS", 12999999.0);
        put("IPX", 5725300.0);
        put("PCO", 2730551.0);
        put("O17", 2500999.0);
        put("OAS", 1989123.0);
        put("AAE", 8676981.0);
        put("AV4", 9150999.0);
        put("LV3", 6666666.0);
        put("AA5", 9999999.0);
        put("MP3", 28999999.0);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.Nama);
        etKodeBarang = findViewById(R.id.Kode_Barang);
        etJumlahBarang = findViewById(R.id.Jumlah_Barang);
        radioGroup = findViewById(R.id.radioGroup);
        btnProses = findViewById(R.id.button);

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = etNama.getText().toString();
                String kodeBarang = etKodeBarang.getText().toString();
                String jumlahBarangString = etJumlahBarang.getText().toString();

                if (TextUtils.isEmpty(jumlahBarangString)) {
                    Toast.makeText(MainActivity.this, "Masukkan jumlah barang", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.isDigitsOnly(jumlahBarangString)) {
                    Toast.makeText(MainActivity.this, "Jumlah barang harus berupa angka", Toast.LENGTH_SHORT).show();
                    return;
                }

                int jumlahBarang = Integer.parseInt(jumlahBarangString);

                if (!daftarBarang.containsKey(kodeBarang)) {
                    Toast.makeText(MainActivity.this, "Kode barang tidak valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                String namaBarang = daftarBarang.get(kodeBarang);
                double harga = hargaBarang.get(kodeBarang);

                double totalHarga = harga * jumlahBarang;

                // Ambil tipe pelanggan yang dipilih
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                String tipePelanggan = radioButton.getText().toString();

                // Hitung diskon berdasarkan tipe pelanggan
                double diskon = 0;
                switch (tipePelanggan) {
                    case "Gold":
                        diskon = 0.1;
                        break;
                    case "Silver":
                        diskon = 0.05;
                        break;
                    case "Biasa":
                        diskon = 0.02;
                        break;
                }

                // Hitung total harga setelah diskon
                double totalHargaSetelahDiskon = totalHarga * (1 - diskon);

                // Lakukan penanganan diskon khusus jika total harga lebih dari 10 juta
                if (totalHarga > 10000000) {
                    totalHargaSetelahDiskon -= 100000;
                }

                // Hitung jumlah bayar
                double jumlahBayar = totalHargaSetelahDiskon;

                // Lanjutkan ke halaman StrukActivity dengan mengirimkan data
                Intent intent = new Intent(MainActivity.this, StrukActivity.class);
                intent.putExtra("Nama", nama);
                intent.putExtra("KodeBarang", kodeBarang);
                intent.putExtra("NamaBarang", namaBarang);
                intent.putExtra("TipePelanggan", tipePelanggan);
                intent.putExtra("JumlahBarang", jumlahBarang);
                intent.putExtra("TotalHarga", totalHarga);
                intent.putExtra("Diskon", diskon);
                intent.putExtra("TotalHargaSetelahDiskon", totalHargaSetelahDiskon);
                intent.putExtra("JumlahBayar", jumlahBayar);
                startActivity(intent);
            }
        });
    }
}
