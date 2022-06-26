package com.example.datapelanggan.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.datapelanggan.model.Data;
import com.example.datapelanggan.R;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists) {
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null && inflater != null) {
            view = inflater.inflate(R.layout.list_pelanggan, null);
        }
        if(view != null) {
            TextView nama = view.findViewById(R.id.text_nama);
            TextView email = view.findViewById(R.id.text_email);
            TextView noHp = view.findViewById(R.id.text_noHp);
            TextView alamat = view.findViewById(R.id.text_alamat);
            Data data = lists.get(i);
            nama.setText(data.getNama());
            email.setText(data.getEmail());
            noHp.setText(data.getNoHp());
            alamat.setText(data.getAlamat());
        }
        return view;
    }
}
