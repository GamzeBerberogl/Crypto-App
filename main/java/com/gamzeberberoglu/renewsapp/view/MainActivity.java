package com.gamzeberberoglu.renewsapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.gamzeberberoglu.renewsapp.R;
import com.gamzeberberoglu.renewsapp.adapter.RecyclerViewAdapter;
import com.gamzeberberoglu.renewsapp.model.Model;
import com.gamzeberberoglu.renewsapp.service.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String  BASE_URL = "https://api.binance.com";

    ArrayList<Model> dataModels;
    Retrofit retrofit;

    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    Button btnSearch;

    EditText editText;



    //  https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC&tsyms=USD,EUR &api_key=6a802c1853fb2f7e9b0f5559cab8ef1e7271391ac7521002720987687c4655e5
    //  &api_key=6a802c1853fb2f7e9b0f5559cab8ef1e7271391ac7521002720987687c4655e5
    //  base url https://www.cryptocompare.com

    // https://api.binance.com/api/v3/ticker/price&api_key=PYO0oNa3o64sHn3iLPuzf6L1KWBA8dkqsoXIpXpq8Jlmn83o8Z8utxOQpDCCEjrM
    //  binance api key PYO0oNa3o64sHn3iLPuzf6L1KWBA8dkqsoXIpXpq8Jlmn83o8Z8utxOQpDCCEjrM


    //  https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        getDataFromAPI();
    }



    private void getDataFromAPI() {
        Api api = retrofit.create(Api.class);

        Call<List<Model>> call = api.getData();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.isSuccessful()) {
                    List<Model> responseList = response.body();
                    dataModels = new ArrayList<>(responseList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(dataModels);
                    recyclerView.setAdapter(recyclerViewAdapter);

//                    for (Model model : dataModels) {
//                        System.out.println(model.symbol);
//                        System.out.println(model.price);
//                    }
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}