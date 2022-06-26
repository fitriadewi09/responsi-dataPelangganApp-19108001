package com.example.datapelanggan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datapelanggan.helper.Helper;

public class EditorActivity extends AppCompatActivity {

    private EditText editNama, editEmail, editnoHp, editAlamat;
    private Button btnSave;
    private Helper db = new Helper(this);
    private String id, nama, email, noHp, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editNama = findViewById(R.id.edit_nama);
        editEmail = findViewById(R.id.edit_email);
        editnoHp = findViewById(R.id.edit_nohp);
        editAlamat = findViewById(R.id.edit_alamat);
        btnSave = findViewById(R.id.btn_save);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        email = getIntent().getStringExtra("email");
        noHp = getIntent().getStringExtra("noHp");
        alamat = getIntent().getStringExtra("alamat");

        if (id == null || id.equals("")) {
            setTitle("Tambah Data Pelanggan");
        }else {
            setTitle("Edit Data Pelanggan");
            editNama.setText(nama);
            editEmail.setText(email);
            editnoHp.setText(noHp);
            editAlamat.setText(alamat);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (id == null || id.equals("")){
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Saving", e.getMessage());
                }
            }
        });
    }

    private void save(){
        if (String.valueOf(editNama.getText()).equals("") || String.valueOf(editEmail.getText()).equals("") || String.valueOf(editnoHp.getText()).equals("") || String.valueOf(editAlamat.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Mohon Lengkapi Data!", Toast.LENGTH_SHORT).show();
        } else {
            db.insert(editNama.getText().toString(), editEmail.getText().toString(), editnoHp.getText().toString(), editAlamat.getText().toString());
        }
    }

    private void edit(){
        if (String.valueOf(editNama.getText()).equals("") || String.valueOf(editEmail.getText()).equals("") || String.valueOf(editnoHp.getText()).equals("") || String.valueOf(editAlamat.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "Mohon Lengkapi Data!", Toast.LENGTH_SHORT).show();
        } else {
            db.update(Integer.parseInt(id), editNama.getText().toString(), editEmail.getText().toString(), editnoHp.getText().toString(), editAlamat.getText().toString());
            finish();
        }
    }
}