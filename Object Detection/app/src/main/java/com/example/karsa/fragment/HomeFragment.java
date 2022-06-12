package com.example.karsa.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.karsa.FiturDeteksiActivity;
import com.example.karsa.R;
import com.example.karsa.adapter.AdapterArtikel1;
import com.example.karsa.helper.Artikel1HelperClass;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView artikel1;
    List<Artikel1HelperClass> listArtikel1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        artikel1 = view.findViewById(R.id.rvBerita);
        artikel1.setHasFixedSize(true);
        artikel1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        artikel1.setAdapter(new AdapterArtikel1(Artikel1Recycler()));

        Button btnFitur = view.findViewById(R.id.fiturDeteksi);
        btnFitur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FiturDeteksiActivity.class);
                startActivity(intent);
            }
        });

        return view;
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