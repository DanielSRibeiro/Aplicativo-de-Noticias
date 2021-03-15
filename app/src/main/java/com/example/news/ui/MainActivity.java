package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.data.model.Noticias;
import com.example.news.data.model.NoticiasModel;
import com.example.news.data.model.ApiClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewNoticia;
    ProgressBar progressBar;
    ArrayList<Noticias> arrayList = new ArrayList<>();
    EditText editTextBuscar;
    Button buttonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        chamandoNoticias();

        editTextBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                progressBar.setVisibility(View.VISIBLE);

                if (!(s.length() < 1)){
                    chamandoNoticiasPortugues(editTextBuscar.getText().toString());
                } else{
                    chamandoNoticias();
                }
            }
        });

        recyclerViewNoticia.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerViewNoticia,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Noticias noticias = arrayList.get(position);

                        chamaUrl(noticias.getUrl());
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));
    }

    private void chamaUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void initView() {
        progressBar = findViewById(R.id.progressBar);
        recyclerViewNoticia = findViewById(R.id.recyclerViewHome);
        editTextBuscar = findViewById(R.id.editTextBuscar);
        buttonBuscar = findViewById(R.id.buttonBuscar);
        progressBar.setVisibility(View.VISIBLE);
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
                Toast.makeText(MainActivity.this, "Sem conexões com a internet", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void chamandoNoticiasPortugues(String s) {
        Call<NoticiasModel> call = ApiClient.getService().getNewsPortuguese(s,"dddb7cb2da564769ac4115af06cbfddd","pt");
        call.enqueue(new Callback<NoticiasModel>() {
            @Override
            public void onResponse(Call<NoticiasModel> call, Response<NoticiasModel> response) {
                if(response.isSuccessful()){
                    arrayList = (ArrayList<Noticias>) response.body().getArticles();
                    chamaAdapter(arrayList);

                } else{
                    Toast.makeText(MainActivity.this, "ERRO: "+response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NoticiasModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Sem conexões com a internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chamaAdapter(ArrayList<Noticias> arrayList) {
        recyclerViewNoticia.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.INVISIBLE);
        recyclerViewNoticia.setAdapter(new MeuAdapter(arrayList));
    }
}