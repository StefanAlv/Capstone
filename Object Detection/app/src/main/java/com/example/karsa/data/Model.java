package com.example.karsa.data;

public class Model {

    private String imageUri;
    private String namaObjek;
    private String namaSampel;


    public Model() {

    }

    public Model(String imageUri) {
        this.imageUri = imageUri;
        this.namaObjek = namaObjek;
        this.namaSampel = namaSampel;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getNamaObjek() {
        return namaObjek;
    }

    public void setNamaObjek(String namaObjek) {
        this.namaObjek = namaObjek;
    }

    public String getNamaSampel() {
        return namaSampel;
    }

    public void setNamaSampel(String namaSampel) {
        this.namaSampel = namaSampel;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }


}
