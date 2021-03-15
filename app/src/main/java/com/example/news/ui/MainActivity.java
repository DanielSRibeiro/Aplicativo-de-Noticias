package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.model.Noticias;
import com.example.news.model.NoticiasModel;
import com.example.news.model.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewNoticia;
    ProgressBar progressBar;
    ArrayList<Noticias> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerViewNoticia = findViewById(R.id.recyclerViewHome);

        progressBar.setVisibility(View.VISIBLE);
        chamandoNoticias();
    }

    private void chamandoNoticias() {

        Call<NoticiasModel> call = ApiClient.getService().getNews ("dddb7cb2da564769ac4115af06cbfddd", "br");
        call.enqueue(new Callback<NoticiasModel>() {
            @Override
            public void onResponse(Call<NoticiasModel> call, Response<NoticiasModel> response) {

                if (response.isSuccessful()) {
                    arrayList = (ArrayList<Noticias>) response.body().getArticles();
                    chamaAdapter(arrayList);

                } else {
                    Toast.makeText(MainActivity.this, "ERRO: "+response.code(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<NoticiasModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "A Execução não pode ser realizado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chamaAdapter(ArrayList<Noticias> arrayList) {
        recyclerViewNoticia.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.INVISIBLE);
        recyclerViewNoticia.setAdapter(new MeuAdapter(arrayList));
    }

}