package com.hfad.techtask1.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hfad.techtask1.R;
import com.hfad.techtask1.adapters.CustomAdapter;
import com.hfad.techtask1.model.Data;
import com.hfad.techtask1.network.APIclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {


    GridView gridView;
    EditText editText;
    CustomAdapter customAdapter;
    Button  button1;
    public static  List<Data> dataList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.input_edit_text);
        gridView = findViewById(R.id.grid);
        button1 = findViewById(R.id.grid_button);

        Call<List<Data>> call = APIclient.apIinterface().getData();

        call.enqueue(new Callback<List<Data>>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                //todo if there is not internet connection
                if (response.isSuccessful()) {
                    customAdapter = new CustomAdapter(response.body(), MainActivity.this);
                    gridView.setAdapter(customAdapter);
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            dataList = response.body();
                            Intent i = new Intent(getApplicationContext(),WebViewActivity.class);
                            i.putExtra("url", dataList.get(position).getUrl());
                            startActivity(i);
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Failure (On response)", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

}