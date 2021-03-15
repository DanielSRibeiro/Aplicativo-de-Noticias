package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.Network.NoticiasService;
import com.example.news.R;
import com.example.news.model.Noticias;
import com.example.news.model.NoticiasModel;
import com.example.news.model.RestClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewNoticia;
    TextView textViewQualquer, textViewTitulo, textViewData, textViewDescricao;
    ProgressBar progressBar;
    ArrayList<NoticiasModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textViewQualquer = findViewById(R.id.textViewQualquer);
        recyclerViewNoticia = findViewById(R.id.recyclerViewHome);
        textViewTitulo = findViewById(R.id.textViewTitulo);
        textViewData = findViewById(R.id.textViewData);
        textViewDescricao = findViewById(R.id.textViewDescricao);

        progressBar.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        chamandoNoticias();
    }

    private void chamandoNoticias() {

        NoticiasService service = RestClient.getService();
        service.getNews("tesla", "dddb7cb2da564769ac4115af06cbfddd").enqueue(new Callback<NoticiasModel>() {
            @Override
            public void onResponse(Call<NoticiasModel> call, Response<NoticiasModel> response) {

                if (response.isSuccessful()) {
                    textViewQualquer.setText("Sucesso: " + response.code());

                } else {
                    textViewQualquer.setText("Code: " + response.code());
                }

            }

            @Override
            public void onFailure(Call<NoticiasModel> call, Throwable t) {

                t.printStackTrace();
                textViewQualquer.setText("onFailure");

            }
        });
    }

    private void chamaAdapter(ArrayList<NoticiasModel> arrayList) {

        recyclerViewNoticia.setLayoutManager(new LinearLayoutManager(this));
        MeuAdapter adapter = new MeuAdapter(arrayList);
        recyclerViewNoticia.setAdapter(adapter);
    }

}