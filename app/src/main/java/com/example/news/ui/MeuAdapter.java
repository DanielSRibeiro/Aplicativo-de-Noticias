package com.example.news.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.model.Noticias;
import com.example.news.model.NoticiasModel;

import java.util.ArrayList;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewHolder> {

    ArrayList<NoticiasModel> arrayList;

    public MeuAdapter(ArrayList<NoticiasModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meu_adapter, parent, false);
        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {

        NoticiasModel arrayLists = arrayList.get(position);

        holder.textViewTitulo.setText(arrayLists.getArticles().get(position).getTitle());
        holder.textViewAutor.setText(arrayLists.getArticles().get(position).getAuthor());
        holder.textViewDescricao.setText(arrayLists.getArticles().get(position).getDescription());
        holder.textViewData.setText(arrayLists.getArticles().get(position).getPublishedAt());

//        Picasso.get().load(arrayListDtoNews.get(position).getUrlToImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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
