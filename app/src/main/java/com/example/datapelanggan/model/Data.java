package com.example.datapelanggan.model;

public class Data {
    private String id, nama, email, noHp, alamat;

    public Data() {

    }

    public Data(String id, String nama, String email, String noHp, String alamat) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.noHp = noHp;
        this.alamat = alamat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
