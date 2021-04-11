package com.example.news.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.data.model.Noticias;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MeuAdapter extends RecyclerView.Adapter<MeuAdapter.MeuViewHolder> {

    private ArrayList<Noticias> arrayList;
    private OnNoteListener mOnNoteListener;

    public MeuAdapter(ArrayList<Noticias> arrayList, OnNoteListener onNoteListener) {
        this.arrayList = arrayList;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meu_adapter, parent, false);
        return new MeuViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MeuViewHolder holder, int position) {

        Noticias a = arrayList.get(position);

        holder.textViewTitulo.setText(a.getTitle());
        holder.textViewAutor.setText(a.getSource().getName());
        holder.textViewDescricao.setText(a.getDescription());
        holder.textViewData.setText(a.getPublishedAt());

        Picasso.get().load(a.getUrlToImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MeuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewTitulo, textViewAutor,textViewData,textViewDescricao;
        ImageView img;

        OnNoteListener onNoteListener;

        public MeuViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            textViewAutor = itemView.findViewById(R.id.textViewAutor);
            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewData = itemView.findViewById(R.id.textViewData);
            textViewDescricao = itemView.findViewById(R.id.textViewDescricao);
            img = itemView.findViewById(R.id.imageAdapterHome);

            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onClickNew(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onClickNew(int posicao);
    }
}
