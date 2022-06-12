package com.example.karsa.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karsa.R;
import com.example.karsa.adapter.AdapterArtikel1;
import com.example.karsa.adapter.AdapterArtikel2;
import com.example.karsa.helper.Artikel1HelperClass;
import com.example.karsa.helper.Artikel2HelperClass;

import java.util.ArrayList;
import java.util.List;


public class ArtikelFragment extends Fragment {

    RecyclerView artikel1, artikel2;
    List<Artikel1HelperClass> listArtikel1;
    List<Artikel2HelperClass> listArtikel2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_artikel, container, false);

        artikel1 = view.findViewById(R.id.rvArtikel1);
        artikel1.setHasFixedSize(true);
        artikel1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        artikel1.setAdapter(new AdapterArtikel1(Artikel1Recycler()));

        artikel2 = view.findViewById(R.id.rvArtikel2);
        artikel2.setHasFixedSize(true);
        artikel2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        artikel2.setAdapter(new AdapterArtikel2((Artikl2Recycler())));

       return view;
    }

    private List<Artikel2HelperClass> Artikl2Recycler() {

        listArtikel2 = new ArrayList<>();
        listArtikel2.add(new Artikel2HelperClass(R.drawable.berita4,
                "Tempo - 13/06/2021, 05:55 WIB",
                "Daftar 10 Politeknik Terbaik, Kuliahnya Lebih Banyak Praktek Ketimbang Teori",
                "INFO EDUKASI"));
        listArtikel2.add(new Artikel2HelperClass(R.drawable.berita1,
                "CNN Indonesia - 03/12/2020, 09:29 WIB",
                "Hari Disabilitas: Tidak Semua yang\nDisabilitas Bisa Terlihat",
                "HARI PENYANDANG DISABILITAS"));
        listArtikel2.add(new Artikel2HelperClass(R.drawable.berita2,
                "CNN Indonesia - 03/12/2019, 12:58 WIB",
                "Hari Disabilitas Perjuangkan Akses\nMasa Depan yang Setara",
                "HARI DISABILITAS INTERNASIONAL"));
        listArtikel2.add(new Artikel2HelperClass(R.drawable.berita3,
                "Kompas - 16/03/2021, 16:06 WIB",
                "Sinopsis Come As You Are, Pencarian jati diri tiga penyandang disabilitas",
                "DISABILITAS"));

        return listArtikel2;
    }

    private List<Artikel1HelperClass> Artikel1Recycler() {

        listArtikel1 = new ArrayList<>();
        listArtikel1.add(new Artikel1HelperClass(R.drawable.artikel1,
                "Kompas - 13 Juni 2021",
                "Kasus positif corona di Indonesia tembus 1,9 juta kasus"));
        listArtikel1.add(new Artikel1HelperClass(R.drawable.artikel2,
                "Kompas - 10 April 2021",
                "Pemerintah Berkomitmen Lindungi dan Penuhi\nHak Penyandang Disabilitas"));
        listArtikel1.add(new Artikel1HelperClass(R.drawable.artikel3,
                "Tribun News - 12 Juni 2021",
                "Mensos Resmikan SKA Balai Ciung Wanara: Penyandang Disabilitas Miliki Kesempatan Sama"));
        listArtikel1.add(new Artikel1HelperClass(R.drawable.artikel4,
                "Kemenko - 12 April 2019",
                "Kemenko PMK Mulai Bahas Rumusan Kebijakan Pemberdayaan Disabilitas dan Lansia"));

        return listArtikel1;
    }
}