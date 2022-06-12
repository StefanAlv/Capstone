package com.example.karsa.data;

public class InputSampel {
    private String key;

    private String objek;
    private String sampel;

    public InputSampel() {

    }

    public InputSampel( String objek, String sampel) {
        this.objek = objek;
        this.sampel = sampel;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getObjek() {
        return objek;
    }

    public void setObjek(String objek) {
        this.objek = objek;
    }

    public String getSampel() {
        return sampel;
    }

    public void setSampel(String sampel) {
        this.sampel = sampel;
    }
}
