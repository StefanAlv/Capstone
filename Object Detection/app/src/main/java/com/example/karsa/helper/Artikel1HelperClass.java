package com.example.karsa.helper;

public class Artikel1HelperClass {
    int image;
    String sumber;
    String judul_berita;

    public Artikel1HelperClass(int image, String sumber, String judul_berita) {
        this.image = image;
        this.sumber = sumber;
        this.judul_berita = judul_berita;
    }

    public int getImage() {
        return image;
    }

    public String getSumber() {
        return sumber;
    }

    public String getJudul_berita() {
        return judul_berita;
    }
}
