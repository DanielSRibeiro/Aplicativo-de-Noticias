package com.example.news.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.model.New;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MeuViewHolder> {

    ArrayList<New> arrayListNews;

    public Adapter(ArrayList<New> arrayListNews) {
        this.arrayListNews = arrayListNews;
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meu_adapter, parent, false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {
        holder.textViewTitulo.setText(arrayListNews.get(position).getTitle());
        holder.textViewAutor.setText(arrayListNews.get(position).getAuthor());
        holder.textViewDescricao.setText(arrayListNews.get(position).getDescription());
        holder.textViewData.setText(arrayListNews.get(position).getPublishedAt());
        holder.img.setImageBitmap(arrayListNews.get(position).getUrlToImage());
    }

    @Override
    public int getItemCount() {
        return arrayListNews.size();
    }

    class MeuViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitulo, textViewAutor,textViewData,textViewDescricao;
        ImageView img;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewAutor = itemView.findViewById(R.id.textViewAutor);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewData = itemView.findViewById(R.id.textViewData);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
            img = itemView.findViewById(R.id.imageAdapterHome);
        }
    }
}
