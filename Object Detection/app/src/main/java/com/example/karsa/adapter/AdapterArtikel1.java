package com.example.karsa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.karsa.R;
import com.example.karsa.helper.Artikel1HelperClass;

import java.util.List;

public class AdapterArtikel1 extends RecyclerView.Adapter<AdapterArtikel1.Artikel1ViewHolder> {

    List<Artikel1HelperClass> artikel1List;


    public AdapterArtikel1(List<Artikel1HelperClass> Artikel1List) {
        this.artikel1List = Artikel1List;
    }

    @NonNull
    @Override
    public AdapterArtikel1.Artikel1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_artikel1, parent, false);
        AdapterArtikel1.Artikel1ViewHolder artikel1ViewHolder = new AdapterArtikel1.Artikel1ViewHolder(view);
        return artikel1ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  Artikel1ViewHolder holder, int position) {

        Artikel1HelperClass artikel1HelperClass = artikel1List.get(position);

        holder.imageArtikel1.setImageResource(artikel1HelperClass.getImage());
        holder.tvSumber.setText(artikel1HelperClass.getSumber());
        holder.tvJudulBerita.setText(artikel1HelperClass.getJudul_berita());
    }

    @Override
    public int getItemCount() {
        return artikel1List.size();
    }

    public class Artikel1ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageArtikel1;
        TextView tvSumber;
        TextView tvJudulBerita;

        public Artikel1ViewHolder(@NonNull View view) {
            super(view);

            imageArtikel1 = view.findViewById(R.id.imageArtikel);
            tvSumber = view.findViewById(R.id.tvSumber);
            tvJudulBerita = view.findViewById(R.id.tvJudulBerita);
        }
    }
}
