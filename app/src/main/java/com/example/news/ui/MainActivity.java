package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.model.Noticias;
import com.example.news.model.NoticiasModel;
import com.example.news.model.ApiClient;
import com.google.android.material.textfield.TextInputLayout;

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
                if (!(s.length() < 1)){
                    progressBar.setVisibility(View.VISIBLE);
                    chamandoNoticiasPortugues(editTextBuscar.getText().toString());
                } else{
                    editTextBuscar.setError("É Necessário Escrever algo!!!");
                }
            }
        });
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