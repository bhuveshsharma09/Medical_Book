package com.bhuvesh.medicalbook;

public class Yoga {
    private String yogaName;
    private String yogaDetail;


    public static final Yoga[] yogas = {
            new Yoga("Chu Asan","Chu main le lo"), new Yoga("Lul Asan","Piche le lo")
            , new Yoga("Tut Asan","Gand main le lo"), new Yoga("Saniya Asan","Saiyan ki bahon main")
            , new Yoga("Tut Asan","Gand main le lo"), new Yoga("Tut Asan","Gand main le lo"),
            new Yoga("Tut Asan","Gand main le lo"), new Yoga("Tut Asan","Gand main le lo"),};


    public Yoga(String yogaName, String yogaDetail) {
        this.yogaName = yogaName;
        this.yogaDetail = yogaDetail;
    }


    public String getYogaName() {
        return yogaName;
    }

    public String getYogaDetail() {
        return yogaDetail;
    }


    @Override
    public String toString() {
        return "Yoga{" +
                "yogaName='" + yogaName + '\'' +
                '}';
    }
}