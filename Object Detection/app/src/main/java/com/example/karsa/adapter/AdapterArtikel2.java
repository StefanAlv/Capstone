package com.example.karsa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karsa.R;
import com.example.karsa.helper.Artikel2HelperClass;

import java.util.List;

public class AdapterArtikel2 extends RecyclerView.Adapter<AdapterArtikel2.Artikel2ViewHolder> {

    List<Artikel2HelperClass> artikel2List;


    public AdapterArtikel2(List<Artikel2HelperClass> Artikel2List) {
        this.artikel2List = Artikel2List;
    }

    @NonNull
    @Override
    public AdapterArtikel2.Artikel2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_artikel2, parent, false);
        AdapterArtikel2.Artikel2ViewHolder artikel2ViewHolder = new AdapterArtikel2.Artikel2ViewHolder(view);
        return artikel2ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  Artikel2ViewHolder holder, int position) {

        Artikel2HelperClass artikel2HelperClass = artikel2List.get(position);

        holder.imageArtikel2.setImageResource(artikel2HelperClass.getImage());
        holder.tvSumber.setText(artikel2HelperClass.getSumber());
        holder.tvTagline.setText(artikel2HelperClass.getTagline());
        holder.tvJudulBerita.setText(artikel2HelperClass.getJudul_berita());
    }

    @Override
    public int getItemCount() {
        return artikel2List.size();
    }

    public class Artikel2ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageArtikel2;
        TextView tvTagline;
        TextView tvSumber;
        TextView tvJudulBerita;

        public Artikel2ViewHolder(@NonNull View view) {
            super(view);

            imageArtikel2 = view.findViewById(R.id.imageArtikel2);
            tvTagline = view.findViewById(R.id.tvTagline);
            tvSumber = view.findViewById(R.id.tvSumber);
            tvJudulBerita = view.findViewById(R.id.tvJudulBerita);
        }
    }
}

