package com.example.karsa.helper;

public class Artikel2HelperClass {
    int image;
    String sumber;
    String judul_berita;
    String tagline;

    public Artikel2HelperClass(int image, String sumber, String judul_berita, String tagline) {
        this.image = image;
        this.sumber = sumber;
        this.judul_berita = judul_berita;
        this.tagline = tagline;
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

    public String getTagline() {
        return tagline;
    }
}
