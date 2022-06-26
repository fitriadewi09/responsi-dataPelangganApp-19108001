package com.example.datapelanggan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.datapelanggan.adapter.Adapter;
import com.example.datapelanggan.helper.Helper;
import com.example.datapelanggan.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> lists = new ArrayList<>();
    Adapter adapter;
    Helper db = new Helper(this);
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Helper(getApplicationContext());
        btnAdd = findViewById(R.id.btn_Add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity.this, lists);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = lists.get(i).getId();
                final String nama = lists.get(i).getNama();
                final String email = lists.get(i).getEmail();
                final String noHp = lists.get(i).getNoHp();
                final String alamat = lists.get(i).getAlamat();
                final CharSequence[] dialogItem = {"Edit Data", "Hapus Data"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("nama", nama);
                                intent.putExtra("email", email);
                                intent.putExtra("noHp", noHp);
                                intent.putExtra("alamat", alamat);
                                startActivity(intent);
                                break;

                            case 1:
                                db.delete(Integer.parseInt(id));
                                lists.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }

    private void getData() {
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i<rows.size(); i++) {
            String id = rows.get(i).get("id");
            String nama = rows.get(i).get("nama");
            String email = rows.get(i).get("email");
            String noHp = rows.get(i).get("noHp");
            String alamat = rows.get(i).get("alamat");

            Data data = new Data();
            data.setId(id);
            data.setNama(nama);
            data.setEmail(email);
            data.setNoHp(noHp);
            data.setAlamat(alamat);
            lists.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lists.clear();
        getData();
    }
}