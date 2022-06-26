package com.example.datapelanggan.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "crudPelanggan";

    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE pelanggan (id INTEGER PRIMARY KEY autoincrement, nama TEXT NOT NULL, email TEXT NOT NULL, noHp TEXT NOT NULL, alamat TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pelanggan");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM pelanggan";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("email", cursor.getString(2));
                map.put("noHp", cursor.getString(3));
                map.put("alamat", cursor.getString(4));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String nama, String email, String noHp, String alamat) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "INSERT INTO pelanggan(nama,email,noHp,alamat) VALUES('"+nama+"', '"+email+"', '"+noHp+"', '"+alamat+"')";
        database.execSQL(QUERY);
    }

    public void update(int id, String nama, String email, String noHp, String alamat) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE pelanggan SET nama = '"+nama+"', email = '"+email+"', noHp = '"+noHp+"', alamat = '"+alamat+"' WHERE id = "+id;
        database.execSQL(QUERY);
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM pelanggan WHERE id = "+id;
        database.execSQL(QUERY);
    }
}
